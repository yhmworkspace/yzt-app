package com.yzt.app.utils.mymapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的YztMapper
 *	相当于BaseMapper
 * @author zhy
 * @since 2015-09-23 21:53
 */
public interface YztMapper<T> extends Mapper<T>, MySqlMapper<T> {
	Integer updateBySql(@Param(value = "sql")String sql) throws Exception;

	List<T> selectByIds(@Param(value = "ids")String sql);
	
}
