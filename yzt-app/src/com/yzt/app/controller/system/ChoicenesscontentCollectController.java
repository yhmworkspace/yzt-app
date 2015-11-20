package com.yzt.app.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzt.app.controller.BaseController;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

/**
 * 新建（于）
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/choicenesscontent")
public class ChoicenesscontentCollectController extends BaseController {
	
	//精选内容被收藏  记录数
	@RequestMapping("/listjson/id")
	public String collectedCount(String yztUserId,String yztChoicenesscontentId){
		choicenesscontentCollectService.collectedCount(yztUserId, yztChoicenesscontentId);
		return "system/jxnr/jxnrlist";
	}
	
	//精选被那些用户收藏列表
	@ResponseBody
	@RequestMapping("/show/{yztChoicenesscontentId}")
	public EasyuiDataGridJson collectedList(@PathVariable String yztChoicenesscontentId,EasyuiDataGrid dg){
		 EasyuiDataGridJson easyuiDataGridJson = choicenesscontentCollectService.getChicenesscontentCollectByChoicenesscontentId(yztChoicenesscontentId, dg);
		 return easyuiDataGridJson;
	}
	
	//转入collect.html
	@RequestMapping("/collected/html/{yztChoicenesscontentId}")
	public String choicenesscontentList(@PathVariable String yztChoicenesscontentId,ModelMap m){
		m.put("yztChoicenesscontentId", yztChoicenesscontentId);
		return "system/collected/collected";
	}

}
