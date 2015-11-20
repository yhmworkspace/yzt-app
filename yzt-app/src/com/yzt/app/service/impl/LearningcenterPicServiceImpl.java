package com.yzt.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.model.YztLearningcenterPic;
import com.yzt.app.service.LearningcenterPicService;
import com.yzt.app.service.LearningcenterService;
import com.yzt.app.utils.web.CommonUtil;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

@Service("learningcenterPicService")
public class LearningcenterPicServiceImpl extends BaseServiceImpl<YztLearningcenterPic> implements LearningcenterPicService{

	@Override
	public EasyuiDataGridJson datagrid( EasyuiDataGrid dg, String learningcenterId) {
	EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztLearningcenterPic.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("yztLearningcenterId",learningcenterId);
        
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}
	
	@Override
	public EasyuiDataGridJson datagrid( EasyuiDataGrid dg, String learningcenterId,String courseId) {
	EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztLearningcenterPic.class);
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
	public List<Map<String,Object>> datagrid(String learningcenterId) {
		Example example = new Example(YztLearningcenterPic.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("yztLearningcenterId",learningcenterId);
        List<YztLearningcenterPic> lp = selectByExample(example);
        //system/learningcenter/pic/display/${learningcenterId}/${picId}
        List<Map<String,Object>> lp2 = new ArrayList<Map<String,Object>>();
        for(YztLearningcenterPic lp_ : lp){
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("imgurl", "system/learningcenter/pic/display/"+lp_.getYztLearningcenterId()+"/"+lp_.getId());
        	map.put("imgWidth", lp_.getFileWidth());
        	map.put("imgHeight", lp_.getFileHeight());
        	lp2.add(map);
        };
        return  lp2;
		
	}

	@Override
	public List<YztLearningcenterPic> WidthAndHieghtIsNull() {
		Example example = new Example(YztLearningcenterPic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fileWidth",0);
        criteria.andEqualTo("fileHeight",0);
        return selectByExample(example);
	}

	

}
