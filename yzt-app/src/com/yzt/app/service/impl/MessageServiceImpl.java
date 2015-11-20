package com.yzt.app.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztIntegralTask;
import com.yzt.app.model.YztMessage;
import com.yzt.app.service.MessageService;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.FieldFilter;

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<YztMessage> implements MessageService {
	@Override
	public EasyuiDataGridJson datagrid(YztMessage msg, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		Example example = new Example(YztMessage.class);
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

}
