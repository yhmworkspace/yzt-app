package com.yzt.app.controller.system;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztCommodity;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.model.YztLearningcenterPic;
import com.yzt.app.service.LearningcenterService;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.obj.YztLearningcenterObj;
import com.yzt.app.utils.web.CommonUtil;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/system/learningcenter")
public class LearningcenterController extends BaseController {
	
	@RequestMapping(value={"", "/","/index"},method = RequestMethod.GET)
	public String index(ModelMap m){
		return "system/learningcenter/learningcenterlist";
	}
	
	
	@RequestMapping("/listjson")
	@ResponseBody
	public EasyuiDataGridJson listjson(YztLearningcenter yztLearningcenter, EasyuiDataGrid dg, ModelMap m){
		return learningcenterService.datagrid(yztLearningcenter, dg);
	}
	
	/*
	 * 给手机接口，查询列表。关键词匹配  名称，地址，分类（edu_type），简介
	 * 
	 * param:keyword, city,longitude,latitude
	 * 	scope-范围，eduType-分类，age-年龄，
	 * 
	 * 	page:1，rows:20
	 * sort,order 
	 */
	@RequestMapping("/m/listjson")
	@ResponseBody
	public EasyuiDataGridJson listjson(String keyword,int scope,String age,YztLearningcenter yztLearningcenter, EasyuiDataGrid dg){
		if(keyword !=null && !keyword.isEmpty()){
			return learningcenterService.learningList(keyword,yztLearningcenter, dg); 
		}else{
			if(scope >0){
				scope = scope*1000;
			}
			
			return learningcenterService.learningList(scope,age,yztLearningcenter, dg);
		}
		
	}
	
	//一条数据   手机接口
	@RequestMapping("/m/listjson/{id}")
	@ResponseBody
	public YztLearningcenterObj minfojson(@PathVariable String id, HttpServletRequest request, ModelMap m) throws Exception{
		//YztLearningcenterObj obj = new YztLearningcenterObj();
		//点击量
		learningcenterService.updateBySql("set view_count=view_count+1 where id='"+id+"'");
		YztLearningcenter lc = learningcenterService.selectByKey(id);
		String jsonString = JSON.toJSONString(lc); 
		YztLearningcenterObj obj = JSON.parseObject(jsonString, YztLearningcenterObj.class); 
		//图片
		obj.setPiclist(learningcenterPicService.datagrid(id));
		//评论
		obj.setCommentlist(learningcenterCommentService.commentlist(id,2));
		/**/
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		obj.setHtmlurl(basePath+"introduction/"+id+".html");
		
		return obj;
	}
	
	//一条数据  
	@RequestMapping("/infojson/{id}")
	@ResponseBody
	public YztLearningcenter infojson(@PathVariable String id,  ModelMap m) throws Exception{
		 return  learningcenterService.selectByKey(id);
		
	}
	
	@RequestMapping("/add")
	public String add(ModelMap m){
		m.put("DicList",dictionaryService.listDictionaryByPid("1"));
		return "system/learningcenter/learningcenterinfo";
	}
	
