package com.yzt.app.controller.system;

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

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yzt.app.config.YztConstant;
import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztLearningcenterVideo;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/system/learningcenter/video")
public class LearningcenterVideoController extends BaseController {
	
	@RequestMapping(value={"html/{learningcenterId}"},method = RequestMethod.GET)
	public String index(@PathVariable String learningcenterId, ModelMap m){
		m.put("learningcenterId", learningcenterId);
		return "system/learningcenter/video/index";
	}
	
	/**
	 * 显示视频列表
	 * @param learningcenterId
	 * @param dg
	 * @param m
	 * @return
	 */
	@RequestMapping("/listjson/{learningcenterId}")
	@ResponseBody
	public EasyuiDataGridJson listjson(@PathVariable String learningcenterId,EasyuiDataGrid dg, ModelMap m){
		return learningcenterVideoService.datagrid( dg,learningcenterId);
	}
	
	/**
	 * 保存视频
	 * @param learningcenterId
	 * @param file
	 * @param m
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save/{learningcenterId}")
	@ResponseBody
	public Json save(@PathVariable String learningcenterId,@RequestParam("file")MultipartFile file,ModelMap m) throws Exception{
		Json json = new Json();
		if(file.isEmpty()){
			System.out.println("no file");
			json.setSuccess(false);
			json.setMsg("无源文件");
		}else{
			String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
			String id = GUIDGener.getGUID();
			String filedir=YztConstant.originalPath+"/learningcenter/"+learningcenterId+"/video";
			
			YztUtil.writeFile(file.getInputStream(), filedir, id+"."+filetype);
			YztLearningcenterVideo pic = new YztLearningcenterVideo();
			pic.setId(id);
			pic.setYztLearningcenterId(learningcenterId);
			pic.setFileType(filetype);
			pic.setFilePath("file:"+filedir+"/"+id+"."+filetype);
			pic.setFileName(file.getOriginalFilename());
			pic.setFileContenttype(file.getContentType());
			pic.setCreatetime(new Date());
			learningcenterVideoService.save(pic);
			
			//更新早教中心的显示图片数量
			learningcenterService.updateBySql(" set video_count=video_count+1 where id='"+learningcenterId+"'");
			
			json.setSuccess(true);
			json.setMsg("上传成功");
		}
		
		return json;
	}
	
	@RequestMapping("view/{learningcenterId}/{videoId}")
	public String view(@PathVariable String learningcenterId,@PathVariable String videoId, ModelMap m){
		m.put("learningcenterId", learningcenterId);
		m.put("videoId", videoId);
		return "system/learningcenter/video/videoViewer";
	}
	
	/*
	 * 显示原文，视频和配图
	 * istype  : pic-配图， video-视频
	 */
	@RequestMapping("display/{learningcenterId}/{videoId}/{istype}")      
	public void display(@PathVariable String learningcenterId,@PathVariable String videoId,@PathVariable String istype, HttpServletResponse response,ModelMap m) throws MalformedURLException, IOException{
		
		
		YztLearningcenterVideo video = new YztLearningcenterVideo();
		video.setId(videoId);
		video.setYztLearningcenterId(learningcenterId);
		YztLearningcenterVideo lpic = learningcenterVideoService.selectByKey(video);
		
		//String filepath = StringUtils.substringAfter(lpic.getFilePath(), "file:");
		String filepath = StringUtils.substringBetween(lpic.getFilePath(), "file:",".");
		if(istype.equals("pic")){
			filepath = filepath + ".jpg";
			response.setContentType("image/jpeg");
		}else{
			filepath = filepath + "."+lpic.getFileType();
			response.setContentType(lpic.getFileContenttype());
		}
		InputStream inputStream = YztUtil.getInputStreamFromFilepath("file:"+filepath);
       
 		OutputStream outputStream = response.getOutputStream();
 		FileCopyUtils.copy(inputStream, outputStream);
 		//outputStream.close();
 		//inputStream.close();
	}
	
	@RequestMapping("/delete/{learningcenterId}")
	@ResponseBody
	public Json delete(@PathVariable String learningcenterId, String ids,ModelMap m) throws Exception{
		Json json = new Json();
		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			learningcenterVideoService.delete(id[i]);
   		}
		
		json.setSuccess(true);
		json.setMsg("删除成功");
		//更新早教中心的显示图片数量
		learningcenterService.updateBySql(" set video_count=video_count-"+id.length+" where id='"+learningcenterId+"'");
		
		
		return json;
	}
	
	
	/**
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
	
	/*
	 * 给视频陪图片
	 */
	@RequestMapping("/pic2video/{id}")
	@ResponseBody
	public Json picsave(@PathVariable String id,HttpServletRequest request,ModelMap m) throws Exception{
		Json json = new Json();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("imgFile");
		
		if(file.isEmpty()){
			System.out.println("no file");
			json.setSuccess(false);
			json.setMsg("无源文件");
		}else{
			YztLearningcenterVideo video= learningcenterVideoService.selectByKey(id);
			String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
			String filedir = StringUtils.substringBetween(video.getFilePath(), "file:", id);
			YztUtil.writeFile(file.getInputStream(), filedir, id+"."+filetype);
			
			//更新视频中是否有图片
			learningcenterVideoService.updateBySql(" set ispic=1 where id='"+id+"'");
			
			//更新早教中心的显示图片数量
			//learningcenterService.updateBySql(" set video_count=video_count+1 where id='"+learningcenterId+"'");
			
			json.setSuccess(true);
			json.setMsg("上传成功");
		}
		
		return json;
	}
	/*
	 * 推荐到中心首页tvShow
	 */
	
	@RequestMapping("/tvshow/{learningcenterId}")
	@ResponseBody
	public Json tvshow(@PathVariable String learningcenterId, String videoId,HttpServletRequest request,ModelMap m) throws Exception{
		Json json = new Json();
		/**/
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
   		//system/learningcenter/video/display/${learningcenterId}/${videoId}/video
		String tvPicurl = basePath+"system/learningcenter/video/display/"+learningcenterId+"/"+videoId+"/pic";
		String tvVideourl = basePath+"system/learningcenter/video/display/"+learningcenterId+"/"+videoId+"/video";
		
		json.setSuccess(true);
		json.setMsg("操作成功");
		//更新早教中心的显示图片数量
		learningcenterService.updateBySql(" set tv_picurl='"+tvPicurl+"',tv_videourl='"+tvVideourl+"' where id='"+learningcenterId+"'");
		
		
		return json;
	}
	
}
