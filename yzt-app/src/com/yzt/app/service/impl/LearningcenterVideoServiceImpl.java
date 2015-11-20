package com.yzt.app.service.impl;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztLearningcenterVideo;
import com.yzt.app.service.LearningcenterVideoService;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

@Service("learningcenterVideoService")
public class LearningcenterVideoServiceImpl extends BaseServiceImpl<YztLearningcenterVideo> implements LearningcenterVideoService{

	@Override
	public EasyuiDataGridJson datagrid( EasyuiDataGrid dg, String learningcenterId) {
	EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztLearningcenterVideo.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("yztLearningcenterId",learningcenterId);
        
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}
	
	@Override
	public EasyuiDataGridJson datagrid( EasyuiDataGrid dg, String learningcenterId, String courseId) {
	EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztLearningcenterVideo.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("yztLearningcenterId",learningcenterId);
        criteria.andEqualTo("courseId",courseId);
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}

	

}
