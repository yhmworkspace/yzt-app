package com.yzt.app.service.impl;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yzt.app.model.YztChoicenesscontent;
import com.yzt.app.service.ChoicenesscontentService;
import com.yzt.app.utils.web.CommonUtil;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.FieldFilter;
import com.yzt.app.utils.web.Json;
import com.yzt.app.utils.web.ResultBase;

@Service("choicenesscontentService")
public class ChoicenesscontentServiceImpl extends BaseServiceImpl <YztChoicenesscontent> implements ChoicenesscontentService {

	@Override
	public EasyuiDataGridJson datagrid(YztChoicenesscontent choicenesscontent, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztChoicenesscontent.class);
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
        
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}
	/*
	@Override
	public EasyuiDataGridJson searchList(EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztChoicenesscontent.class);
        Example.Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("isdelete",0);
        if (CommonUtil.notEmpty((String)dg.getOthers().getString("maintitle"))) {
        	  criteria.andEqualTo("maintitle", (String)dg.getOthers().getString("maintitle"));
		}
        if (CommonUtil.notEmpty((String)dg.getOthers().getString("weight"))) {
        	 criteria.andEqualTo("weight", (String)dg.getOthers().getString("weight"));
		}
        listjson.setTotal( selectCountByExample(example));
        //分页查询
        PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}
	
	public ResultBase getList(){
		ResultBase res = new ResultBase();
		Example example = new Example(YztChoicenesscontent.class);
        Example.Criteria criteria = example.createCriteria();
        List<YztChoicenesscontent> list = selectByExample(example);
        res.setObj(list);
        res.setResult(ResultBase.RESULT_SUCC);;
        res.setMessage("操作成功");;
        return res;
	}

	@Override
	public YztChoicenesscontent detail(String id) {
        YztChoicenesscontent cho = new YztChoicenesscontent();
        cho.setId(id);
		return selectByKey(cho);
	}

	
	@Override
	@Transactional
	public Json delete(String id) {
		Json json = new Json();
		YztChoicenesscontent cho = new YztChoicenesscontent();
		cho = selectByKey(id);
		cho.setIsdelete(1);
		int success = updateAll(cho);
		if (success == 1) {
			json.setSuccess(true);
		}
		return json;
	}
	*/

}
