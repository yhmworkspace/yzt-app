package com.yzt.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.utils.mymapper.YztMapper;

public interface YztLearningcenterMapper extends YztMapper<YztLearningcenter> {
	public List<YztLearningcenter> learningList(Map<String,Object> map) ;
	public  List<Map<String,Object>> learningListCount(Map<String,Object> map) ;
	public int learningListSelectCount(Map<String,Object> map) ;
	public  List<YztLearningcenter> learningListSelect(Map<String,Object> map) ;
	
      
}