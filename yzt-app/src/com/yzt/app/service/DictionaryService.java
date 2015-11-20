package com.yzt.app.service;

import java.util.List;

import com.yzt.app.model.YztDictionary;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.EasyuiTreeNode;
import com.yzt.app.utils.web.Json;

public interface DictionaryService extends BaseService<YztDictionary>{
	//树结构
	public  List<EasyuiTreeNode> treeDictionary(String id) throws Exception ; 
	
	//分页列表
	public EasyuiDataGridJson listDictionaryByPid(String dic_pid,EasyuiDataGrid dg);
	
	public List<YztDictionary> listDictionaryByPid(String dic_pid);
	
	public int move(String updn, String dic_pid,String id, int cur_seq)  throws Exception;
	
}
