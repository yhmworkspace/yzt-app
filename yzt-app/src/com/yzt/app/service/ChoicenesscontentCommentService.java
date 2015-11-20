package com.yzt.app.service;

import java.util.List;

import com.yzt.app.model.YztChoicenesscontentComment;
import com.yzt.app.utils.obj.YztLearningcenterCommentObj;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface ChoicenesscontentCommentService extends BaseService<YztChoicenesscontentComment>{
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String choicenesscontentId);
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String learningcenterId,String courseId);
	
	//public List<YztLearningcenterCommentObj> commentlist(String learningcenterId,int num);
	
}
