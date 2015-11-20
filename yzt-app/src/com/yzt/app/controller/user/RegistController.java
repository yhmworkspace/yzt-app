package com.yzt.app.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/user/regist")
public class RegistController extends BaseController {
	
	
	@RequestMapping(value={"", "/"},method = RequestMethod.GET)
	public String index(ModelMap m){
		return "user/regist/registlist";
	}
	
	@RequestMapping("/listjson")
	@ResponseBody
	public EasyuiDataGridJson listjson(YztRegistor registor, EasyuiDataGrid dg, ModelMap m){
		
		return registService.datagrid(registor, dg);
	}
	/*
	 * 获取验证码
	 */
	@RequestMapping("/verifycode")
	@ResponseBody
	public Json verifyCode(YztRegistor registor,ModelMap m){
		//String aa = JSON.toJSONString(registService.verifyCode(registor));
		return registService.verifyCode(registor);
		
	}
	
	/*
	 * 注册
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public Json regist(YztRegistor registor,ModelMap m){
		return registService.regist(registor);
	}
	
}
