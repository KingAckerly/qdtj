package com.qdtj.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.qdtj.dao.IInvestmentDao;
import com.qdtj.domain.InvSelectCondition;
import com.qdtj.domain.InvestmentSum;
import com.qdtj.service.IInvestmentService;

/**
 * 投资统计
 * 
 * @author caiang
 *
 */
@Service("investmentService")
public class InvestmentServiceImpl implements IInvestmentService {

	@Resource
	private IInvestmentDao investmentDao;

	@Override
	public List getT6211List() {
		return investmentDao.getT6211List();
	}

	@Override
	public InvestmentSum getInvestmentSum(InvSelectCondition invSelectCondition) {
		return investmentDao.getInvestmentSum(invSelectCondition);
	}

	@Override
	public List getInvestment(InvSelectCondition invSelectCondition) {
		return investmentDao.getInvestment(invSelectCondition);
	}

}
