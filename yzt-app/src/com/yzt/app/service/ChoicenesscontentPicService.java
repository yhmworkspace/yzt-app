package com.yzt.app.service;

import com.yzt.app.model.YztChoicenesscontentPic;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.model.YztLearningcenterPic;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

public interface ChoicenesscontentPicService extends BaseService<YztChoicenesscontentPic>{
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg,String choicenesscontentId);
	
}
