package com.yzt.app.service;

import java.util.List;

import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztCommodity;
import com.yzt.app.model.YztIntegralTask;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

public interface TaskService extends BaseService<YztIntegralTask> {
	public EasyuiDataGridJson datagrid(YztIntegralTask task,EasyuiDataGrid dg);

	public List<YztIntegralTask> selectByUserId( String user_id);
}
