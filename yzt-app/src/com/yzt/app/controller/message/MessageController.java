package com.yzt.app.controller.message;

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
import com.yzt.app.model.YztIntegralTask;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.model.YztMessage;
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {
	
	@RequestMapping(value={"", "/"},method = RequestMethod.GET)
	public String task(ModelMap m){
		
		return "message/msglist";
	}
	
	@RequestMapping("/listjson")
	@ResponseBody
	public EasyuiDataGridJson listjson(YztMessage msg, EasyuiDataGrid dg, ModelMap m){
		return messageService.datagrid(msg, dg);
	}
	
	@RequestMapping("{user_id}/list")
	@ResponseBody
	public List<YztBaby> listjson(@PathVariable String user_id, EasyuiDataGrid dg, ModelMap m){
		
		return babyService.selectByUserId(user_id);
	}
	
	@RequestMapping("/save")
	public void save(YztMessage msg,ModelMap m) {
		if(msg.getId() !=null && !msg.getId().isEmpty()){//修改
			messageService.updateNotNull(msg);
		}else{//新增
			msg.setId(GUIDGener.getGUID());
			msg.setCreatetime(new Date());
			messageService.save(msg);
		}
	}
		
	//删除
	@RequestMapping("/delete")
	public Json delete(String ids,ModelMap m) throws ParseException{
		 Json json = new Json();
		//String rdids = "'" + StringUtils.replace(ids, ",", "','") + "'";
   		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			messageService.delete(id[i]);
   		}
   		json.setSuccess(true);
   		json.setMsg("删除成功");
   		return json;
	}
}
