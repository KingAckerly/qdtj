package com.qdtj.service;

import java.util.List;

import com.qdtj.domain.InvSelectCondition;
import com.qdtj.domain.InvestmentSum;


/**
 * 投资统计
 * 
 * @author caiang
 *
 */
public interface IInvestmentService {
	/**
	 * 查询标的类型
	 * 
	 * @return
	 */
	List getT6211List();
	
	/**
	 * 按投资时间查询 总投资人次汇总数,总投资金额汇总数,首次投资金额汇总数,首次投资人数汇总数
	 * 
	 * @param date
	 * @return
	 */
	InvestmentSum getInvestmentSum(InvSelectCondition invSelectCondition);
	
	/**
	 * 查询投资数据
	 * @return
	 */
	List getInvestment(InvSelectCondition invSelectCondition);
}
