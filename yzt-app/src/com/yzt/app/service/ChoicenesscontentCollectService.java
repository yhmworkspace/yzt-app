package com.yzt.app.service;

import com.yzt.app.model.YztChoicenesscontentCollect;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

/**
 * 2015/11/9新建（于） 用于记录用户所收藏的精选内容记录
 * 
 * @author Administrator
 *
 */
public interface ChoicenesscontentCollectService extends
		BaseService<YztChoicenesscontentCollect> {
	/**
	 * 根据用户id获取收藏精选的记录
	 * 
	 * @param userId
	 * @return
	 */
	int collectCount(String userId);

	/**
	 * 用户点添加精选
	 * 
	 * @param yztUserId
	 * @param yztChoicenesscontentId
	 * @return
	 */
	int collect(String yztUserId, String yztChoicenesscontentId);

	/**
	 * 用户收藏的精选数量
	 * 
	 * @param yztUserId
	 * @param yztChoicenesscontentId
	 * @return
	 */
	int collectedCount(String yztUserId,String yztChoicenesscontentId);

	/**
	 * 精选内容id查询用户列表
	 * 
	 * @param yztChoicenesscontentId
	 * @param dg
	 * @return
	 */
	EasyuiDataGridJson getChicenesscontentCollectByChoicenesscontentId(
			String yztChoicenesscontentId, EasyuiDataGrid dg);

	/**
	 * 用户id查询均精选列表
	 * 
	 * @param yztUserId
	 * @param dg
	 * @return
	 */
	EasyuiDataGridJson getCollectByUserId(String yztUserId, EasyuiDataGrid dg);

	/**
	 * 取消收藏 根据 中间表的ids修改相对应的精选
	 * 
	 * @param ids
	 * @return
	 */
	int unCollect(String ids);

	/**
	 * 取消收藏（不用）
	 * 
	 * @param yztUserId
	 * @param choicenesscontentId
	 * @return
	 */
	// int unCollect(String yztUserId,String yztChoicenesscontentId);
	/**
	 * 判断是否被收藏
	 * 
	 * @param yztUserId
	 * @param yztChoicenesscontentId
	 * @return
	 */
	int collectedFlag(String yztUserId, String yztChoicenesscontentId);

}
