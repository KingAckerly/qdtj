package com.qdtj.service;

import java.util.List;
import java.util.Map;

import com.qdtj.util.PagedResult;

/**
 * 注册统计
 * 
 * @author xuyanlin
 *
 */
public interface IRegisterStatService {
	/**
	 * 按时间跨度查询 注册用户量汇总数，实名认证用户汇总数，成功绑卡用户汇总数
	 * 
	 * @param date
	 * @return
	 */
	Map<?, ?> getRegisterStatSum(Map<?, ?> dateMap);
	
	/**
	 * 查询注册数据
	 * 
	 * @return
	 */
	PagedResult<?> getRegisterStat(Map<?, ?> registerSearchMap, Integer pageNo, Integer pageSize);
}
