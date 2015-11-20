package com.yzt.app.service;

import com.yzt.app.model.YztIndexChoicenesscontent;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface IndexChoicenesscontentService extends BaseService<YztIndexChoicenesscontent>{
	//public EasyuiDataGridJson searchList(EasyuiDataGrid req);
	public EasyuiDataGridJson datagrid(YztIndexChoicenesscontent pic,EasyuiDataGrid dg);	
}
