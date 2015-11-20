package com.yzt.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

public interface BabyService extends BaseService<YztBaby> {
	public EasyuiDataGridJson datagrid(YztBaby baby,EasyuiDataGrid dg);
	
	public List<YztBaby> selectByUserId(String userId);
	
}
