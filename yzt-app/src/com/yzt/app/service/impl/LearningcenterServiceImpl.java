package com.yzt.app.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yzt.app.mapper.YztLearningcenterMapper;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.service.LearningcenterService;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.compare.LearningcenterComparator;
import com.yzt.app.utils.compare.LearningcenterComparatorComment;
import com.yzt.app.utils.compare.LearningcenterComparatorCommentCount;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.FieldFilter;

@Service("learningcenterService")
public class LearningcenterServiceImpl extends BaseServiceImpl<YztLearningcenter> implements LearningcenterService{
	@Autowired
    protected YztLearningcenterMapper mapper;
	
	@Override
	public EasyuiDataGridJson datagrid(YztLearningcenter yztLearningcenter, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztLearningcenter.class);
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
		listjson.setRows(selectByExample(example));
        
		return listjson;
	}

	@Override
	public EasyuiDataGridJson learningList(String keyword,YztLearningcenter yztLearningcenter, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("city",yztLearningcenter.getCity());
		
		List<Map<String, Object>> num = mapper.learningListCount(map);
		
		map.put("limitstart", ((dg.getPage()-1<0) ? 0:dg.getPage()-1) * dg.getRows());
		map.put("limitrows", dg.getRows());
		//limit ${limitstart},${limitrows}
		
		listjson.setTotal(num.size());
		//mapper.learningList(map);
		List<YztLearningcenter> list = mapper.learningList(map);
		if(yztLearningcenter.getLongitude() !=null && yztLearningcenter.getLatitude() !=null){
			if(!yztLearningcenter.getLongitude().isEmpty() && !yztLearningcenter.getLatitude().isEmpty()){
				double lng1 =  Double.valueOf(yztLearningcenter.getLongitude()).doubleValue();
				double lat1 =  Double.valueOf(yztLearningcenter.getLatitude()).doubleValue();
				List<YztLearningcenter> list2 = new ArrayList<YztLearningcenter>();
				for(YztLearningcenter lt :list){
					double lng2 =  Double.valueOf((lt.getLongitude()==null ||lt.getLongitude().isEmpty())? "116":lt.getLongitude()).doubleValue();
					double lat2 =  Double.valueOf((lt.getLatitude()==null ||lt.getLatitude().isEmpty()) ? "40.00":lt.getLatitude()).doubleValue();
					lt.setDistance(new Double(YztUtil.GetDistance(lng1, lat1, lng2, lat2)).intValue());
					list2.add(lt);
				}
				Comparator<YztLearningcenter> compare = new LearningcenterComparator();
				Collections.sort(list2, compare);
				//System.out.println("====2");
				//return list2;
				List<Map<String,Object>> list3 = new ArrayList<Map<String,Object>>();
				for(YztLearningcenter lt :list2){
					Map<String,Object> mp = new HashMap<String,Object>();
					mp.put("id", lt.getId());
					mp.put("name", lt.getName());
					mp.put("distance", lt.getDistance());
					mp.put("comments", lt.getComments());
					mp.put("commentsCount", lt.getCommentsCount());
					mp.put("businissCircle", lt.getBusinissCircle());
					mp.put("longitude", lt.getLongitude());
					mp.put("latitude", lt.getLatitude());
					
					mp.put("picCount", lt.getPicCount());
					mp.put("videoCount", lt.getVideoCount());
					mp.put("commodityCount", lt.getCommentsCount());
					mp.put("consultPhone", lt.getConsultPhone());
					mp.put("contactPhone", lt.getContactPhone());
					mp.put("logoUrl", lt.getLogoUrl());
					list3.add(mp);
				}
				listjson.setRows(list3);
			}else{
				List<Map<String,Object>> list3 = new ArrayList<Map<String,Object>>();
				for(YztLearningcenter lt :list){
					Map<String,Object> mp = new HashMap<String,Object>();
					mp.put("id", lt.getId());
					mp.put("name", lt.getName());
					mp.put("distance", lt.getDistance());
					mp.put("comments", lt.getComments());
					mp.put("commentsCount", lt.getCommentsCount());
					mp.put("businissCircle", lt.getBusinissCircle());
					mp.put("longitude", lt.getLongitude());
					mp.put("latitude", lt.getLatitude());
					mp.put("picCount", lt.getPicCount());
					mp.put("videoCount", lt.getVideoCount());
					mp.put("commodityCount", lt.getCommentsCount());
					mp.put("consultPhone", lt.getConsultPhone());
					mp.put("contactPhone", lt.getContactPhone());
					mp.put("logoUrl", lt.getLogoUrl());
					list3.add(mp);
				}
				listjson.setRows(list3);
				//listjson.setRows(list);
			}
		}else{
			//System.out.println("===2=2");
			List<Map<String,Object>> list3 = new ArrayList<Map<String,Object>>();
			for(YztLearningcenter lt :list){
				Map<String,Object> mp = new HashMap<String,Object>();
				mp.put("id", lt.getId());
				mp.put("name", lt.getName());
				mp.put("distance", lt.getDistance());
				mp.put("comments", lt.getComments());
				mp.put("commentsCount", lt.getCommentsCount());
				mp.put("businissCircle", lt.getBusinissCircle());
				mp.put("longitude", lt.getLongitude());
				mp.put("latitude", lt.getLatitude());
				mp.put("picCount", lt.getPicCount());
				mp.put("videoCount", lt.getVideoCount());
				mp.put("commodityCount", lt.getCommentsCount());
				mp.put("consultPhone", lt.getConsultPhone());
				mp.put("contactPhone", lt.getContactPhone());
				mp.put("logoUrl", lt.getLogoUrl());
				list3.add(mp);
			}
			listjson.setRows(list3);
			//listjson.setRows(list);
		}
		
		 
		
		 return listjson;
	}

	
	@Override
	public EasyuiDataGridJson learningList(int scope,String age,YztLearningcenter yztLearningcenter, EasyuiDataGrid dg) {
		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("keyword", keyword);
		map.put("city",yztLearningcenter.getCity());
		map.put("eduType", yztLearningcenter.getEduType());
		
		if(age !=null && !age.isEmpty()){
			String[] ages = age.split("-");
			map.put("min_age", ages[0]);
			map.put("max_age", ages[1]);
		}
		int totalRows = mapper.learningListSelectCount(map);
		int pageRecorders = dg.getRows();//每页显示条数,默认20
		int page = dg.getPage();//当前页
		//总页数
		int totalPages= 0;
		
		
		int pageEndRow=0;
		int pageStartRow =0;
		
		
		System.out.println();
		//int distance = yztLearningcenter.getDistance()==null ? 5 : yztLearningcenter.getDistance();
		
		List<YztLearningcenter> list = mapper.learningListSelect(map);
		System.out.println(totalRows+"="+list.size());
		if(yztLearningcenter.getLongitude() !=null && yztLearningcenter.getLatitude() !=null){
			if(!yztLearningcenter.getLongitude().isEmpty() && !yztLearningcenter.getLatitude().isEmpty()){
				double lng1 =  Double.valueOf(yztLearningcenter.getLongitude()).doubleValue();
				double lat1 =  Double.valueOf(yztLearningcenter.getLatitude()).doubleValue();
				List<YztLearningcenter> list2 = new ArrayList<YztLearningcenter>();
				for(YztLearningcenter lt :list){
					double lng2 =  Double.valueOf((lt.getLongitude()==null ||lt.getLongitude().isEmpty())? "116":lt.getLongitude()).doubleValue();
					double lat2 =  Double.valueOf((lt.getLatitude()==null ||lt.getLatitude().isEmpty()) ? "40.00":lt.getLatitude()).doubleValue();
					lt.setDistance(new Double(YztUtil.GetDistance(lng1, lat1, lng2, lat2)).intValue());
					if(scope==0){
						list2.add(lt);
					}else if(lt.getDistance()<scope){
						list2.add(lt);
					}
				}
				//根据坐标计算的米数排序。
				
				//评论分
				if(dg.getSort() !=null && dg.getSort().equals("comment")){
					Comparator<YztLearningcenter> compare = new LearningcenterComparatorComment();
					Collections.sort(list2, compare);
				//评论数	
				}if(dg.getSort() !=null && dg.getSort().equals("commentcount")){
					Comparator<YztLearningcenter> compare = new LearningcenterComparatorCommentCount();
					Collections.sort(list2, compare);
				}else{//默认距离
					Comparator<YztLearningcenter> compare = new LearningcenterComparator();
					Collections.sort(list2, compare);
				}
				
				totalRows = list2.size();
				if(totalRows>0){
					if ((totalRows % pageRecorders) == 0) {
			            totalPages = totalRows / pageRecorders;
			        } else {
			            totalPages = totalRows / pageRecorders + 1;
			        }
					if (page * pageRecorders < totalRows) {// 判断是否为最后一页
			            pageEndRow = page * pageRecorders;
			            pageStartRow = pageEndRow - pageRecorders;
			        } else {
			            pageEndRow = totalRows;
			            pageStartRow = pageRecorders * (totalPages - 1);
			        }
				}
				List<Map<String,Object>> list3 = new ArrayList<Map<String,Object>>();
				for(YztLearningcenter lt :list2){
					//list2.su
					Map<String,Object> mp = new HashMap<String,Object>();
					mp.put("id", lt.getId());
					mp.put("name", lt.getName());
					mp.put("distance", lt.getDistance());
					mp.put("comments", lt.getComments());
					mp.put("commentsCount", lt.getCommentsCount());
					mp.put("businissCircle", lt.getBusinissCircle());
					mp.put("longitude", lt.getLongitude());
					mp.put("latitude", lt.getLatitude());
					
					mp.put("picCount", lt.getPicCount());
					mp.put("videoCount", lt.getVideoCount());
					mp.put("commodityCount", lt.getCommentsCount());
					mp.put("consultPhone", lt.getConsultPhone());
					mp.put("contactPhone", lt.getContactPhone());
					mp.put("logoUrl", lt.getLogoUrl());
					list3.add(mp);
				}
				
				listjson.setTotal(totalRows);
				System.out.println(totalRows+"="+ pageStartRow+"=="+ pageEndRow);
				listjson.setRows(list3.subList(pageStartRow, pageEndRow));
			}else{
				//totalRows = list2.size();
				if (page * pageRecorders < totalRows) {// 判断是否为最后一页
		            pageEndRow = page * pageRecorders;
		            pageStartRow = pageEndRow - pageRecorders;
		        } else {
		            pageEndRow = totalRows;
		            pageStartRow = pageRecorders * (totalPages - 1);
		        }
				List<Map<String,Object>> list3 = new ArrayList<Map<String,Object>>();
				for(YztLearningcenter lt :list){
					Map<String,Object> mp = new HashMap<String,Object>();
					mp.put("id", lt.getId());
					mp.put("name", lt.getName());
					mp.put("distance", lt.getDistance());
					mp.put("comments", lt.getComments());
					mp.put("commentsCount", lt.getCommentsCount());
					mp.put("businissCircle", lt.getBusinissCircle());
					mp.put("longitude", lt.getLongitude());
					mp.put("latitude", lt.getLatitude());
					mp.put("picCount", lt.getPicCount());
					mp.put("videoCount", lt.getVideoCount());
					mp.put("commodityCount", lt.getCommentsCount());
					mp.put("consultPhone", lt.getConsultPhone());
					mp.put("contactPhone", lt.getContactPhone());
					mp.put("logoUrl", lt.getLogoUrl());
					list3.add(mp);
				}
				listjson.setRows(list3.subList(pageStartRow, pageEndRow));
				//listjson.setRows(list);
			}
		}else{
			//totalRows = list2.size();
			if ((totalRows % pageRecorders) == 0) {
	            totalPages = totalRows / pageRecorders;
	        } else {
	            totalPages = totalRows / pageRecorders + 1;
	        }
			if (page * pageRecorders < totalRows) {// 判断是否为最后一页
	            pageEndRow = page * pageRecorders;
	            pageStartRow = pageEndRow - pageRecorders;
	        } else {
	            pageEndRow = totalRows;
	            pageStartRow = pageRecorders * (totalPages - 1);
	        }
			List<Map<String,Object>> list3 = new ArrayList<Map<String,Object>>();
			for(YztLearningcenter lt :list){
				Map<String,Object> mp = new HashMap<String,Object>();
				mp.put("id", lt.getId());
				mp.put("name", lt.getName());
				mp.put("distance", lt.getDistance());
				mp.put("comments", lt.getComments());
				mp.put("commentsCount", lt.getCommentsCount());
				mp.put("businissCircle", lt.getBusinissCircle());
				mp.put("longitude", lt.getLongitude());
				mp.put("latitude", lt.getLatitude());
				mp.put("picCount", lt.getPicCount());
				mp.put("videoCount", lt.getVideoCount());
				mp.put("commodityCount", lt.getCommentsCount());
				mp.put("consultPhone", lt.getConsultPhone());
				mp.put("contactPhone", lt.getContactPhone());
				mp.put("logoUrl", lt.getLogoUrl());
				list3.add(mp);
			}
			listjson.setRows(list3.subList(pageStartRow, pageEndRow));
			//listjson.setRows(list);
		}
		
		 
		
		 return listjson;
	}

	

}
