package com.yzt.app.service;


import com.yzt.app.model.YztIndexPic;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface IndexPicService extends BaseService<YztIndexPic>{
	public EasyuiDataGridJson datagrid(YztIndexPic pic,EasyuiDataGrid dg);
	
	
}
