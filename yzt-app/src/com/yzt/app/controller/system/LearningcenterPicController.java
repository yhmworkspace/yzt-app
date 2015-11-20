package com.yzt.app.controller.system;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

import tk.mybatis.mapper.entity.Example;

import com.yzt.app.config.YztConstant;
import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.model.YztLearningcenterPic;
import com.yzt.app.service.LearningcenterService;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.CommonUtil;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/system/learningcenter/pic")
public class LearningcenterPicController extends BaseController {
	
	@RequestMapping(value={"html/{learningcenterId}"},method = RequestMethod.GET)
	public String index(@PathVariable String learningcenterId, ModelMap m){
		m.put("learningcenterId", learningcenterId);
		return "system/learningcenter/pic/index";
	}
	
	
	@RequestMapping("/listjson/{learningcenterId}")
	@ResponseBody
	public EasyuiDataGridJson listjson(@PathVariable String learningcenterId,EasyuiDataGrid dg, ModelMap m){
		return learningcenterPicService.datagrid( dg,learningcenterId);
	}
	
	
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
			String filedir=YztConstant.originalPath+"/learningcenter/"+learningcenterId+"/pic";
			
			YztUtil.writeFile(file.getInputStream(), filedir, id+"."+filetype);
			YztLearningcenterPic pic = new YztLearningcenterPic();
			pic.setId(id);
			pic.setYztLearningcenterId(learningcenterId);
			pic.setFileType(filetype);
			pic.setFilePath("file:"+filedir+"/"+id+"."+filetype);
			pic.setFileName(file.getOriginalFilename());
			
			pic.setFileSize(new Double(file.getSize()/1000).intValue());
			//is = new FileInputStream(file);
			BufferedImage  src = javax.imageio.ImageIO.read(file.getInputStream());
			pic.setFileWidth(src.getWidth());
			pic.setFileHeight(src.getHeight());
			
			pic.setFileContenttype(file.getContentType());
			pic.setCreatetime(new Date());
			learningcenterPicService.save(pic);
			
			//更新早教中心的显示图片数量
			learningcenterService.updateBySql(" set pic_count=pic_count+1 where id='"+learningcenterId+"'");
			
			json.setSuccess(true);
			json.setMsg("上传成功");
		}
		
		return json;
	}
	
	@RequestMapping(value={"view/{learningcenterId}/{picId}"},method = RequestMethod.GET)
	public String view(@PathVariable String learningcenterId,@PathVariable String picId, ModelMap m){
		m.put("learningcenterId", learningcenterId);
		m.put("picId", picId);
		return "system/learningcenter/pic/imgViewer";
	}
	
	@RequestMapping("display/{learningcenterId}/{picId}")
	@ResponseBody
	public void display(@PathVariable String learningcenterId,@PathVariable String picId, HttpServletResponse response,ModelMap m) throws MalformedURLException, IOException{
		
		
		YztLearningcenterPic pic = new YztLearningcenterPic();
		pic.setId(picId);
		pic.setYztLearningcenterId(learningcenterId);
		YztLearningcenterPic lpic = learningcenterPicService.selectByKey(pic);
		
		String filepath = StringUtils.substringAfter(lpic.getFilePath(), "file:");
		System.out.println(filepath);
		File file = new File(filepath);
		//return "system/learningcenter/pic/imgViewer";
		
		InputStream inputStream = YztUtil.getInputStreamFromFilepath("file:"+filepath);
         
        response.setContentType(lpic.getFileContenttype());
 		OutputStream outputStream = response.getOutputStream();
 		FileCopyUtils.copy(inputStream, outputStream);
 		outputStream.close();
 		inputStream.close();
	}
	
	@RequestMapping("/delete/{learningcenterId}")
	@ResponseBody
	public Json delete(@PathVariable String learningcenterId, String ids,ModelMap m) throws Exception{
		Json json = new Json();
		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			learningcenterPicService.delete(id[i]);
   		}
		
		json.setSuccess(true);
		json.setMsg("删除成功");
		//更新早教中心的显示图片数量
		learningcenterService.updateBySql(" set pic_count=pic_count-"+id.length+" where id='"+learningcenterId+"'");
		
		
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
    @ResponseBody
    public void originalzip(@PathVariable String learningcenterId,String ids,HttpServletResponse response,ModelMap m) throws Exception {
		byte buffer[] = new byte[512];
		response.setContentType("application/x-zip-compressed");
		OutputStream outputStream = response.getOutputStream();
	    ZipOutputStream zipFile = new ZipOutputStream(outputStream);
	    
        try {
        	Map<String, Object> tableinfo = new HashMap<String, Object>();
        	//System.out.println(orglist.size());
        	 String rdids = "'" + StringUtils.replace(ids, ",", "','") + "'";
        	List<YztLearningcenterPic> orglist=  learningcenterPicService.selectByIds(rdids);
        	for(YztLearningcenterPic org : orglist) {
        		InputStream inputStream = YztUtil.getInputStreamFromFilepath(org.getFilePath());
                
        		ZipEntry zipEntry = new ZipEntry(org.getFileName());
		        zipFile.putNextEntry(zipEntry);
		        int length=0;
		        while((length=inputStream.read(buffer)) != -1) {
		        	zipFile.write(buffer,0,length);
		        }
		        zipFile.closeEntry();
		        inputStream.close();
			}
		   
		    zipFile.close();
		    outputStream.flush();
		    outputStream.close();
		    
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
       // return json;
	}
	
	@RequestMapping("/setWH")
	@ResponseBody
	public void setWH() throws MalformedURLException, IOException{
		
		
		List<YztLearningcenterPic> lpic = learningcenterPicService.WidthAndHieghtIsNull();
		for(YztLearningcenterPic pic : lpic){
			
			String filepath = StringUtils.substringAfter(pic.getFilePath(), "file:");
			System.out.println(filepath);
			File file = new File(filepath);
			//return "system/learningcenter/pic/imgViewer";
			if(file.exists()){
				InputStream inputStream = YztUtil.getInputStreamFromFilepath("file:"+filepath);
				pic.setFileSize(new Double(file.length()/1000).intValue());
			
				BufferedImage  src = javax.imageio.ImageIO.read(inputStream);
				pic.setFileWidth(src.getWidth());
				pic.setFileHeight(src.getHeight());
				inputStream.close();
				learningcenterPicService.updateNotNull(pic);
			}else{
				//pic.setFilePath("");
				//learningcenterPicService.updateNotNull(pic);
			}
		}
        
	}
	
}
