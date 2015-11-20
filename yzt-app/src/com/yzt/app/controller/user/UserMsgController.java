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
import com.yzt.app.model.YztMessage;
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserMessage;
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
@RequestMapping("/user/message")
public class UserMsgController extends BaseController {
	
	//用户下的消息
	@RequestMapping("/html/{user_id}")
	public String baby(@PathVariable String user_id,ModelMap m){
		m.put("userId", user_id);
		return "user/message/msglist";
	}
	
	@RequestMapping("/listjson/{userId}")
	@ResponseBody
	public EasyuiDataGridJson listjson(@PathVariable String userId,YztUserMessage  usermsg, EasyuiDataGrid dg, ModelMap m){
		usermsg.setUserId(userId);
		return usermessageService.datagrid(usermsg, dg);
	}
	
	/*
	 * 用户读消息
	 */
	@RequestMapping("read/{userId}/{messageId}")
	public Json read(@PathVariable String userId,@PathVariable String messageId,ModelMap m) throws ParseException{
		Json json = new Json();
		YztUserMessage msg = usermessageService.selectByKey(messageId);
		if(msg ==null){
			YztMessage message = messageService.selectByKey(messageId);
			if(message !=null){
				msg.setUserId(userId);
				msg.setId(messageId);
				msg.setTitle(message.getTitle());
				msg.setContent(message.getContent());
				msg.setLinkurl(message.getLinkurl());
				msg.setCreatetime(new Date());
				msg.setIsRead(1);
				usermessageService.save(msg);
				json.setSuccess(true);
				json.setMsg("读消息成功");
				json.setObj(msg);
			}else{
				json.setSuccess(false);
				json.setMsg("无此消息");
			}
		}else{
			json.setSuccess(true);
			json.setMsg("消息已读");
		}
		
		return json;
	}
		
	
}
