package com.yzt.app.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.yzt.app.model.YztLearningcenterCollect;
import com.yzt.app.service.LearningcenterCollectService;

@Service("learningcenterCollectService")
public class LearningcenterCollectServiceImpl extends
		BaseServiceImpl<YztLearningcenterCollect> implements
		LearningcenterCollectService {

	/**
	 * 根据用户id查询所有 早教中心id，并拼串，用逗号隔开
	 * @param yztUserId
	 * @return
	 */
	public String getYztLearningcenterIds(String yztUserId){
		String yztLearningcenterIds = "";
		//根据用户id查询所有的 LearningcenterCollect
		Example example = new Example(YztLearningcenterCollect.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("yztUserId", yztUserId);
        //只查询收藏有效的记录
        criteria.andEqualTo("collect",1);
        List<YztLearningcenterCollect> list = selectByExample(example);
		//遍历list将所用早教中心的id进行拼串，用逗号隔开
        for (YztLearningcenterCollect yztLearningcenterCollect : list) {
        	yztLearningcenterIds += yztLearningcenterCollect.getYztLearningcenterId() + ",";
		}
        yztLearningcenterIds = StringUtils.substringBeforeLast(yztLearningcenterIds, ",");
		return yztLearningcenterIds;
	}

	/**
	 * 根据早教中心id查询所有 用户id，并拼串，用逗号隔开
	 * @param yztLearningcenterId
	 * @return
	 */
	public String getYztUseridCollectIds(String yztLearningcenterId){
		String yztUserIds = "";
		//根据早教中心id查询所有 Userinfo
		Example example = new Example(YztLearningcenterCollect.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("yztLearningcenterId", yztLearningcenterId);
        //只查询收藏有效的记录
        criteria.andEqualTo("collect",1);
        List<YztLearningcenterCollect> list = selectByExample(example);
		//遍历list将所用早教中心的id进行拼串，用逗号隔开
        for (YztLearningcenterCollect yztLearningcenterCollect : list) {
        	yztUserIds += yztLearningcenterCollect.getYztUserId() + ",";
		}
        yztUserIds = StringUtils.substringBeforeLast(yztUserIds, ",");
		return yztUserIds;
	}
	
	/**
	 * 返回收藏该早教中心的用户数量
	 * @param yztUserid
	 * @return
	 */
	public int getLearningcenterCollectedCount(String yztLearningcenterId){
		Example example = new Example(YztLearningcenterCollect.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("yztLearningcenterId", yztLearningcenterId);
        //只查询收藏有效的记录
        criteria.andEqualTo("collect",1);
        
		return selectCountByExample(example);
	}
	
	/**
	 * 返回指定用户的收藏数量
	 * @param yztUserid
	 * @return
	 */
	public int getUserCollectCount(String yztUserid){
		Example example = new Example(YztLearningcenterCollect.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("yztUserid", yztUserid);
        //只查询收藏有效的记录
        criteria.andEqualTo("collect",1);
        
		return selectCountByExample(example);
	}

}
