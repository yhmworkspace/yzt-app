package com.yzt.app.service.impl;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztChoicenesscontentPic;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.model.YztLearningcenterPic;
import com.yzt.app.service.ChoicenesscontentPicService;
import com.yzt.app.service.LearningcenterPicService;
import com.yzt.app.service.LearningcenterService;
import com.yzt.app.utils.web.CommonUtil;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

@Service("choienesscontentPicService")
public class ChoienesscontentPicServiceImpl extends BaseServiceImpl<YztChoicenesscontentPic> implements ChoicenesscontentPicService{

	@Override
	public EasyuiDataGridJson datagrid( EasyuiDataGrid dg, String learningcenterId) {
	EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztChoicenesscontentPic.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("yztChoicenesscontentId",learningcenterId);
        
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}

	

}
