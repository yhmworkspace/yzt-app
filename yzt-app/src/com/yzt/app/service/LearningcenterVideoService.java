package com.yzt.app.service;

import com.yzt.app.model.YztLearningcenterVideo;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface LearningcenterVideoService extends BaseService<YztLearningcenterVideo>{
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId);
	
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId,String courseId);
	
}
