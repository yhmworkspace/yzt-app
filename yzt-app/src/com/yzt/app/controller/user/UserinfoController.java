package com.yzt.app.controller.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzt.app.controller.BaseController;

import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/user/userinfo")
public class UserinfoController extends BaseController {

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String index(YztUserinfo user, ModelMap m) {
		return "user/user/userlist";
	}

	@RequestMapping("/listjson")
	@ResponseBody
	public EasyuiDataGridJson listjson(YztUserinfo userinfo, EasyuiDataGrid dg,
			ModelMap m) {

		return userinfoService.datagrid(userinfo, dg);
	}

	/*
	 * 用户详情
	 */
	@RequestMapping("detail/{userId}")
	@ResponseBody
	public YztUserinfo detail(@PathVariable String userId, ModelMap m) {

		return userinfoService.selectByKey(userId);
	}

	// 登录
	@RequestMapping("/login")
	@ResponseBody
	public Json login(YztUserinfo user, ModelMap m) {

		return userinfoService.login(user);
	}

	// 修改密码
	@RequestMapping("/updatepwd")
	@ResponseBody
	public Json updatepwd(YztUserinfo user, ModelMap m,
			HttpServletRequest request) throws ServletRequestBindingException {
		String password_new = ServletRequestUtils.getStringParameter(request,
				"password_new", "");
		String password_re = ServletRequestUtils.getStringParameter(request,
				"password_re", "");
		return userinfoService.updatePWD(user, password_new, password_re);
	}

	// 修改昵称
	@RequestMapping("/update")
	@ResponseBody
	public Json edit(YztUserinfo user, ModelMap m) {
		Json json = new Json();
		int res = userinfoService.updateNotNull(user);
		if (res > 0) {
			json.setSuccess(true);
			json.setMsg("成功");
			json.setObj(user);
		} else {
			json.setSuccess(false);
			json.setMsg("失败");
		}
		// json.setMsg("失败");
		return json;
	}

	// 上传头像
	@RequestMapping("/upload")
	@ResponseBody
	public Json upload(YztUserinfo user,
			@RequestParam("imgFile") MultipartFile file,
			HttpServletRequest request, ModelMap m) throws IOException {
		Json json = new Json();
		// json.setObj(user);
		if (file.isEmpty()) {
			System.out.println("no file");
			json.setSuccess(false);
			json.setMsg("无源文件");
		} else {
			String filetype = StringUtils.substringAfterLast(
					file.getOriginalFilename(), ".");

			String realRootPath = getServletContext().getResource("/")
					.toString() + "upload/user/headimg/";
			YztUtil.writeFile(file.getInputStream(), realRootPath, user.getId()
					+ "." + filetype);

			/**/
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			// System.out.println("getContextPath==="+
			// basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
			String picurl = basePath + "upload/user/headimg/" + user.getId()
					+ "." + filetype;
			user.setPicurl(picurl);
			int res = userinfoService.updateNotNull(user);
			if (res > 0) {
				json.setSuccess(true);
				json.setMsg("成功");
				json.setObj(user);
			} else {
				json.setSuccess(false);
				json.setMsg("失败");
			}
			// json.setMsg("失败");
		}
		return json;
	}

	/*
	 * 找回密码时获取验证码
	 */
	@RequestMapping("/verifycode")
	@ResponseBody
	public Json verifyCode(YztRegistor registor, ModelMap m) {
		return userinfoService.getVerifyCode(registor);
	}

	/**
	 * 找回密码
	 */
	@RequestMapping("/resetpwd")
	@ResponseBody
	public Json resetpwd(YztRegistor registor, HttpServletRequest request,
			ModelMap m) {
		String password_re = ServletRequestUtils.getStringParameter(request,
				"isPassword_re", "");
		Json json = new Json();
		if (registor.getIsMobile() == null || registor.getIsMobile().isEmpty()) {
			json.setSuccess(false);
			json.setMsg("手机号不能为空");
			return json;
		} else if (password_re == null || password_re.isEmpty()
				|| registor.getIsPassword() == null
				|| registor.getIsPassword().isEmpty()) {
			json.setSuccess(false);
			json.setMsg("密码和确认密码不能为空");
			return json;
		} else if (!password_re.equals(registor.getIsPassword())) {
			json.setSuccess(false);
			json.setMsg("密码和确认密码不一致");
			return json;
		} else {

			return userinfoService.resetPWD(registor);
		}
	}


}
