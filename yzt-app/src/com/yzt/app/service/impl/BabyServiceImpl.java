package com.yzt.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztBaby;
import com.yzt.app.service.BabyService;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.id.UUID;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.FieldFilter;
import com.yzt.app.utils.web.Json;

@Service("babyService")
public class BabyServiceImpl extends BaseServiceImpl<YztBaby> implements BabyService {
	@Override
	public EasyuiDataGridJson datagrid(YztBaby baby, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		Example example = new Example(YztBaby.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("yztUserId", baby.getYztUserId());
		if(baby.getId() !=null && !baby.getId().isEmpty()){
			criteria.andEqualTo("idd", baby.getId());
		}
		 //查询总数
	    listjson.setTotal( selectCountByExample(example));
	    //分页查询
	    PageHelper.startPage(dg.getPage(), dg.getRows());
	    //return selectByExample(example);
	   // List<YztLearningcenter>
	   
		listjson.setRows(selectByExample(example));
		return listjson;
	}

	@Override
	public List<YztBaby> selectByUserId(String userId) {
		Example example = new Example(YztBaby.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("yztUserId", userId);
		//criteria.andEqualTo("isVerifyCode", registor.getIsVerifyCode());
		//List<YztBaby> list = 
		return selectByExample(example);
	}

	

	
	
	
}
