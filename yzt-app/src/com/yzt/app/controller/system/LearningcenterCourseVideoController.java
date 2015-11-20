package com.yzt.app.controller.system;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzt.app.config.YztConstant;
import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztLearningcenterVideo;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/system/learningcenter/course/video")
public class LearningcenterCourseVideoController extends BaseController {
	
	@RequestMapping(value={"html/{learningcenterId}/{courseId}"},method = RequestMethod.GET)
	public String index(@PathVariable String learningcenterId,@PathVariable String courseId, ModelMap m){
		m.put("learningcenterId", learningcenterId);
		m.put("courseId", courseId);
		return "system/learningcenter/course/video/index";
	}
	
	
	@RequestMapping("/listjson/{learningcenterId}/{courseId}")
	@ResponseBody
	public EasyuiDataGridJson listjson(@PathVariable String learningcenterId,@PathVariable String courseId,EasyuiDataGrid dg, ModelMap m){
		return learningcenterVideoService.datagrid( dg,learningcenterId,courseId);
	}
	
	/**
	 * 云端上传（yhm修改）
	 * @param learningcenterId
	 * @param courseId
	 * @param file
	 * @param m
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save/{learningcenterId}/{courseId}")
	@ResponseBody
	public Json save(@PathVariable String learningcenterId,@PathVariable String courseId,@RequestParam("file")MultipartFile file,ModelMap m) throws Exception{
		Json json = new Json();
		if(file.isEmpty()){
			//上传文件为空
			System.out.println("no file");
			json.setSuccess(false);
			json.setMsg("无源文件");
		}else{
			//有上传文件
			//StringUtils是API里面的方法
			String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
			String id = GUIDGener.getGUID();
			String filedir=YztConstant.originalPath+"/learningcenter/"+learningcenterId+"/video";
			//String filepath = null;
			
			YztUtil.writeFile(file.getInputStream(), filedir, id+"."+filetype);
			YztLearningcenterVideo pic = new YztLearningcenterVideo();
			pic.setId(id);
			pic.setYztLearningcenterId(learningcenterId);
			pic.setCourseId(courseId);
			pic.setFileType(filetype);
			pic.setFilePath("file:"+filedir+"/"+id+"."+filetype);
			
			pic.setFileName(file.getOriginalFilename());
			pic.setFileContenttype(file.getContentType());
			pic.setCreatetime(new Date());
			
			learningcenterVideoService.save(pic);
			
			//更新早教中心的显示图片数量
			learningcenterService.updateBySql(" set video_count=video_count+1 where id='"+learningcenterId+"'");
			//更新早教中心的显示图片数量
			learningcenterCourseService.updateBySql(" set video_count=video_count+1 where id='"+courseId+"'");
			
			json.setSuccess(true);
			json.setMsg("服务器上传成功");
			
			/*pic.setVideoTitle(file.getOriginalFilename());
			pic.setChecked(1);
			pic.setDescribe(file.getName());
			pic.setStatus("OK");*/
	
			/*上传云服务器*/
		//	new Thread(new UploadVideoRunnable(pic, new File(filepath), learningcenterVideoService)).start();
			
		
		}
		return json;
	}
	
	@RequestMapping("view/{learningcenterId}/{videoId}")
	public String view(@PathVariable String learningcenterId,@PathVariable String videoId, ModelMap m){
		m.put("learningcenterId", learningcenterId);
		m.put("videoId", videoId);
		return "system/learningcenter/video/videoViewer";
	}
	/**
	 * 显示视频列表
	 * @param learningcenterId
	 * @param videoId
	 * @param response
	 * @param m
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@RequestMapping("display/{learningcenterId}/{videoId}")
	public void display(@PathVariable String learningcenterId,@PathVariable String videoId, HttpServletResponse response,ModelMap m) throws MalformedURLException, IOException{
		
		YztLearningcenterVideo video = new YztLearningcenterVideo();
		video.setId(videoId);
		video.setYztLearningcenterId(learningcenterId);
		YztLearningcenterVideo lpic = learningcenterVideoService.selectByKey(video);
		
		String filepath = StringUtils.substringAfter(lpic.getFilePath(), "file:");
		File file = new File(filepath);
		//return "system/learningcenter/pic/imgViewer";
		
		InputStream inputStream = YztUtil.getInputStreamFromFilepath("file:"+filepath);
        response.setContentType(lpic.getFileContenttype());
 		OutputStream outputStream = response.getOutputStream();
 		FileCopyUtils.copy(inputStream, outputStream);
 		//outputStream.close();
 		//inputStream.close();
	}
	/**
	 * 删除视频
	 * @param learningcenterId
	 * @param courseId
	 * @param ids
	 * @param m
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete/{learningcenterId}/{courseId}")
	@ResponseBody
	public Json delete(@PathVariable String learningcenterId,@PathVariable String courseId, String ids,ModelMap m) throws Exception{
		Json json = new Json();
		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			learningcenterVideoService.delete(id[i]);
   		}
		
		json.setSuccess(true);
		json.setMsg("删除成功");
		//更新早教中心的显示图片数量
		learningcenterService.updateBySql(" set video_count=video_count-"+id.length+" where id='"+learningcenterId+"'");
		//更新早教中心的显示图片数量
		learningcenterCourseService.updateBySql(" set video_count=video_count-"+id.length+" where id='"+courseId+"'");
		
		return json;
	}
	
	
	/**
	 * 下载使用压缩的方式
     * <b>功能：</b>打包下载原文条目数据<br>
     * <b>作者：</b>zhy<br>
     * <b>日期：</b> 2015-9-29 <br>
	 * @throws Exception 
	 * @throws Exception 
     */
	@RequestMapping("/originalzip/{learningcenterId}")
    //@ResponseBody
    public void originalzip(@PathVariable String learningcenterId,String ids,HttpServletResponse response,ModelMap m) throws Exception {
		byte buffer[] = new byte[512];
		response.setContentType("application/x-zip-compressed");
		OutputStream outputStream = response.getOutputStream();
	    ZipOutputStream zipFile = new ZipOutputStream(outputStream);
        try {
        	Map<String, Object> tableinfo = new HashMap<String, Object>();
        	//System.out.println(orglist.size());
        	 String rdids = "'" + StringUtils.replace(ids, ",", "','") + "'";
        	List<YztLearningcenterVideo> orglist=  learningcenterVideoService.selectByIds(rdids);
        	for(YztLearningcenterVideo org : orglist) {
        		 //inputStream=null;
        		InputStream inputStream = getApplicationContext().getResource(org.getFilePath()).getInputStream();
        		ZipEntry zipEntry = new ZipEntry(org.getFileName());
		        zipFile.putNextEntry(zipEntry);
		        int length=0;
		        while((length=inputStream.read(buffer)) != -1) {
		        	zipFile.write(buffer,0,length);
		        }
		        zipFile.closeEntry();
			}
		   
		    zipFile.close();
		    outputStream.flush();
		    outputStream.close();
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
       // return json;
	}
	
}
