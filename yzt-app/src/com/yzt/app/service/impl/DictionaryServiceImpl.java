package com.yzt.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztDictionary;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.service.DictionaryService;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.EasyuiTreeNode;
import com.yzt.app.utils.web.Json;

@Service("dictionaryService")
public class DictionaryServiceImpl extends BaseServiceImpl<YztDictionary> implements DictionaryService{

	@Override
	public List<EasyuiTreeNode> treeDictionary(String pid) throws Exception {
		List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
		
		Example example = new Example(YztDictionary.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dicPid", pid);
        
		List<YztDictionary> diclist = selectByExample(example);
        for(YztDictionary dic : diclist){
        	 tree.add(addTree(dic, false));
        }
        
       
        //分页查询
        //PageHelper.startPage(page, rows);
        //return selectByExample(example);
        
        return tree;
		
	}
	
	private EasyuiTreeNode addTree(YztDictionary dic, boolean recursive) throws Exception{
        EasyuiTreeNode node = new EasyuiTreeNode();
        node.setId(dic.getId());
        node.setText(dic.getDicName());
        
        Example example = new Example(YztDictionary.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dicPid", dic.getId());
        List<YztDictionary> plist = selectByExample(example);
        if (plist.size()>0 ) {
        	if(dic.getDicName()!=null){
        		//node.setState("open");
        		node.setState("closed");
        		//node.setIconCls("tree-filep");
        	}else{
        		node.setState("closed");
        	}
            List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
            for(YztDictionary sdic : plist){
            	children.add(addTree(sdic, false));
            }
                node.setChildren(children);
        }
        return node;
    }

	@Override
	public EasyuiDataGridJson listDictionaryByPid(String dic_pid, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztDictionary.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("dicPid", dic_pid);
        
        example.setOrderByClause("dicSequence asc");
        
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
        
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}

	@Override
	public List<YztDictionary> listDictionaryByPid(String pid) {
		Json json = new Json();
		Example example = new Example(YztDictionary.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dicPid", pid);
        //加入排序
        example.setOrderByClause("dicSequence asc");
		//List<YztDictionary> diclist = selectByExample(example);
		return selectByExample(example);
	}

	@Override
	public int move(String updn, String dic_pid, String id, int cur_seq) throws Exception {
		
		List<YztDictionary> list = listDictionaryByPid(dic_pid);
		String bef_dicid =null;
		String aft_dicid = null;
		for(YztDictionary dic : list){
			if(dic.getDicSequence().intValue() == cur_seq-1){
				bef_dicid = dic.getId();
			}
			if(dic.getDicSequence().intValue() == cur_seq+1){
				aft_dicid = dic.getId();
			}
        }
		int k = 0;
		if(updn.equals("up")){
			k = updateBySql(" set dic_sequence=dic_sequence-1 where id='"+id+"'");
			if(bef_dicid !=null){
				k = updateBySql(" set dic_sequence=dic_sequence+1 where id='"+bef_dicid+"'");
			}
		}else if(updn.equals("dn")){
			k = updateBySql(" set dic_sequence=dic_sequence+1 where id='"+id+"'");
			if(aft_dicid !=null){
				k = updateBySql(" set dic_sequence=dic_sequence-1 where id='"+aft_dicid+"'");
			}
		}
		return k;
	}

}
