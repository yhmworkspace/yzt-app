package com.yzt.app.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.yzt.app.mapper.YztChoicenesscontentMapper;
import com.yzt.app.mapper.YztUserinfoMapper;
import com.yzt.app.model.YztChoicenesscontent;
import com.yzt.app.model.YztChoicenesscontentCollect;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.service.ChoicenesscontentCollectService;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

/**
 * 2015/11/9新建（于） 用于记录用户所收藏的精选内容记录
 * 
 * @author Administrator
 *
 */
@Service("ChoicenesscontentCollectService")
public class ChoicenesscontentCollectServiceImpl extends
		BaseServiceImpl<YztChoicenesscontentCollect> implements
		ChoicenesscontentCollectService {

	@Autowired
	private YztChoicenesscontentMapper yztChoicenesscontentMapper;
	@Autowired
	private YztUserinfoMapper yztUserinfoMapper;

	/**
	 * 精选页面被收藏后，用户再次点击精选，变为已收藏 
	 * 0是未收藏 1是已收藏
	 * 
	 * @param yztUserId
	 * @param yztChoicenesscontentId
	 * @return
	 */
	public int collectedFlag(String yztUserId, String yztChoicenesscontentId) {

		Example example = new Example(YztChoicenesscontentCollect.class);
		Example.Criteria criteria = example.createCriteria();

		criteria.andEqualTo("yztChoicenesscontentId", yztChoicenesscontentId);
		criteria.andEqualTo("yztUserId", yztUserId);

		YztChoicenesscontentCollect yztChoicenesscontentCollect = selectOneByExample(example);
		// 已收藏
		if (yztChoicenesscontentCollect != null) {
			return 1;
		}
		return 0;
	}

	/**
	 * 用户点击添加精选到收藏 先查询，数据库里面有没有记录： 1.数据库有记录，看是否有效
	 * （1）、如userid与精选id能够查到记录，（有效）则直接返回1。 （2）、不能查到（状态无效），重新收藏 2.数据库里面没有记录
	 * (1)新建收藏
	 */
	@Override
	@Transactional
	public int collect(String yztUserId, String yztChoicenesscontentId) {

		Example example = new Example(YztChoicenesscontentCollect.class);
		Example.Criteria criteria = example.createCriteria();

		criteria.andEqualTo("yztChoicenesscontentId", yztChoicenesscontentId);
		criteria.andEqualTo("yztUserId", yztUserId);

		YztChoicenesscontentCollect yztChoicenesscontentCollect = selectOneByExample(example);
		// 数据库里面有过收藏
		if (yztChoicenesscontentCollect != null) {

			// 0取消收藏1收藏 状态有效
			if (yztChoicenesscontentCollect.getCollect() == 1) {
				return 1;
			}
			// 状态无效
			String id = yztChoicenesscontentCollect.getId();
			try {
				// 修改yztChoicenesscontentCollect中间表
				String sql = " set collect = 1 where id = '" + id + "'";
				updateBySql(sql);

				// 修改精选内容表
				sql = " set collected_count = collected_count + 1 where id = '"
						+ yztChoicenesscontentId + "'";
				yztChoicenesscontentMapper.updateBySql(sql);

				// 修改用户信息表
				sql = " set collect_content_count = collect_content_count + 1 where id = '"
						+ yztUserId + "'";
				yztUserinfoMapper.updateBySql(sql);
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 数据库里面没有记录
			YztChoicenesscontent yztChoicenesscontent = yztChoicenesscontentMapper
					.getById(yztChoicenesscontentId);

			YztUserinfo user = yztUserinfoMapper.getById(yztUserId);

			YztChoicenesscontentCollect collect = new YztChoicenesscontentCollect();
			// GUIDGener:一个工具类，用于生成GUID
			collect.setId(GUIDGener.getGUID());
			// 为中间表设置数据
			collect.setCollect(1);
			collect.setYztUserId(yztUserId);
			collect.setYztChoicenesscontentId(yztChoicenesscontentId);
			collect.setCreatetime(new Date());
			collect.setCreateUserid(yztUserId);
			collect.setModifyUserid(yztUserId);
			collect.setModfiytime(new Date());
			collect.setIsdelete(0);
			collect.setYztChoicenesscontentEndTime(yztChoicenesscontent
					.getEndTime());
			collect.setYztChoicenesscontentStartTime(yztChoicenesscontent
					.getStartTime());
			collect.setYztChoicenesscontentMaintitle(yztChoicenesscontent
					.getMaintitle());
			collect.setYztUserinfoNickname(user.getNickname());
			collect.setYztUserinfoAccount(user.getAccount());
			try {
				// 向中间表中插入数据
				this.save(collect);
				// 修改精选内容收藏表
				String sql = " set collected_count = collected_count + 1 where id = '"
						+ yztChoicenesscontentId + "'";
				yztChoicenesscontentMapper.updateBySql(sql);
				// 修改用户信息表
				sql = " set collect_content_count = collect_Content_Count + 1 where id = '"
						+ yztUserId + "'";
				yztUserinfoMapper.updateBySql(sql);

				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 根据用户获取收藏精选的记录
	 */
	@Override
	@Transactional(readOnly = true)
	public int collectCount(String yztUserId) {

		Example example = new Example(YztChoicenesscontentCollect.class);
		Example.Criteria criteria = example.createCriteria();

		criteria.andEqualTo("yztUserId", yztUserId);
		criteria.andEqualTo("collect", 1);

		int count = selectCountByExample(example);

		return count;

	}

	/**
	 * 精选内容被用户收藏记录
	 */
	@Override
	@Transactional(readOnly = true)
	public int collectedCount(String yztUserId, String yztChoicenesscontentId) {

		Example example = new Example(YztChoicenesscontentCollect.class);
		Example.Criteria criteria = example.createCriteria();

		criteria.andEqualTo("yztChoicenesscontentId", yztChoicenesscontentId);
		criteria.andEqualTo("yztUserId", yztUserId);
		criteria.andEqualTo("collect", 1);

		int total = selectCountByExample(example);

		return total;
	}

	/**
	 * 根据精选内容的id 查询所有 用户列表 order：指的是降序升序
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public EasyuiDataGridJson getChicenesscontentCollectByChoicenesscontentId(
			String yztChoicenesscontentId, EasyuiDataGrid dg) {

		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		Example example = new Example(YztChoicenesscontentCollect.class);
		Example.Criteria criteria = example.createCriteria();

		criteria.andEqualTo("yztChoicenesscontentId", yztChoicenesscontentId);
		// 只查询收藏有效的记录
		criteria.andEqualTo("collect", 1);

		// 加入排序
		if (dg.getSort() != null && dg.getOrder() != null) {
			String orderby = "";
			String[] order = dg.getSort().split(",");
			String[] sort = dg.getOrder().split(",");
			for (int i = 0; i < order.length; i++) {
				orderby += order[i] + " " + sort[i] + ",";
			}
			orderby = StringUtils.substringBeforeLast(orderby, ",");
			example.setOrderByClause(orderby);
		}

		// 查询总数
		listjson.setTotal(selectCountByExample(example));
		// 分页查询
		PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
		return listjson;

	}

	/**
	 * 根据用户查询所有的精品收藏的记录
	 */
	@Override
	@Transactional(readOnly = true)
	public EasyuiDataGridJson getCollectByUserId(String yztUserId,
			EasyuiDataGrid dg) {

		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		Example example = new Example(YztChoicenesscontentCollect.class);
		Example.Criteria criteria = example.createCriteria();

		criteria.andEqualTo("yztUserId", yztUserId);
		criteria.andEqualTo("collect", 1);
		// 加入排序
		if (dg.getSort() != null && dg.getOrder() != null) {
			String orderby = "";
			String[] order = dg.getSort().split(",");
			String[] sort = dg.getOrder().split(",");
			for (int i = 0; i < order.length; i++) {
				orderby += order[i] + " " + sort[i] + ",";
			}
			orderby = StringUtils.substringBeforeLast(orderby, ",");
			example.setOrderByClause(orderby);
		}
		// 查询总数
		listjson.setTotal(selectCountByExample(example));
		// 分页查询
		PageHelper.startPage(dg.getPage(), dg.getRows());
		listjson.setRows(selectByExample(example));
		return listjson;
	}

	/**
	 * 取消收藏 根据中间表的id更改状态
	 */
	@Override
	@Transactional
	public int unCollect(String ids) {

		String idsSelect = "";
		// 从前台获取中间表的字符串ids，使用split以，截取
		String[] split = ids.split(",");
		// 遍历字符串，并给每个id加上''
		for (String id : split) {
			idsSelect += ",'" + id + "'";
		}
		// 将第一个多余的，删掉
		idsSelect = idsSelect.substring(1);
		List<YztChoicenesscontentCollect> list = selectByIds(idsSelect);
		try {
			for (YztChoicenesscontentCollect collect : list) {

				if (collect.getCollect() == 0) {
					System.out.println("collect.getCollect:"+ collect.getCollect());
					continue;
				}
				// 根据yztUserId和查询到中间表的collect修改1改为0
				String sql = "SET collect = 0 WHERE id = '" + collect.getId()
						+ "'";
				updateBySql(sql);

				// 根据精选内容的id查询出被收藏的次数
				sql = "SET collected_count = collected_count - 1 WHERE id = '"
						+ collect.getYztChoicenesscontentId() + "'";
				yztChoicenesscontentMapper.updateBySql(sql);

				// 根据用户的id查询出收藏精品的数量
				sql = "SET collect_content_count = collect_content_count - 1 WHERE id = '"
						+ collect.getYztUserId() + "'";
				yztUserinfoMapper.updateBySql(sql);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("return0:"+0);;
		return 0;
	}

	/**
	 * 取消收藏(方法麻烦，不使用)
	 */
	/*
	 * @Override
	 * 
	 * @Transactional(readOnly = true) public int unCollect(String yztUserId,
	 * String yztChoicenesscontentId) {
	 * 
	 * try { // 根据yztUserId和查询到中间表的collect修改1改为0 String sql =
	 * "SET collect = 0 WHERE yzt_user_id = '" + yztUserId +
	 * "'AND yzt_choicenesscontent_id = '" + yztChoicenesscontentId + "'";
	 * updateBySql(sql);
	 * 
	 * // 根据精选内容的id查询出被收藏的次数-1 sql =
	 * "SET collected_count = collected_count - 1 WHERE id = '" +
	 * yztChoicenesscontentId + "'";
	 * yztChoicenesscontentCollectMapper.updateBySql(sql);
	 * 
	 * // 根据用户的id查询出收藏精品的数量-1 sql =
	 * "SET collect_content_count = collect_content_count - 1 WHERE id = '" +
	 * yztUserId + "'"; yztUserinfoMapper.updateBySql(sql);
	 * 
	 * return 1; } catch (Exception e) { e.printStackTrace(); } return 0; }
	 */

}
