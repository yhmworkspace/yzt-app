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
import com.yzt.app.model.YztCdkey;
import com.yzt.app.model.YztCommodity;
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/commodity/cdkey")
public class CdkeyController extends BaseController {
	
	@RequestMapping(value={"/html/{commodityId}"},method = RequestMethod.GET)
	public String html(@PathVariable String commodityId, ModelMap m){
		m.put("commodityId", commodityId);
		return "commodity/cdkeylist";
	}
	
	@RequestMapping("listjson/{commodityId}")
	@ResponseBody
	public EasyuiDataGridJson listjson(@PathVariable String commodityId,YztCdkey cdkey, EasyuiDataGrid dg, ModelMap m){
		//baby.setYztUserId(userId);
		cdkey.setYztCommodityId(commodityId);
		return cdkeyService.datagrid(cdkey, dg);
	}
	
	@RequestMapping("save/{commodityId}")
	public void save(@PathVariable String commodityId,YztCdkey cdkey,ModelMap m) throws ParseException, IllegalStateException, IOException{
		
		if(cdkey.getId() !=null && !cdkey.getId().isEmpty()){//修改
			cdkey.setStatus(1);
			cdkeyService.updateNotNull(cdkey);
		}else{//新增
			cdkey.setId(GUIDGener.getGUID());
			cdkey.setYztCommodityId(commodityId);
			cdkey.setCreatetime(new Date());
			cdkey.setStatus(1);
			cdkeyService.save(cdkey);
			YztCommodity commodity = new YztCommodity();
			commodity.setId(commodityId);
			commodity.setIsCount(cdkeyService.selectCountByCommodityId(commodityId));
			commodityService.updateNotNull(commodity);
		}
	}
		
	//上传头像
	@RequestMapping("/upload")
	@ResponseBody
	public Json upload(YztBaby baby, @RequestParam("babyimgFile")MultipartFile file, HttpServletRequest request,ModelMap m) throws IOException{
		Json json = new Json();
		//json.setObj(user);
		if(file.isEmpty()){
			System.out.println("no file");
			json.setSuccess(false);
			json.setMsg("无源文件");
		}else{
			String filetype = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
			
				String realRootPath = getServletContext().getResource("/").toString()+"upload/user/baby/";
				YztUtil.writeFile(file.getInputStream(), realRootPath, baby.getId()+"."+filetype);
			
			/**/
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			//System.out.println("getContextPath==="+ basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
			String picurl = basePath+"upload/user/baby/"+baby.getId()+"."+filetype;
			baby.setPicurl(picurl);
			int res = babyService.updateNotNull(baby);
			if(res>0){
				json.setSuccess(true);
				json.setMsg("成功");
				json.setObj(baby);
			}else{
				json.setSuccess(false);
				json.setMsg("失败");
			}
			//json.setMsg("失败");
		}
		return json;
	}
	
	//删除
	@RequestMapping("/delete")
	public Json delete(String ids,ModelMap m) throws ParseException{
		 Json json = new Json();
		//String rdids = "'" + StringUtils.replace(ids, ",", "','") + "'";
   		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			cdkeyService.delete(id[i]);
   		}
   		json.setSuccess(true);
   		json.setMsg("删除成功");
   		return json;
	}
}
