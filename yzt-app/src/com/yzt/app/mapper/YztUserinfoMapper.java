package com.yzt.app.mapper;


import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.mymapper.YztMapper;

public interface YztUserinfoMapper extends YztMapper<YztUserinfo> {

	YztUserinfo getById(String yztUserId);
	
}