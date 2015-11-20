package com.yzt.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztFeedback;
import com.yzt.app.service.BabyService;
import com.yzt.app.service.FeedbackService;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.id.UUID;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.FieldFilter;
import com.yzt.app.utils.web.Json;

@Service("feedbackService")
public class FeedbackServiceImpl extends BaseServiceImpl<YztFeedback> implements FeedbackService {
	@Override
	public EasyuiDataGridJson datagrid(YztFeedback fb, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		Example example = new Example(YztFeedback.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("yztUserId", fb.getYztUserId());
		
		 //查询总数
	    listjson.setTotal( selectCountByExample(example));
	    //分页查询
	    PageHelper.startPage(dg.getPage(), dg.getRows());
	    //return selectByExample(example);
	   // List<YztLearningcenter>
	   
		listjson.setRows(selectByExample(example));
		return listjson;
	}

	
}
