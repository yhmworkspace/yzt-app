package com.yzt.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztChoicenesscontentComment;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.model.YztLearningcenterComment;
import com.yzt.app.model.YztLearningcenterCourse;
import com.yzt.app.model.YztLearningcenterPic;
import com.yzt.app.service.ChoicenesscontentCommentService;
import com.yzt.app.service.LearningcenterCommentPicService;
import com.yzt.app.service.LearningcenterCommentService;
import com.yzt.app.service.LearningcenterCourseService;
import com.yzt.app.service.LearningcenterPicService;
import com.yzt.app.service.LearningcenterService;
import com.yzt.app.utils.obj.YztLearningcenterCommentObj;
import com.yzt.app.utils.obj.YztLearningcenterObj;
import com.yzt.app.utils.web.CommonUtil;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.FieldFilter;

@Service("choicenesscontentCommentService")
public class ChoicenesscontentCommentServiceImpl extends BaseServiceImpl<YztChoicenesscontentComment> implements ChoicenesscontentCommentService{
	@Autowired
	protected LearningcenterCommentPicService learningcenterCommentPicService;
	
	@Override
	public EasyuiDataGridJson datagrid( EasyuiDataGrid dg, String choicenesscontentId) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztChoicenesscontentComment.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("yztChoicenesscontentId",choicenesscontentId);
        //criteria.andEqualTo("parentid","0");
        
        String filterRules = dg.getFilterRules();
	    if(filterRules !=null && !"[]".equals(filterRules)){ 
			List<FieldFilter> filtersr =  JSON.parseArray(filterRules,FieldFilter.class);
			for(FieldFilter ft : filtersr){
	        	//postparam.append(" and "+ft.getField()+" like '%"+ft.getValue().trim().replace(" ", "%")+"%'  ");
	        	criteria.andLike(ft.getField(), "%" + ft.getValue().trim().replaceAll(" ", "%") + "%");
			}
		}
	  //加入排序
        if(dg.getSort() != null && dg.getOrder() !=null){
        	String orderby="";
        	String[] order = dg.getSort().split(",");
        	String[] sort = dg.getOrder().split(",");
        	for(int i =0;i<order.length;i++){
        		orderby += order[i]+" "+sort[i]+",";
        	}
        	orderby = StringUtils.substringBeforeLast(orderby, ",");
        	 example.setOrderByClause(orderby);
        }else{
        	example.setOrderByClause(" createtime desc ");
        }
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}

	@Override
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, String learningcenterId, String courseId) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztLearningcenterComment.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("yztLearningcenterId",learningcenterId);
        criteria.andEqualTo("parentid",courseId);
        
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}
	/*
	@Override
	public List<YztLearningcenterCommentObj> commentlist(String learningcenterId,int num) {
		
		Example example = new Example(YztLearningcenterComment.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("yztLearningcenterId",learningcenterId);
        example.setOrderByClause(" createtime desc limit 2");
        //PageHelper.startPage(1, num);
        List<YztChoicenesscontentComment> lc = selectByExample(example);
        List<YztLearningcenterCommentObj> lcc = new ArrayList<YztLearningcenterCommentObj>();
        for(YztLearningcenterComment lc_c : lc){
        	String jsonString = JSON.toJSONString(lc_c);
        	YztLearningcenterCommentObj obj = JSON.parseObject(jsonString, YztLearningcenterCommentObj.class); 
        	obj.setPiclist(learningcenterCommentPicService.piclist(lc_c.getId()));
        	lcc.add(obj);
        }
        
       
		//return lcc;
	}
 */
	

}
