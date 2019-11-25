package com.qdtj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 注册统计
 * 
 * @author xuyanlin
 *
 */
public interface IRegisterStatDao {

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
	List<?> getRegisterStat(Map<?, ?> registerSearchMap);

}
