package com.yzt.app.service;

import org.apache.ibatis.annotations.Param;

import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

public interface UserinfoService extends BaseService<YztUserinfo> {
	public EasyuiDataGridJson datagrid(YztUserinfo userinfo,EasyuiDataGrid dg);
	
	public Json login(YztUserinfo userinfo);
	public Json updatePWD(YztUserinfo userinfo,String password_new , String password_re);
	
	public Json getVerifyCode(YztRegistor userinfo);
	public Json resetPWD(YztRegistor registor);
	
	//Integer updateBySql(String sql) throws Exception;
}
