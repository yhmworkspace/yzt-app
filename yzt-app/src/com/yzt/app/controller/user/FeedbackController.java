package com.yzt.app.controller.user;

import java.io.IOException;
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
import com.yzt.app.model.YztFeedback;
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;
/**
 * @author zhy
 *意见反馈
 */
@Controller
@RequestMapping("/user/feedback")
public class FeedbackController extends BaseController {
	
	
	
	
	@RequestMapping("/html/{user_id}")
	public String baby(@PathVariable String user_id,ModelMap m){
		m.put("userId", user_id);
		return "user/feedback/feedbacklist";
	}
	
	@RequestMapping("/listjson/{userId}")
	@ResponseBody
	public EasyuiDataGridJson listjson(@PathVariable String userId,YztFeedback  fb, EasyuiDataGrid dg, ModelMap m){
		fb.setYztUserId(userId);
		return feedbackService.datagrid(fb, dg);
	}
	
	
	
	@RequestMapping("save/{userId}")
	public Json save(@PathVariable String userId,YztFeedback fb,ModelMap m) throws ParseException{
		Json json = new Json();
		if(fb.getId() !=null && !fb.getId().isEmpty()){//修改
			//SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
			//Date date = sdf.parse( baby.getBirthday());
			fb.setResponsetime(new Date());
			feedbackService.updateNotNull(fb);
		}else{//新增
			fb.setId(GUIDGener.getGUID());
			fb.setYztUserId(userId);
			fb.setStatus(0);
			fb.setCreatetime(new Date());
			feedbackService.save(fb);
			
			json.setSuccess(true);
			json.setMsg("保存成功");
			json.setObj(fb);
		}
		return json;
	}
		
	
	@RequestMapping("delete/{userId}/{feedbackId}")
	public void delete(@PathVariable String userId,@PathVariable String feedbackId,ModelMap m) throws ParseException{
			feedbackService.delete(feedbackId);
	}
}
