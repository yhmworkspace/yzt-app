package com.yzt.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztFeedback;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

public interface FeedbackService extends BaseService<YztFeedback> {
	public EasyuiDataGridJson datagrid(YztFeedback fb,EasyuiDataGrid dg);
	
	
}
