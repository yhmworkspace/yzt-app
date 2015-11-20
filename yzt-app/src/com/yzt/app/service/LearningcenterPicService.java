package com.yzt.app.service;

import java.util.List;
import java.util.Map;

import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.model.YztLearningcenterPic;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface LearningcenterPicService extends BaseService<YztLearningcenterPic>{
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId);
	//属于某个早教中心的图片
	public List<Map<String,Object>> datagrid(String learningcenterId);
	
	//属于某个课程的图片
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId,String courseId);
	
	public List<YztLearningcenterPic> WidthAndHieghtIsNull();
	
}
