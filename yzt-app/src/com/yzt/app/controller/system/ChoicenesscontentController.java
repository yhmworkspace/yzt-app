package com.yzt.app.controller.system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
//import org.json.simple.JSONObject;
import com.alibaba.fastjson.JSONObject;
import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztChoicenesscontent;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.obj.YztChoicenesscontentObj;
import com.yzt.app.utils.web.CommonUtil;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;
import com.yzt.app.utils.web.YztWEB;

@Controller
@RequestMapping("/system/choicenesscontent")
public class ChoicenesscontentController extends BaseController {

	@RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
	public String index(ModelMap m) {
		return "system/jxnr/jxnrlist";
	}

	@RequestMapping("/listjson")
	@ResponseBody
	public EasyuiDataGridJson listjson(YztChoicenesscontent choicenesscontent,
			EasyuiDataGrid dg, ModelMap m) {
		return choicenesscontentService.datagrid(choicenesscontent, dg);
	}

	@RequestMapping("/add")
	public String add(ModelMap m) {
		m.put("DicList", dictionaryService.listDictionaryByPid("3"));

		return "system/jxnr/jxnrinfo";
	}

	@RequestMapping("/add/{id}")
	public String add(@PathVariable String id, ModelMap m) {
		m.put("archive_id", id);
		m.put("DicList", dictionaryService.listDictionaryByPid("3"));
		m.put("jxnr", choicenesscontentService.selectByKey(id));
		return "system/jxnr/jxnrinfo";
	}

	/*
	 * @RequestMapping("/list")
	 * 
	 * @ResponseBody public EasyuiDataGridJson choicenessList(EasyuiDataGrid
	 * req){ return choicenesscontentService.searchList(req); }
	 */
	// 一条数据
	@RequestMapping("/infojson/{id}")
	@ResponseBody
	public YztChoicenesscontent infojson(@PathVariable String id, ModelMap m) {
		return choicenesscontentService.selectByKey(id);
	}

	/**
	 * 返回精选详情(yhm)
	 * 根据id(精选内容的id)
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping("/listjson/{id}")
	@ResponseBody
	public YztChoicenesscontentObj listjson(@PathVariable String id,
			ModelMap m, @RequestParam("userId") String yztUserId) {

		YztChoicenesscontent yc = choicenesscontentService.selectByKey(id);

		String jsonString = JSON.toJSONString(yc);
		YztChoicenesscontentObj obj = JSON.parseObject(jsonString,
				YztChoicenesscontentObj.class);
		// 查询数据库得到flag
		int flag = choicenesscontentCollectService.collectedFlag(yztUserId,
				id);
		// 0是未收藏 1是已收藏
		obj.setChoicenesscontentFlag(flag);

		return obj;

	}

	/*
	 * //手机接口
	 * 
	 * @RequestMapping("/interfacelist")
	 * 
	 * @ResponseBody public ResultBase interfaceList(){ return
	 * choicenesscontentService.getList(); }
	 */
	@RequestMapping("/getChoicenessconten")
	@ResponseBody
	public Json getChoicenessconten(String id) {
		Json json = new Json();
		json.setObj(choicenesscontentService.selectByKey(id));
		json.setSuccess(true);
		return json;

	}

