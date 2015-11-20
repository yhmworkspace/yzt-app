package com.yzt.app.controller.user;

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
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/user/baby")
public class BabyController extends BaseController {

	// 用户下的baby
	@RequestMapping("/html/{user_id}")
	public String baby(@PathVariable String user_id, ModelMap m) {
		m.put("userId", user_id);
		// List<YztBaby>
		return "user/baby/babylist";
	}

	@RequestMapping("/listjson/{userId}")
	@ResponseBody
	public EasyuiDataGridJson listjson(@PathVariable String userId,
			YztBaby baby, EasyuiDataGrid dg, ModelMap m) {
		baby.setYztUserId(userId);
		return babyService.datagrid(baby, dg);
	}

	@RequestMapping("/listjson/{userId}/{babyId}")
	@ResponseBody
	public EasyuiDataGridJson listjsonOne(@PathVariable String userId,
			@PathVariable String babyId, YztBaby baby, EasyuiDataGrid dg,
			ModelMap m) {
		baby.setYztUserId(userId);
		baby.setId(babyId);
		return babyService.datagrid(baby, dg);
	}

	@RequestMapping("{user_id}/list")
	@ResponseBody
	public List<YztBaby> listjson(@PathVariable String user_id,
			EasyuiDataGrid dg, ModelMap m) {

		return babyService.selectByUserId(user_id);
	}

	@RequestMapping("save/{userId}")
	public void save(@PathVariable String userId, YztBaby baby,
			@RequestParam("babyimgFile") MultipartFile file,
			HttpServletRequest request, ModelMap m) throws ParseException,
			IllegalStateException, IOException {
		if (baby.getId() != null && !baby.getId().isEmpty()) {// 修改
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(baby.getBirthday());
			// 生肖
			baby.setZodiac(YztUtil.date2Zodica(date));
			// 星座
			baby.setConstellation(YztUtil.date2Constellation(date));
			babyService.updateNotNull(baby);

		} else {// 新增
			baby.setId(GUIDGener.getGUID());
			baby.setYztUserId(userId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(baby.getBirthday());
			// 生肖
			baby.setZodiac(YztUtil.date2Zodica(date));
			// 星座
			baby.setConstellation(YztUtil.date2Constellation(date));
			baby.setCreatetime(new Date());

			String filetype = StringUtils.substringAfterLast(
					file.getOriginalFilename(), ".");

			String realRootPath = getServletContext().getResource("/")
					.toString() + "upload/user/baby/";
			YztUtil.writeFile(file.getInputStream(), realRootPath, baby.getId()
					+ "." + filetype);

			/**/
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			// System.out.println("getContextPath==="+
			// basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
			String picurl = basePath + "upload/user/baby/" + baby.getId() + "."
					+ filetype;
			baby.setPicurl(picurl);

			babyService.save(baby);
			List<YztBaby> babylist = babyService.selectByUserId(userId);
			YztUserinfo user = new YztUserinfo();
			user.setId(userId);
			user.setBabyCount(babylist.size());
			userinfoService.updateNotNull(user);
		}
	}

	// 上传头像
	@RequestMapping("/upload")
	@ResponseBody
	public Json upload(YztBaby baby,
			@RequestParam("babyimgFile") MultipartFile file,
			HttpServletRequest request, ModelMap m) throws IOException {
		Json json = new Json();
		// json.setObj(user);
		if (file.isEmpty()) {
			// System.out.println("no file");
			json.setSuccess(false);
			json.setMsg("无源文件");
		} else {
			String filetype = StringUtils.substringAfterLast(
					file.getOriginalFilename(), ".");

			String realRootPath = getServletContext().getResource("/")
					.toString() + "upload/user/baby/";
			YztUtil.writeFile(file.getInputStream(), realRootPath, baby.getId()
					+ "." + filetype);

			/**/
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			// System.out.println("getContextPath==="+
			// basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
			String picurl = basePath + "upload/user/baby/" + baby.getId() + "."
					+ filetype;
			baby.setPicurl(picurl);
			int res = babyService.updateNotNull(baby);
			if (res > 0) {
				json.setSuccess(true);
				json.setMsg("成功");
				json.setObj(baby);
			} else {
				json.setSuccess(false);
				json.setMsg("失败");
			}
			// json.setMsg("失败");
		}
		return json;
	}

	// 删除宝宝
	@RequestMapping("delete/{userId}/{babyId}")
	public void delete(@PathVariable String userId,
			@PathVariable String babyId, ModelMap m) throws ParseException {
		babyService.delete(babyId);
		List<YztBaby> babylist = babyService.selectByUserId(userId);
		YztUserinfo user = new YztUserinfo();
		user.setId(userId);
		user.setBabyCount(babylist.size());
		userinfoService.updateNotNull(user);
	}
}
