package com.yzt.app.controller.video;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.yzt.app.config.YztWebInterfaceConfig;
import com.yzt.app.model.YztLearningcenterVideo;
import com.yzt.app.service.LearningcenterVideoService;
import com.yzt.app.utils.web.MD5;
/**
 * 上传云端视频
 * @author Administrator
 *
 */
public class UploadVideoRunnable implements Runnable {

	private LearningcenterVideoService learningcenterVideoService;
	// 分块的界限
	public static final int limit = 1024 * 1024 * 2;
	// 早教中心的视频
	private YztLearningcenterVideo learningcentervideo;
	//上传的视频文件
	private File file;
	//文件分块大小
	private long filesize;
	// 已接收的字节数
	private long received = 0;
	// 上传需要的参数
	private String servicetype = null;
	//
	private String metaurl = null;
	//分割为大块文件的url
	private String chunkurl = null;
	private String md5 = null;
	
	/**
	 * 上传云端线程
	 * 构造器为属性赋值
	 * @param video
	 * @param file
	 * @param learningcenterVideoService
	 */
	public UploadVideoRunnable(YztLearningcenterVideo video, File file,
			LearningcenterVideoService learningcenterVideoService) {
		this.learningcentervideo = video;
		this.file = file; 
		this.filesize = video.getFileSize();
		this.learningcenterVideoService = learningcenterVideoService;
	}
	
