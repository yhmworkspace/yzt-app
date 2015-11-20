package com.yzt.app.service;

import com.yzt.app.model.YztUserMessage;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface UserMessageService extends BaseService<YztUserMessage> {
	public EasyuiDataGridJson datagrid(YztUserMessage msg,EasyuiDataGrid dg);
}
