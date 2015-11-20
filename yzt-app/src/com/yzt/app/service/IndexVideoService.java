package com.yzt.app.service;


import com.yzt.app.model.YztIndexVideo;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface IndexVideoService extends BaseService<YztIndexVideo>{
	public EasyuiDataGridJson datagrid(YztIndexVideo pic,EasyuiDataGrid dg);	
}
