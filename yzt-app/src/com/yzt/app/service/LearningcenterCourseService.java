package com.yzt.app.service;

import com.yzt.app.model.YztLearningcenterCourse;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface LearningcenterCourseService extends BaseService<YztLearningcenterCourse>{
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId);
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId,String courseId);
	
}