	@RequestMapping("/add/{id}")
	public String add(@PathVariable String id,ModelMap m){
		m.put("archive_id", id);
		m.put("DicList",dictionaryService.listDictionaryByPid("1"));
		return "system/learningcenter/learningcenterinfo";
	}
	
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Json save(YztLearningcenter learningcenter,@RequestParam("imgFile") MultipartFile file,@RequestParam("imgFile2") MultipartFile file2,HttpServletRequest request,ModelMap m) throws ParseException, IllegalStateException, IOException{
		Json json = new Json();
		
		if(learningcenter.getId() !=null && !learningcenter.getId().isEmpty()){//修改
			if(!file.isEmpty()){
				String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
				
				String realRootPath = getServletContext().getResource("/").toString()+"upload/logo/";
				YztUtil.writeFile(file.getInputStream(), realRootPath, learningcenter.getId()+"."+filetype);
			
				/**/
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				//System.out.println("getContextPath==="+ basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
				String logurl = basePath+"upload/logo/"+learningcenter.getId()+"."+filetype;
				learningcenter.setLogoUrl(logurl);
			}
			if(!file2.isEmpty()){
				String filetype = StringUtils.substringAfterLast(file2.getOriginalFilename(), ".");
				
				String realRootPath = getServletContext().getResource("/").toString()+"upload/learningcenterimg/";
				YztUtil.writeFile(file2.getInputStream(), realRootPath, learningcenter.getId()+"."+filetype);
			
				/**/
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				//System.out.println("getContextPath==="+ basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
				String imgurl = basePath+"upload/learningcenterimg/"+learningcenter.getId()+"."+filetype;
				learningcenter.setImgUrl(imgurl);
			}
			//commodity.setStatus(0);
			learningcenter.setModfiytime(new Date());
			int flag = learningcenterService.updateNotNull(learningcenter);
			if(flag>0){
				json.setMsg("修改成功");
				json.setSuccess(true);
				json.setObj(learningcenter);
			}else{
				json.setMsg("修改失败");
				json.setSuccess(false);
			}
				
			
		}else{//新增
			learningcenter.setId(GUIDGener.getGUID());
			
			learningcenter.setCreatetime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat( "MMddHHmm" );
			learningcenter.setYztLearningcenterId(sdf.format(new Date()));
			
			if(!file.isEmpty()){
				String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
				
				String realRootPath = getServletContext().getResource("/").toString()+"upload/commodity/";
				YztUtil.writeFile(file.getInputStream(), realRootPath, learningcenter.getId()+"."+filetype);
			
				/**/
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				//System.out.println("getContextPath==="+ basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
				String picurl = basePath+"upload/logo/"+learningcenter.getId()+"."+filetype;
				learningcenter.setLogoUrl(picurl);
			}
			if(!file2.isEmpty()){
				String filetype = StringUtils.substringAfterLast(file2.getOriginalFilename(), ".");
				
				String realRootPath = getServletContext().getResource("/").toString()+"upload/learningcenterimg/";
				YztUtil.writeFile(file2.getInputStream(), realRootPath, learningcenter.getId()+"."+filetype);
			
				/**/
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				//System.out.println("getContextPath==="+ basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
				String imgurl = basePath+"upload/learningcenterimg/"+learningcenter.getId()+"."+filetype;
				learningcenter.setImgUrl(imgurl);
			}
			learningcenter.setStatus(0);
			learningcenter.setVideoCount(0);
			learningcenter.setPicCount(0);
			learningcenter.setCourseCount(0);
			learningcenter.setViewCount(1);
			int flag = learningcenterService.save(learningcenter);
			if(flag>0){
				json.setMsg("保存成功");
				json.setSuccess(true);
				json.setObj(learningcenter);
			}else{
				json.setMsg("保存失败");
				json.setSuccess(false);
			}
		}
		return json;
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable String id,ModelMap m){
		//m.put("DicList",dictionaryService.listDictionaryByPid("1"));
		m.put("archive_id", id);
		m.put("learingcenter", learningcenterService.selectByKey(id));
		return "system/learningcenter/learningcenterview";
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String ids,ModelMap m) throws ParseException{
		 Json json = new Json();
		//String rdids = "'" + StringUtils.replace(ids, ",", "','") + "'";
   		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			learningcenterService.delete(id[i]);
   		}
   		json.setSuccess(true);
   		json.setMsg("删除成功");
   		return json;
	}
	
	/*
	 * 更改status状态
	 */
	@RequestMapping("/updateStatus/{status}")
	@ResponseBody
	public Json updateStatus(@PathVariable int status,String ids,ModelMap m) throws Exception{
		 Json json = new Json();
		int num = 1;
		if(0 == status){
			num=0;
		}
   		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			learningcenterService.updateBySql(" set status="+num+" where id='"+id[i]+"'");
   		}
   		json.setSuccess(true);
   		json.setMsg("设置成功");
   		return json;
	}
	
	/*
	 * 设置权重
	 */
	@RequestMapping("/setweight")
	@ResponseBody
	public Json setweight(int is_weight,String ids,ModelMap m) throws Exception{
		 Json json = new Json();
		
   		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			learningcenterService.updateBySql(" set weight="+is_weight+" where id='"+id[i]+"'");
   		}
   		json.setSuccess(true);
   		json.setMsg("设置成功");
   		return json;
	}
  
}
