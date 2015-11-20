package com.yzt.app.mapper;

import com.yzt.app.model.YztChoicenesscontent;
import com.yzt.app.utils.mymapper.YztMapper;
/**
 * 用于精选内容信息
 * @author Administrator
 *
 */
public interface YztChoicenesscontentMapper extends YztMapper<YztChoicenesscontent> {

	YztChoicenesscontent getById(String yztChoicenesscontentId );
	
}