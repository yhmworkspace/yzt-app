package com.yzt.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztCommodity;
import com.yzt.app.model.YztIntegralTask;
import com.yzt.app.service.BabyService;
import com.yzt.app.service.CommodityService;
import com.yzt.app.service.IntegralService;
import com.yzt.app.service.TaskService;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.id.UUID;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.FieldFilter;
import com.yzt.app.utils.web.Json;

@Service("taskService")
public class TaskServiceImpl extends BaseServiceImpl<YztIntegralTask> implements TaskService {
	@Autowired
	protected IntegralService integralService;
	
	@Override
	public EasyuiDataGridJson datagrid(YztIntegralTask task, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		Example example = new Example(YztIntegralTask.class);
		Example.Criteria criteria = example.createCriteria();
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
	public List<YztIntegralTask> selectByUserId(String user_id) {
		Example example = new Example(YztIntegralTask.class);
		Example.Criteria criteria = example.createCriteria();
		//criteria.andEqualTo("taskType", tasktype);//1-每日，2-进阶
		
		example.setOrderByClause(" taskType asc");
		//取出所有任务
		List<YztIntegralTask> list = selectByExample(example);
		List<YztIntegralTask> list2 = new ArrayList<YztIntegralTask>();
		
		//每日任务，查询用户当天的任务，与之匹配。
		for(YztIntegralTask task : list){
			if(integralService.isTaskComplete(user_id, task.getId(), task.getTaskType())){
				task.setIscomplete(1);
			}else{
				task.setIscomplete(0);
			}
			list2.add(task);
		}
		
		return list2;
	}

}
