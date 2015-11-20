package com.yzt.app.service;

import com.yzt.app.model.YztRegistor;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

public interface RegistService extends BaseService<YztRegistor> {
	
	public EasyuiDataGridJson datagrid(YztRegistor registor,EasyuiDataGrid dg);
	
	public Json verifyCode(YztRegistor registor);
	public Json regist(YztRegistor registor);
	
}
