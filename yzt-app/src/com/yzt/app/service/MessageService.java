package com.yzt.app.service;

import com.yzt.app.model.YztMessage;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface MessageService extends BaseService<YztMessage> {
	public EasyuiDataGridJson datagrid(YztMessage msg,EasyuiDataGrid dg);
}
