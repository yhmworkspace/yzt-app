package com.yzt.app.controller.system;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztIndexChoicenesscontent;
import com.yzt.app.model.YztIndexVideo;
import com.yzt.app.service.IndexChoicenesscontentService;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.CommonUtil;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/system/indexchoicenesscontent")
public class IndexChoicenesscontentController extends BaseController{

	@RequestMapping(value={"","/"},method = RequestMethod.GET)
	public String index( ModelMap m){
		return "system/indexjx/indexjxlist";
	}
	
	
	@RequestMapping("/listjson")
	@ResponseBody
	public EasyuiDataGridJson listjson(YztIndexChoicenesscontent video,EasyuiDataGrid dg, ModelMap m){
		return indexChoicenesscontentService.datagrid(video, dg);
	}
	
	@RequestMapping("add")
	public String add( ModelMap m){
		return "system/indexjx/indexjxinfo";
	}
	
	@RequestMapping("add/{id}")
	public String add2( ModelMap m){
		return "system/indexjx/indexjxinfo";
	}
	
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Json save(YztIndexChoicenesscontent logo,@RequestParam("imgFile") MultipartFile file,HttpServletRequest request,ModelMap m) throws ParseException, IllegalStateException, IOException{
		Json json = new Json();
		
		if(logo.getId() !=null && !logo.getId().isEmpty()){//修改
			if(!file.isEmpty()){
				String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
				
				String realRootPath = getServletContext().getResource("/").toString()+"upload/indexjx/";
				YztUtil.writeFile(file.getInputStream(), realRootPath, logo.getId()+"."+filetype);
			
				/**/
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				//System.out.println("getContextPath==="+ basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
				String logurl = basePath+"upload/indexjx/"+logo.getId()+"."+filetype;
				logo.setImgUrl(logurl);
			}
			//commodity.setStatus(0);
			int flag = indexChoicenesscontentService.updateNotNull(logo);
			if(flag>0){
				json.setMsg("修改成功");
				json.setSuccess(true);
				//json.setObj(learningcenter);
			}else{
				json.setMsg("修改失败");
				json.setSuccess(false);
			}
				
			
		}else{//新增
			logo.setId(GUIDGener.getGUID());
			
			logo.setCreatetime(new Date());
			
			if(!file.isEmpty()){
				String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
				
				String realRootPath = getServletContext().getResource("/").toString()+"upload/indexjx/";
				YztUtil.writeFile(file.getInputStream(), realRootPath, logo.getId()+"."+filetype);
			
				/**/
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				//System.out.println("getContextPath==="+ basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
				String picurl = basePath+"upload/indexjx/"+logo.getId()+"."+filetype;
				logo.setImgUrl(picurl);
			}
			logo.setStatus(0);
			int flag = indexChoicenesscontentService.save(logo);
			if(flag>0){
				json.setMsg("保存成功");
				json.setSuccess(true);
				//json.setObj(learningcenter);
			}else{
				json.setMsg("保存失败");
				json.setSuccess(false);
			}
		}
		return json;
	}
	
	@RequestMapping(value={"view/{id}"},method = RequestMethod.GET)
	public String view(@PathVariable String learningcenterId,@PathVariable String picId, ModelMap m){
		m.put("learningcenterId", learningcenterId);
		m.put("picId", picId);
		return "system/learningcenter/pic/imgViewer";
	}
	
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete( String ids,ModelMap m) throws Exception{
		Json json = new Json();
		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			indexChoicenesscontentService.delete(id[i]);
   		}
		
		json.setSuccess(true);
		json.setMsg("删除成功");
		//更新早教中心的显示图片数量
		//learningcenterService.updateBySql(" set pic_count=pic_count-"+id.length+" where id='"+learningcenterId+"'");
		
		
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
   			indexChoicenesscontentService.updateBySql(" set status="+num+" where id='"+id[i]+"'");
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
   			indexChoicenesscontentService.updateBySql(" set weight="+is_weight+" where id='"+id[i]+"'");
   		}
   		json.setSuccess(true);
   		json.setMsg("设置成功");
   		return json;
	}
}
