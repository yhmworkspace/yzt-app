package com.yzt.app.controller.system;

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
@RequestMapping("/system")
public class IndexController extends BaseController {
	
	@RequestMapping(value={"", "/", "login"},method = RequestMethod.GET)
	public String login(YztDictionary dic,ModelMap m){
		return "system/login";
	}
	
	@RequestMapping(value={"/index"},method = RequestMethod.GET)
	public String index(YztDictionary dic,ModelMap m){
		return "system/index/index";
	}
	
}