	/**
	 * 修改页面提交表单 action="system/choicenesscontent/save"
	 * 
	 * @param yc
	 * @param m
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public String save(YztChoicenesscontent yc, ModelMap m,
			HttpServletRequest request) {
		Json json = new Json();
		int flag = 0;
		// 修改之前先判断是否
		String introduction = yc.getIntroduction();
		if (CommonUtil.notEmpty(yc.getId())) {
			// 不为空的时候是修改
			yc.setModfiytime(new Date());
			// 写入流
			try {
				saveHtmlUrl(request, yc);
			} catch (IOException e) {
				e.printStackTrace();
			}
			flag = choicenesscontentService.updateNotNull(yc);
		} else {
			// 为空的时候添加
			try {
				saveHtmlUrl(request, yc);
			} catch (IOException e) {
				e.printStackTrace();
			}
			yc.setId(GUIDGener.getGUID());
			yc.setCreatetime(new Date());
			yc.setPicCount(0);
			yc.setViewCount(1);
			flag = choicenesscontentService.save(yc);
		}
		return "redirect:view/" + yc.getId();
	}

	@RequestMapping("/view/{id}")
	public String view(@PathVariable String id, ModelMap m) {
		// m.put("DicList",dictionaryService.listDictionaryByPid("1"));
		YztChoicenesscontent yc = choicenesscontentService.selectByKey(id);
		m.put("archive_id", id);
		m.put("jxnr", yc);
		m.put("introduction", YztWEB.HttpRetrieve(yc.getIntroductionHtmlUrl()));

		return "system/jxnr/jxnrview";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, ModelMap m) {
		YztChoicenesscontent yc = choicenesscontentService.selectByKey(id);
		m.put("DicList", dictionaryService.listDictionaryByPid("3"));
		m.put("archive_id", id);
		m.put("jxnr", yc);
		m.put("introduction", YztWEB.HttpRetrieve(yc.getIntroductionHtmlUrl()));
		return "system/jxnr/jxnrinfo";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String ids, ModelMap m) throws ParseException {
		Json json = new Json();
		// String rdids = "'" + StringUtils.replace(ids, ",", "','") + "'";
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			choicenesscontentService.delete(id[i]);
		}
		json.setSuccess(true);
		json.setMsg("删除成功");
		return json;
	}

	@RequestMapping("/info")
	public String info() {
		return "system/jxnr/jxnrinfo";
	}

	// 获取分类
	@RequestMapping("/getedu_type")
	@ResponseBody
	public Json getEduType() throws Exception {
		return null;// dictionaryService.listDictionaryByPid("3");
	}

	// 编辑器上传图片
	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject upload(@RequestParam("imgFile") MultipartFile file,
			HttpServletRequest request, ModelMap m) throws IOException {

		// String filetype =
		// StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());

		String realRootPath = getServletContext().getResource("/").toString()
				+ "upload/choicenesscontent/" + ymd + "/";
		YztUtil.writeFile(file.getInputStream(), realRootPath,
				file.getOriginalFilename());

		/**/
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		// System.out.println("getContextPath==="+
		// basePath+"upload/user/headimg/"+user.getId()+"."+filetype);
		String picurl = basePath + "upload/choicenesscontent/" + ymd + "/"
				+ file.getOriginalFilename();
		// user.setPicurl(picurl);

		JSONObject json = new JSONObject();
		json.put("error", 0);
		json.put("url", picurl);
		return json;
	}

	/*
	 * 设置权重
	 */
	@RequestMapping("/setweight")
	@ResponseBody
	public Json setweight(int is_weight, String ids, ModelMap m)
			throws Exception {
		Json json = new Json();

		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			choicenesscontentService.updateBySql(" set weight=" + is_weight
					+ " where id='" + id[i] + "'");
		}
		json.setSuccess(true);
		json.setMsg("设置成功");
		return json;
	}

	/**
	 * 将用户提交的简介写入html文件中， 并将文件的url设置进的htmlurl属性中
	 * 
	 * @param request
	 * @param choicenesscontent
	 * @throws IOException
	 */
	private void saveHtmlUrl(HttpServletRequest request,
			YztChoicenesscontent choicenesscontent) throws IOException {
		// 保存简介的html
		String introduction = choicenesscontent.getIntroduction();

		String htmlStr = "<!DOCTYPE html><head><meta charset=\"UTF-8\"><title>内容简介</title></head><body>"
				+ introduction + "</body></html>";

		/* String htmlStr = choicenesscontent.getIntroduction(); */
		// 返回简介html 要存在服务器上的绝对路径
		String path = "upload/choicenesscontent/" + choicenesscontent.getId()
				+ ".html";
		String realRootPath = request.getServletContext().getRealPath("/")
				+ path;
		File file = new File(realRootPath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(htmlStr);
		// 拼成一个url路径
		String htmlurl = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/" + path;

		choicenesscontent.setIntroductionHtmlUrl(htmlurl);
		bw.close();
	}

}
