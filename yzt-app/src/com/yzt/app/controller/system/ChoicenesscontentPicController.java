package com.yzt.app.controller.system;

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
import com.yzt.app.model.YztChoicenesscontentPic;
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
@RequestMapping("/system/choicenesscontent/pic")
public class ChoicenesscontentPicController extends BaseController {
	

	@RequestMapping(value={"html/{choicenesscontentId}"},method = RequestMethod.GET)
	public String index(@PathVariable String choicenesscontentId, ModelMap m){
		m.put("choicenesscontentId", choicenesscontentId);
		return "system/jxnr/pic/index";
	}
	
	
	@RequestMapping("/listjson/{choicenesscontentId}")
	@ResponseBody
	public EasyuiDataGridJson listjson(@PathVariable String choicenesscontentId,EasyuiDataGrid dg, ModelMap m){
		return choicenesscontentPicService.datagrid( dg,choicenesscontentId);
	}
	
	
	@RequestMapping("/save/{choicenesscontentId}")
	@ResponseBody
	public Json save(@PathVariable String choicenesscontentId,@RequestParam("file")MultipartFile file,ModelMap m) throws Exception{
		Json json = new Json();
		if(file.isEmpty()){
			System.out.println("no file");
			json.setSuccess(false);
			json.setMsg("无源文件");
		}else{
			String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
			String id = GUIDGener.getGUID();
			String filedir=YztConstant.originalPath+"/choicenesscontent/"+choicenesscontentId+"/pic";
			
			YztUtil.writeFile(file.getInputStream(), filedir, id+"."+filetype);
			YztChoicenesscontentPic pic = new YztChoicenesscontentPic();
			pic.setId(id);
			pic.setYztChoicenesscontentId(choicenesscontentId);
			pic.setFileType(filetype);
			pic.setFilePath("file:"+filedir+"/"+id+"."+filetype);
			pic.setFileName(file.getOriginalFilename());
			pic.setFileContenttype(file.getContentType());
			pic.setCreatetime(new Date());
			choicenesscontentPicService.save(pic);
			
			//更新早教中心的显示图片数量
			choicenesscontentService.updateBySql(" set pic_count=pic_count+1 where id='"+choicenesscontentId+"'");
			
			json.setSuccess(true);
			json.setMsg("上传成功");
		}
		
		return json;
	}
	
	@RequestMapping(value={"view/{choicenesscontentId}/{picId}"},method = RequestMethod.GET)
	public String view(@PathVariable String choicenesscontentId,@PathVariable String picId, ModelMap m){
		m.put("choicenesscontentId", choicenesscontentId);
		m.put("picId", picId);
		return "system/jxnr/pic/imgViewer";
	}
	
	@RequestMapping("/display/{choicenesscontentId}/{picId}")
	@ResponseBody
	public void display(@PathVariable String choicenesscontentId,@PathVariable String picId, HttpServletResponse response,ModelMap m) throws MalformedURLException, IOException{
		
		
		YztChoicenesscontentPic pic = new YztChoicenesscontentPic();
		pic.setId(picId);
		pic.setYztChoicenesscontentId(choicenesscontentId);
		YztChoicenesscontentPic lpic = choicenesscontentPicService.selectByKey(pic);
		
		String filepath = StringUtils.substringAfter(lpic.getFilePath(), "file:");
		System.out.println(filepath);
		//File file = new File(filepath);
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
   			choicenesscontentPicService.delete(id[i]);
   		}
		
		json.setSuccess(true);
		json.setMsg("删除成功");
		//更新早教中心的显示图片数量
		choicenesscontentService.updateBySql(" set pic_count=pic_count-"+id.length+" where id='"+learningcenterId+"'");
		
		
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
        	List<YztChoicenesscontentPic> orglist=  choicenesscontentPicService.selectByIds(rdids);
        	for(YztChoicenesscontentPic org : orglist) {
        		InputStream inputStream = YztUtil.getInputStreamFromFilepath(org.getFilePath());
                
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