package com.yzt.app.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzt.app.controller.BaseController;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

/**
 * 新建（于）
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserCollectController extends BaseController {

	/**
	 * 用户添加精选
	 * 0是未收藏，其他是收藏
	 * @param yztUserId
	 * @param yztChoicenesscontentId
	 */
	@ResponseBody
	@RequestMapping("/add/{userId}")
	public String addCollect(
			@PathVariable("userId") String yztUserId,
			@RequestParam("yztChoicenesscontentId") String yztChoicenesscontentId) {
		  int addCount = choicenesscontentCollectService.collect(yztUserId,yztChoicenesscontentId);
		  return "{addCount:"+addCount+"}";
	}

	/**
	 * 用户收藏精选的数量
	 * 0是未收藏，其他是收藏
	 * @param yztUserId
	 * @param yztChoicenesscontentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/collect/{yztUserId}")
	public String collectContentCount(@PathVariable String yztUserId,
			String yztChoicenesscontentId) {
		 int collectedCount = choicenesscontentCollectService.collectCount(yztUserId);
		 return "{collectedCount:"+collectedCount+"}";
	}

	/**
	 * 根据userid显示收藏的精选列表
	 * 
	 * @param yztUserId
	 * @param dg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/showlist/{yztUserId}")
	public EasyuiDataGridJson collectList(@PathVariable String yztUserId,
			EasyuiDataGrid dg) {
		return choicenesscontentCollectService
				.getCollectByUserId(yztUserId, dg);
	}

	/**
	 * 取消收藏
	 * 0是取消成功，1是不成功
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del/{userId}")
	public String uncollect(@PathVariable("userId") String userId,
			@RequestParam("ids") String ids) {
		int unCollect = choicenesscontentCollectService.unCollect(ids);
		return "{unCollect:"+unCollect+"}";
	}

	// 转入collect.html,显示查询客户列表
	@RequestMapping("/collect/html/{userId}")
	public String choicenesscontents(@PathVariable String userId, ModelMap m) {
		m.put("userId", userId);
		return "user/collect/collect";
	}

	/*
	 * @RequestMapping(value={"/save/{userId}"},method=RequestMethod.POST)
	 * public void save(@PathVariable String userId,@RequestParam String
	 * yztChoicenesscontentId,ModelMap m) throws ParseException,
	 * IllegalStateException, IOException{
	 * 
	 * if(user.getId() !=null && !user.getId().isEmpty()){ //修改
	 * user.setStatus(1); userinfoService.updateNotNull(user); }else{ //新增
	 * System.out.println("************"); user.setId(GUIDGener.getGUID());
	 * user.setNickname(user.getNickname()); user.setAccount(user.getAccount());
	 * user.setStatus(1); userinfoService.save(user);
	 * 
	 * YztChoicenesscontent choicenesscontent = new YztChoicenesscontent();
	 * choicenesscontent.setId(yztChoicenesscontentId);
	 * choicenesscontent.setMaintitle(choicenesscontent.getMaintitle());
	 * choicenesscontent.setEndTime(choicenesscontent.getEndTime());
	 * choicenesscontent.setStartTime(choicenesscontent.getStartTime());
	 * choicenesscontentService.updateNotNull(choicenesscontent); } }
	 */

}
