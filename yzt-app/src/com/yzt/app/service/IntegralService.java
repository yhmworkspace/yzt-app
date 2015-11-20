package com.yzt.app.service;

import java.util.List;

import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztCommodity;
import com.yzt.app.model.YztIntegralDetail;
import com.yzt.app.model.YztIntegralTask;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

public interface IntegralService extends BaseService<YztIntegralDetail> {
	public EasyuiDataGridJson datagrid(YztIntegralDetail integral,EasyuiDataGrid dg);
	
	//任务是否已经完成
	public boolean isTaskComplete(String userId, String taskId,int type);
	//商品是否可兑换
	public boolean isCommodityComplete(String userId, YztCommodity commodity);
}
