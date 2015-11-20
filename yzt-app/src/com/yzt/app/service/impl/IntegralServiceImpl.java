package com.yzt.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yzt.app.mapper.YztIntegralDetailMapper;
import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztCommodity;
import com.yzt.app.model.YztIntegralDetail;
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

@Service("integralService")
public class IntegralServiceImpl extends BaseServiceImpl<YztIntegralDetail> implements IntegralService {
	@Autowired
	YztIntegralDetailMapper mapper;
	
	@Override
	public EasyuiDataGridJson datagrid(YztIntegralDetail integral, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		Example example = new Example(YztIntegralDetail.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("yztUserId",integral.getYztUserId());
		if(integral.getStatus()==0 || integral.getStatus()==1){
			criteria.andEqualTo("status",integral.getStatus());
		}
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
	   
		listjson.setRows(selectByExample(example));
		return listjson;
	}

	@Override
	public boolean isTaskComplete(String userId,String taskId, int type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String todate = sdf.format(new Date());
		Example example = new Example(YztIntegralDetail.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("yztUserId",userId);
		criteria.andEqualTo("taskId",taskId);
		if(type==1){
			criteria.andEqualTo("contentDatetime", todate);
		}
		int num = selectCountByExample(example);
		if(num>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isCommodityComplete(String userId, YztCommodity commodity) {
		Example example = new Example(YztIntegralDetail.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("yztUserId",userId);
		criteria.andEqualTo("taskId",commodity.getId());
		
		int num = selectCountByExample(example);
		//System.out.println("num=="+num);
		if(num>0){
			//单次且存在  =已经兑换过了
			if(commodity.getIsrepetition().intValue()==0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}

}
