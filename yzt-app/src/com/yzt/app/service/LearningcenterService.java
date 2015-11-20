package com.yzt.app.service;

import java.util.List;

import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface LearningcenterService extends BaseService<YztLearningcenter>{
	public EasyuiDataGridJson datagrid(YztLearningcenter yztLearningcenter,EasyuiDataGrid dg);
	public EasyuiDataGridJson learningList(String keyword,YztLearningcenter yztLearningcenter,EasyuiDataGrid dg);

	public EasyuiDataGridJson learningList(int scope,String age,YztLearningcenter yztLearningcenter,EasyuiDataGrid dg);

}
