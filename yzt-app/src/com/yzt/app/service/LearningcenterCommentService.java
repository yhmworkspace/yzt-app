package com.yzt.app.service;

import java.util.List;

import com.yzt.app.model.YztLearningcenterComment;
import com.yzt.app.model.YztLearningcenterCourse;
import com.yzt.app.utils.obj.YztLearningcenterCommentObj;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface LearningcenterCommentService extends BaseService<YztLearningcenterComment>{
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId);
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId,String courseId);
	
	public List<YztLearningcenterCommentObj> commentlist(String learningcenterId,int num);
	
}
