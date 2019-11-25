package com.qdtj.domain;

import java.io.Serializable;

/**
 * 数据一览bean
 * 
 * @author caiang
 *
 */
public class DataShow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6994582393096257469L;

	/**
	 * 今日投资金额
	 */
	private String totalAmountToday;
	/**
	 * 昨日投资金额
	 */
	private String totalAmountYesterday;
	/**
	 * 本周投资金额
	 */
	private String totalAmountWeek;
	/**
	 * 本月投资金额
	 */
	private String totalAmountMonth;
	/**
	 * 今日注册人数
	 */
	private String totalNumberToday;
	/**
	 * 昨日注册人数
	 */
	private String totalNumberYesterday;
	/**
	 * 本周注册人数
	 */
	private String totalNumberWeek;
	/**
	 * 本月注册人数
	 */
	private String totalNumberMonth;
	/**
	 * 今日新增投资人数(首次投资)
	 */
	private String totalInvestmentToday;
	/**
	 * 昨日新增投资人数(首次投资)
	 */
	private String totalInvestmentYesterday;
	/**
	 * 本周新增投资人数(首次投资)
	 */
	private String totalInvestmentWeek;
	/**
	 * 本月新增投资人数(首次投资)
	 */
	private String totalInvestmentMonth;

	public String getTotalAmountToday() {
		return totalAmountToday;
	}

	public void setTotalAmountToday(String totalAmountToday) {
		this.totalAmountToday = totalAmountToday;
	}

	public String getTotalAmountYesterday() {
		return totalAmountYesterday;
	}

	public void setTotalAmountYesterday(String totalAmountYesterday) {
		this.totalAmountYesterday = totalAmountYesterday;
	}

	public String getTotalAmountWeek() {
		return totalAmountWeek;
	}

	public void setTotalAmountWeek(String totalAmountWeek) {
		this.totalAmountWeek = totalAmountWeek;
	}

	public String getTotalAmountMonth() {
		return totalAmountMonth;
	}

	public void setTotalAmountMonth(String totalAmountMonth) {
		this.totalAmountMonth = totalAmountMonth;
	}

	public String getTotalNumberToday() {
		return totalNumberToday;
	}

	public void setTotalNumberToday(String totalNumberToday) {
		this.totalNumberToday = totalNumberToday;
	}

	public String getTotalNumberYesterday() {
		return totalNumberYesterday;
	}

	public void setTotalNumberYesterday(String totalNumberYesterday) {
		this.totalNumberYesterday = totalNumberYesterday;
	}

	public String getTotalNumberWeek() {
		return totalNumberWeek;
	}

	public void setTotalNumberWeek(String totalNumberWeek) {
		this.totalNumberWeek = totalNumberWeek;
	}

	public String getTotalNumberMonth() {
		return totalNumberMonth;
	}

	public void setTotalNumberMonth(String totalNumberMonth) {
		this.totalNumberMonth = totalNumberMonth;
	}

	public String getTotalInvestmentToday() {
		return totalInvestmentToday;
	}

	public void setTotalInvestmentToday(String totalInvestmentToday) {
		this.totalInvestmentToday = totalInvestmentToday;
	}

	public String getTotalInvestmentYesterday() {
		return totalInvestmentYesterday;
	}

	public void setTotalInvestmentYesterday(String totalInvestmentYesterday) {
		this.totalInvestmentYesterday = totalInvestmentYesterday;
	}

	public String getTotalInvestmentWeek() {
		return totalInvestmentWeek;
	}

	public void setTotalInvestmentWeek(String totalInvestmentWeek) {
		this.totalInvestmentWeek = totalInvestmentWeek;
	}

	public String getTotalInvestmentMonth() {
		return totalInvestmentMonth;
	}

	public void setTotalInvestmentMonth(String totalInvestmentMonth) {
		this.totalInvestmentMonth = totalInvestmentMonth;
	}

}
