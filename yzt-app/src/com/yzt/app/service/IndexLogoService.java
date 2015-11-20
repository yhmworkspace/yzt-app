package com.yzt.app.service;

import java.util.List;

import com.yzt.app.model.YztDictionary;
import com.yzt.app.model.YztFeedback;
import com.yzt.app.model.YztIndexLogo;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.EasyuiTreeNode;
import com.yzt.app.utils.web.Json;

public interface IndexLogoService extends BaseService<YztIndexLogo>{
	public EasyuiDataGridJson datagrid(YztIndexLogo logo,EasyuiDataGrid dg);
	
	
}
