package com.yzt.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztFeedback;
import com.yzt.app.model.YztUserMessage;
import com.yzt.app.service.BabyService;
import com.yzt.app.service.FeedbackService;
import com.yzt.app.service.UserMessageService;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.id.UUID;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.FieldFilter;
import com.yzt.app.utils.web.Json;

@Service("usermessageService")
public class UserMessagekServiceImpl extends BaseServiceImpl<YztUserMessage> implements UserMessageService {
	@Override
	public EasyuiDataGridJson datagrid(YztUserMessage msg, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		Example example = new Example(YztUserMessage.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", msg.getUserId());
		
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
