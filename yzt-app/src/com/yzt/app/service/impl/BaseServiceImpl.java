package com.yzt.app.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.common.Mapper;

import com.github.pagehelper.PageHelper;
import com.yzt.app.mapper.YztChoicenesscontentCollectMapper;
import com.yzt.app.mapper.YztUserinfoMapper;
import com.yzt.app.model.YztLearningcenter;
import com.yzt.app.service.BaseService;
import com.yzt.app.utils.mymapper.YztMapper;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T>{
	 	@Autowired
	    protected YztMapper<T> mapper;
	 
	    @Override
	    public T selectByKey(Object key) {
	        return mapper.selectByPrimaryKey(key);
	    }

	    public int save(T entity) {
	        return mapper.insert(entity);
	    }

	    public int delete(Object key) {
	        return mapper.deleteByPrimaryKey(key);
	    }

	    public int updateAll(T entity) {
	        return mapper.updateByPrimaryKey(entity);
	    }

	    public int updateNotNull(T entity) {
	        return mapper.updateByPrimaryKeySelective(entity);
	    }

	    public List<T> selectByExample(Object example) {
	        return mapper.selectByExample(example);
	    }
	    
	    public int selectCountByExample(Object example){
	    	return mapper.selectCountByExample(example);
	    }
	    public Integer updateBySql(@Param(value = "sql")String sql) throws Exception{
	    	return mapper.updateBySql(sql);
	    }
	    
	   
	    
	   /* public T selectOneByExample(Object example) {
	    	PageHelper.startPage(1, 1);
	        return mapper.selectByExample(example).get(0);
	    }*/
	    
	    // 自定义
		public T selectOneByExample(Object example) {
			PageHelper.startPage(1, 1);
			List<T> list = mapper.selectByExample(example);
			if(list != null && list.size() > 0){
				/*System.out.println("selectByExample存在。。。。。。。");*/
				return list.get(0);
			}
			/*System.out.println("selectByExample不存在！！！！！！！！！！！");*/
			return null;
		}

	    
	    public List<T> selectByIds(@Param(value = "ids")String sql) {
	        return mapper.selectByIds(sql);
	    }
	   
}
