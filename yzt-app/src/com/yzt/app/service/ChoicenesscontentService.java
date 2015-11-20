package com.yzt.app.service;

import com.yzt.app.model.YztChoicenesscontent;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;
import com.yzt.app.utils.web.ResultBase;

public interface ChoicenesscontentService extends BaseService<YztChoicenesscontent>{
	public EasyuiDataGridJson datagrid(YztChoicenesscontent choicenesscontent,EasyuiDataGrid dg);
	/*
	public EasyuiDataGridJson searchList(EasyuiDataGrid dg);
	public YztChoicenesscontent detail(String id);
	public Json delete(String id);
	public ResultBase getList();
	*/
}
