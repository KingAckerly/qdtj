package com.qdtj.util;

import java.util.List;
import java.util.Map;

import com.qdtj.dto.BaseEntity;

/**
 * 功能概要：分页结果集
 * 
 * @author xuyanlin
 * @since  2015年10月23日 
 */
public class PagedResult<T> extends BaseEntity {
	
	/*serialVersionUID*/
	private static final long serialVersionUID = 1L;

	private List<T> dataList;//数据
	
	private long pageNo;//当前页
	
	private long pageSize;//条数
	
	private long total;//总条数
	
	private long pages;//总页面数目
	
	private Map<?, ?> sumMap;

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public Map<?, ?> getSumMap() {
		return sumMap;
	}

	public void setSumMap(Map<?, ?> sumMap) {
		this.sumMap = sumMap;
	}
	
}
