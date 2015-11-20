package com.yzt.app.utils.web;

import com.alibaba.fastjson.JSONObject;

/**
 * easyui的datagrid向后台传递参数使用的model
 * 
 * @author zhy
 * 
 */
public class EasyuiDataGrid implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int page = 1;// 当前页
	private int rows = 20;// 每页显示记录数
	private String sort = null;// 排序字段名
	private String order = "asc";// 按什么排序(asc,desc)
	private JSONObject others ;

	private String filterRules = null;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getFilterRules() {
		return filterRules;
	}

	public void setFilterRules(String filterRules) {
		this.filterRules = filterRules;
	}
	
	public JSONObject getOthers() {
		return others;
	}

	public void setOthers(JSONObject others) {
		this.others = others;
	}

}
