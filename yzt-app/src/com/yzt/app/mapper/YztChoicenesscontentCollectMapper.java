package com.yzt.app.mapper;

import com.yzt.app.model.YztChoicenesscontent;
import com.yzt.app.model.YztChoicenesscontentCollect;
import com.yzt.app.utils.mymapper.YztMapper;
/**
 * 用于记录用户所收藏的精选内容记录   
 * @author Administrator
 *
 */
public interface YztChoicenesscontentCollectMapper extends YztMapper<YztChoicenesscontentCollect> {
	YztChoicenesscontentCollect getById(String id );
}