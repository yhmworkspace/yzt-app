package com.yzt.app.service;

import com.yzt.app.model.YztLearningcenterCollect;

public interface LearningcenterCollectService extends
		BaseService<YztLearningcenterCollect> {
	// 根据用户id查询所有 早教中心id，并拼串，用逗号隔开
	public String getYztLearningcenterIds(String yztUserId);

	// 根据早教中心id查询所有 用户id，并拼串，用逗号隔开
	public String getYztUseridCollectIds(String yztLearningcenterId);

	// 返回收藏该早教中心的用户数量
	public int getLearningcenterCollectedCount(String yztLearningcenterId);

	// 返回指定用户的收藏数量
	public int getUserCollectCount(String yztUserid);
}
