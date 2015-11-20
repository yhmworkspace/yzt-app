package com.yzt.app.service;

import java.util.List;

import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztCommodity;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

public interface CommodityService extends BaseService<YztCommodity> {
	public EasyuiDataGridJson datagrid(YztCommodity commodity,EasyuiDataGrid dg);
	
	//public List<YztBaby> selectByUserId(String userId);
}
