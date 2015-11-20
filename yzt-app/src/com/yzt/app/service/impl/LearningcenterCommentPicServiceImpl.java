package com.yzt.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztLearningcenterCommentPic;
import com.yzt.app.service.LearningcenterCommentPicService;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

@Service("learningcenterCommentPicService")
public class LearningcenterCommentPicServiceImpl extends BaseServiceImpl<YztLearningcenterCommentPic> implements LearningcenterCommentPicService{

	@Override
	public EasyuiDataGridJson datagrid( EasyuiDataGrid dg,String commentId) {
	EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztLearningcenterCommentPic.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("commentId",commentId);
        
        
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}
	
	@Override
	public EasyuiDataGridJson datagrid( EasyuiDataGrid dg, String learningcenterId,String courseId) {
	EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztLearningcenterCommentPic.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("yztLearningcenterId",learningcenterId);
        criteria.andEqualTo("courseId",courseId);
        
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}

	@Override
	public List<Map<String,Object>> piclist(String commentId) {
		Example example = new Example(YztLearningcenterCommentPic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("commentId",commentId);
        
        List<Map<String,Object>> lsp = new ArrayList<Map<String,Object>>();
        List<YztLearningcenterCommentPic> lcp = selectByExample(example);
        for(YztLearningcenterCommentPic pic : lcp){
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("imgurl", "/system/learningcenter/comment/display/"+commentId+"/"+pic.getId());
        	lsp.add(map);
        }
        
		return lsp;
	}

	

}
