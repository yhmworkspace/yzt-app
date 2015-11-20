package com.yzt.app.controller.commodity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztCommodity;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;
/**
 * 糖果商城
 * @author zhy
 *
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController {
	
	@RequestMapping(value={"", "/"},method = RequestMethod.GET)
	public String commodity(ModelMap m){
		
		return "commodity/commoditylist";
	}
	
	@RequestMapping("listjson")
	@ResponseBody
	public EasyuiDataGridJson listjson(YztCommodity commodity, EasyuiDataGrid dg, ModelMap m){
		//baby.setYztUserId(userId);
		
		return commodityService.datagrid(commodity, dg);
	}
	/*
	 * 商品详情
	 */
	@RequestMapping("detail/{commodityId}")
	@ResponseBody
	public YztCommodity detail(@PathVariable String commodityId,  ModelMap m){
		
		return commodityService.selectByKey(commodityId);
	}
	
	/*
	 * 商品保存
	 */
	@RequestMapping("save")
	public void save(YztCommodity commodity,@RequestParam("imgFile") MultipartFile file,HttpServletRequest request,ModelMap m) throws ParseException, IllegalStateException, IOException{
		
		
		if(commodity.getId() !=null && !commodity.getId().isEmpty()){//修改
			if(!file.isEmpty()){
				String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
				
				String realRootPath = getServletContext().getResource("/").toString()+"upload/commodity/";
				YztUtil.writeFile(file.getInputStream(), realRootPath, commodity.getId()+"."+filetype);
			
				/**/
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				//System.out.println("getContextPath==="+ basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
				String picurl = basePath+"upload/commodity/"+commodity.getId()+"."+filetype;
				commodity.setPicurl(picurl);
			}
			commodity.setStatus(1);
			commodityService.updateNotNull(commodity);
		}else{//新增
			commodity.setId(GUIDGener.getGUID());
			
			commodity.setCreatetime(new Date());
			
			if(!file.isEmpty()){
				String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
				
				String realRootPath = getServletContext().getResource("/").toString()+"upload/commodity/";
				YztUtil.writeFile(file.getInputStream(), realRootPath, commodity.getId()+"."+filetype);
			
				/**/
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				//System.out.println("getContextPath==="+ basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
				String picurl = basePath+"upload/commodity/"+commodity.getId()+"."+filetype;
				commodity.setPicurl(picurl);
			}
			commodity.setStatus(1);
			commodity.setIsSellCount(0);
			commodityService.save(commodity);
		}
	}
		
	
	//删除
	@RequestMapping("/delete")
	public Json delete(String ids,ModelMap m) throws ParseException{
		 Json json = new Json();
		//String rdids = "'" + StringUtils.replace(ids, ",", "','") + "'";
   		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			commodityService.delete(id[i]);
   		}
   		json.setSuccess(true);
   		json.setMsg("删除成功");
   		return json;
	}
}
