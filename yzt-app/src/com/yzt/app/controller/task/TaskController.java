package com.yzt.app.controller.task;

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
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {
	
	@RequestMapping(value={"", "/"},method = RequestMethod.GET)
	public String task(ModelMap m){
		
		return "task/tasklist";
	}
	
	@RequestMapping("/listjson")
	@ResponseBody
	public EasyuiDataGridJson listjson(YztIntegralTask task, EasyuiDataGrid dg, ModelMap m){
		return taskService.datagrid(task, dg);
	}
	
	/*
	 * 用户的任务是否完成，添加标记；0-未完成，1-已完成
	 */
	@RequestMapping("/list/{userId}")
	@ResponseBody
	public List<YztIntegralTask> listjson(@PathVariable String userId, EasyuiDataGrid dg, ModelMap m){
		
		return taskService.selectByUserId(userId);
	}
	
	@RequestMapping("/save")
	public void save(YztIntegralTask task,ModelMap m) {
		if(task.getId() !=null && !task.getId().isEmpty()){//修改
			taskService.updateNotNull(task);
		}else{//新增
			task.setId(GUIDGener.getGUID());
			task.setCreatetime(new Date());
			taskService.save(task);
		}
	}
		
	//删除
	@RequestMapping("/delete")
	public Json delete(String ids,ModelMap m) throws ParseException{
		 Json json = new Json();
		//String rdids = "'" + StringUtils.replace(ids, ",", "','") + "'";
   		String[] id = ids.split(",");
   		for(int i =0;i<id.length;i++){
   			taskService.delete(id[i]);
   		}
   		json.setSuccess(true);
   		json.setMsg("删除成功");
   		return json;
	}
}
