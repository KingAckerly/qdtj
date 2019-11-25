package com.qdtj.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qdtj.datasource.DataSourceContextHolder;
import com.qdtj.datasource.DataSourceType;
import com.qdtj.domain.InvSelectCondition;
import com.qdtj.domain.Investment;
import com.qdtj.domain.InvestmentSum;
import com.qdtj.service.impl.InvestmentServiceImpl;
import com.qdtj.service.impl.UserServiceImpl;

/**
 * 投资统计
 * 
 * @author caiang
 *
 */
@Controller
public class InvestmentController {

	private static Logger logger = Logger.getLogger(InvestmentController.class);

	@Resource
	UserServiceImpl userServiceImpl;
	@Resource
	InvestmentServiceImpl investmentServiceImpl;

	@RequestMapping("/investStat.do")
	public String doInvestStat(HttpServletRequest request) throws Exception {
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.QDTJ);
		//查询渠道列表
		String userId = request.getSession().getAttribute("userId").toString();
		logger.info("---userId---" + userId);
		List userChannelList = userServiceImpl.getUserChannel(Integer.parseInt(userId));
		request.setAttribute("userChannelList", userChannelList);
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.S62);
		//查询标的类型
		List t6211List = investmentServiceImpl.getT6211List();
		request.setAttribute("t6211List", t6211List);
		//查询 总投资人次汇总数,总投资金额汇总数,首次投资金额汇总数,首次投资人数汇总数
		InvestmentSum is = new InvestmentSum();
		//传null代表查所有
		is = investmentServiceImpl.getInvestmentSum(null);
		request.setAttribute("investmentSum", is);
		//查询 用户名,手机号码,投资标的,投资金额,投资时间
		InvSelectCondition invSelectCondition = new InvSelectCondition();
		List investmentList = investmentServiceImpl.getInvestment(invSelectCondition);
		request.setAttribute("investmentList", investmentList);
		return "investStat";
	}
	
	@RequestMapping("/selectByCondition.do")
	public String doSelectByCondition(HttpServletRequest request) throws Exception {
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.QDTJ);
		//查询渠道列表
		String userId = request.getSession().getAttribute("userId").toString();
		logger.info("---userId---" + userId);
		List userChannelList = userServiceImpl.getUserChannel(Integer.parseInt(userId));
		request.setAttribute("userChannelList", userChannelList);
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.S62);
		//查询标的类型
		List t6211List = investmentServiceImpl.getT6211List();
		request.setAttribute("t6211List", t6211List);
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.S62);
		//获取前台查询条件
		String dataInvestAgain = request.getParameter("dataInvestAgain");//起始日期
		String dataInvestEnd = request.getParameter("dataInvestEnd");//结束日期
		String channelNumber = request.getParameter("channelNumber");//渠道识别号
		String invBidType = request.getParameter("invBidType");//投资标的类型
		String userName = request.getParameter("userName");//用户名
		String phone = request.getParameter("phone");//手机号
		//填充查询条件对象
		InvSelectCondition invSelectCondition = new InvSelectCondition();
		invSelectCondition.setDataInvestAgain(dataInvestAgain);
		invSelectCondition.setDataInvestEnd(dataInvestEnd);
		invSelectCondition.setChannelNumber(channelNumber);
		invSelectCondition.setInvBidType(invBidType);
		invSelectCondition.setUserName(userName);
		invSelectCondition.setPhone(phone);
		//打印
		logger.info("---dataInvestAgain---"+dataInvestAgain);
		logger.info("---dataInvestEnd---"+dataInvestEnd);
		//投资汇总对象
		InvestmentSum is = new InvestmentSum();
		//查询 总投资人次汇总数,总投资金额汇总数,首次投资金额汇总数,首次投资人数汇总数
		is = investmentServiceImpl.getInvestmentSum(invSelectCondition);
		request.setAttribute("investmentSum", is);
		//查询 用户名,手机号码,投资标的,投资金额,投资时间
		List investmentList = investmentServiceImpl.getInvestment(invSelectCondition);
		request.getSession().setAttribute("investmentList", investmentList);
		return "investStat";
	}
	

}