	/**
	 * 开启新的线程
	 */
	@Override
	public void run() {
		try {
			// 第一步：创建视频信息 并 计算MD5
			create();
			md5 = MD5.getFileMD5(file);
			// 第二步：上传视频meta信息
			uploadmeta();
			// 第三步：循环上传文件块
			long left = filesize;
			while (left > 0) {
				long end = filesize - 1;
				if (left > limit) {
					end = received + limit - 1;
				}
				int i = uploadchunk(received, end);
				if (i == -1) {
					System.out.println("上传出错");
					return;
				}
				left = filesize - received;
			}
			// 更新数据库
			learningcenterVideoService.updateBySql(" set videoid = '"
					+ learningcentervideo.getVideoid()
					+ "' status = 1 where id='" + learningcentervideo.getId()
					+ "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 第一步：创建视频上传信息
	 * @throws Exception
	 */
	
	public void create() throws Exception {
		Map<String, String> treeMap = new TreeMap<String, String>();
		// 查询参数输入
		treeMap.put("userid", YztWebInterfaceConfig.userid);
		// 设置参数
		treeMap.put("title", learningcentervideo.getVideoTitle());
		treeMap.put("describe", learningcentervideo.getDescribe());
		treeMap.put("filename", learningcentervideo.getFileName());
		treeMap.put("filesize", learningcentervideo.getFileSize() + "");
		treeMap.put("notify_url", YztWebInterfaceConfig.notify_url);
		String qs = createQueryString(treeMap);
		// 生成时间片
		long time = new Date().getTime() / 1000;
		// 生成HASH码值
		String hash = md5(String.format("%s&time=%s&salt=%s", qs, time,
				YztWebInterfaceConfig.key));
		// 生成地址
		String address = YztWebInterfaceConfig.api_createVideo + "?" + qs
				+ "&time=" + time + "&hash=" + hash;

		// 上传 并 获取返回信息
		URL url = new URL(address);
		URLConnection conn = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			sb.append(str);
			sb.append("\r\n");
		}
		reader.close();
		System.out.println(sb.toString());

		Document document = null;
		try {
			document = DocumentHelper.parseText(sb.toString());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// 解析xml提取信息（失败、成功）
		Element root = document.getRootElement();
		Element eleVideoid = root.element("videoid");
		learningcentervideo.setVideoid(eleVideoid.getTextTrim());

		Element eleServicetype = root.element("servicetype");
		servicetype = eleServicetype.getTextTrim();

		Element eleMetaurl = root.element("metaurl");
		metaurl = eleMetaurl.getTextTrim();

		Element eleChunkurl = root.element("chunkurl");
		chunkurl = eleChunkurl.getTextTrim();
	}
	
	/**
	 * 第二步：上传视频meta信息
	 * @throws Exception
	 */
	public void uploadmeta() throws Exception {
		// 举例说明为视频搜索
		Map<String, String> treeMap = new TreeMap<String, String>();
		// 查询参数输入
		treeMap.put("uid", YztWebInterfaceConfig.userid);
		treeMap.put("ccvid", learningcentervideo.getVideoid());
		treeMap.put("first", "1");
		treeMap.put("filename", learningcentervideo.getFileName());
		treeMap.put("md5", md5);
		treeMap.put("filesize", filesize + "");
		treeMap.put("servicetype", servicetype);

		String qs = createQueryString(treeMap);
		// 生成时间片
		long time = new Date().getTime() / 1000;

		// 生成HASH码值
		String hash = md5(String.format("%s&time=%s&salt=%s", qs, time,
				YztWebInterfaceConfig.key));
		// 生成地址
		String address = metaurl + "?" + qs + "&time=" + time + "&hash=" + hash;
		System.out.println(address);

		// 上传 并 获取返回信息
		URL url = new URL(address);
		URLConnection conn = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			sb.append(str);
			sb.append("\r\n");
		}
		reader.close();
		System.out.println(sb.toString());

	}
	/**
	 * 第三步：断点续传
	 * @param start
	 * @param end
	 * @return
	 */
	public int uploadchunk(long start, long end) {
		String result = uploadchunk(
				chunkurl + "?ccvid=" + learningcentervideo.getVideoid()
						+ "&format=xml", start, end, file);
		Document document = null;
		try {
			document = DocumentHelper.parseText(result);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		String msg = root.element("msg").getText();
		if ("success".equals(msg)) {
			String r = root.element("received").getTextTrim();
			int ls = Integer.parseInt(r);
			received = ls;
			System.out.println("上传成功：" + received + "字节");
			return ls;
		}
		return -1;
	}

	/**
	 * url为/servlet/uploadchunk?ccvid=&format= 
	 * chunkStart为chunk起始位置 
	 * chunkStart为chunk起始位置 
	 * chunkEnd为chunk结束位置 
	 * file为文件
	 * bufferOut为实际文件输出二进制内容
	 * 
	 * @param url
	 * @param chunkStart
	 * @param chunkEnd
	 * @param file
	 * @return
	 */
	public static String uploadchunk(String url, long chunkStart,
			long chunkEnd, File file) {
		byte[] bufferOut = null;
		try {
			bufferOut = readChunk(file, chunkStart, chunkEnd);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (bufferOut == null) {
			return "read file error";
		}
		HttpURLConnection conn = null;
		try {
			String BOUNDARY = "---------CCHTTPAPIFormBoundaryEEXX"
					+ new Random().nextInt(65536); // 定义数据分隔线
			URL openUrl = new URL(url);
			conn = (HttpURLConnection) openUrl.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4)");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			// content-range
			conn.setRequestProperty("Content-Range", "bytes " + chunkStart
					+ "-" + chunkEnd + "/" + file.length());
			System.out.println("bytes " + chunkStart + "-" + chunkEnd + "/"
					+ file.length());

			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			StringBuilder sb = new StringBuilder();
			sb.append("--").append(BOUNDARY).append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file"
					+ file.getName() + "\";filename=\"" + file.getName()
					+ "\"\r\n");
			sb.append("Content-Type: application/octet-stream\r\n");
			sb.append("\r\n");
			byte[] data = sb.toString().getBytes();
			out.write(data);
			out.write(bufferOut);
			out.write("\r\n".getBytes());
			// 定义最后数据分隔线
			byte[] end_data = ("--" + BOUNDARY + "--\r\n").getBytes();
			out.write(end_data);
			out.flush();
			out.close();

			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuffer resultBuf = new StringBuffer("");
			String line = null;
			while ((line = reader.readLine()) != null) {
				resultBuf.append(line);
			}
			reader.close();
			conn.disconnect();
			return resultBuf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return null;
	}

	/**
	 * 读取文件指定范围的字节
	 * @param file
	 * @param chunkStart
	 * @param chunkEnd
	 * @return
	 * @throws IOException
	 */
	public static byte[] readChunk(File file, long chunkStart, long chunkEnd)
			throws IOException {
		if (file == null) {
			throw new IOException("The file does not exist");
		}
		long fileLength = file.length();
		if (chunkStart >= fileLength) {
			throw new IOException("Start position > file length");
		}
		RandomAccessFile accessFile = null;
		try {
			int chunksLen = (int) (chunkEnd - chunkStart + 1);
			byte[] chunks = new byte[chunksLen];
			accessFile = new RandomAccessFile(file, "r");
			accessFile.seek(chunkStart);
			int readLength = accessFile.read(chunks, 0, chunksLen);
			System.out.println("read Length: " + readLength);
			accessFile.close();
			return chunks;
		} finally {
			if (accessFile != null) {
				try {
					accessFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** 以下为THQS算法的相关函数 */
	/**
	 * 功能：用一个Map生成一个QueryString，参数的顺序不可预知。
	 * 
	 * @param queryString
	 * @return
	 */
	public static String createQueryString(Map<String, String> queryMap) {
		if (queryMap == null) {
			return null;
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : queryMap.entrySet()) {
				if (entry.getValue() == null) {
					continue;
				}
				String key = entry.getKey().trim();
				String value = URLEncoder.encode(entry.getValue().trim(),
						"utf-8");
				sb.append(String.format("%s=%s&", key, value));
			}
			return sb.substring(0, sb.length() - 1);
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能：计算字符串的md5值
	 * 
	 * @param src
	 * @return
	 */
	public static String md5(String src) {
		return digest(src, "MD5");
	}

	/**
	 * 功能：根据指定的散列算法名，得到字符串的散列结果。
	 * 
	 * @param src
	 * @param name
	 * @return
	 */
	private static String digest(String src, String name) {
		try {
			MessageDigest alg = MessageDigest.getInstance(name);
			byte[] result = alg.digest(src.getBytes());
			return byte2hex(result);
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}
	}

	/**
	 * 功能：将byte数组转换成十六进制可读字符串。
	 * 
	 * @param b
	 *            需要转换的byte数组
	 * @return 如果输入的数组为null，则返回null；否则返回转换后的字符串。
	 */
	public static String byte2hex(byte[] b) {

		if (b == null) {
			return null;
		}
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0" + stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	
}
