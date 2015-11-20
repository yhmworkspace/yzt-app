package com.yzt.app.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztDictionary;
import com.yzt.app.utils.web.EasyuiTreeNode;

@Controller
@RequestMapping("/user")
public class UserIndexController extends BaseController {
	
	@RequestMapping(value={"", "/"},method = RequestMethod.GET)
	public String index(ModelMap m){
		return "user/index/index";
	}
	
	@RequestMapping(value={"/index"},method = RequestMethod.GET)
	public String index2(YztDictionary dic,ModelMap m){
		return "user/index/index";
	}
	
}
