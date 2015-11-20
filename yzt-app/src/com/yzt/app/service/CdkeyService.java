package com.yzt.app.service;

import java.util.List;

import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztCdkey;
import com.yzt.app.model.YztCommodity;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

public interface CdkeyService extends BaseService<YztCdkey> {
	public EasyuiDataGridJson datagrid(YztCdkey cdkey,EasyuiDataGrid dg);
	
	public int selectCountByCommodityId(String commodityId);
}
