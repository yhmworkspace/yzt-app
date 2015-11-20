package com.yzt.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 通用接口
 */
@Service
public interface BaseService<T> {
	T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectByExample(Object example);
    
   // public List<T> getChildList(String pid) throws Exception ;
    Integer updateBySql(@Param(value = "sql")String sql) throws Exception;

	T selectOneByExample(Object example);
	
	List<T> selectByIds(@Param(value = "ids")String sql) throws Exception;
}
