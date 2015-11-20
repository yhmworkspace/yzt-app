package com.yzt.app.service;

import java.util.List;
import java.util.Map;

import com.yzt.app.model.YztLearningcenterCommentPic;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface LearningcenterCommentPicService extends BaseService<YztLearningcenterCommentPic>{
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId);
	
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId,String courseId);
	public List<Map<String,Object>> piclist(String learningcenterId);
}
