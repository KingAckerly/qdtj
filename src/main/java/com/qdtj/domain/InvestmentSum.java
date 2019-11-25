package com.qdtj.domain;
/**
 * 投资汇总bean
 * @author caiang
 *
 */

import java.io.Serializable;

public class InvestmentSum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7953594279548444994L;

	/**
	 * 总投资人次汇总数
	 */
	private String invPeoSum;
	/**
	 * 总投资金额汇总数
	 */
	private String invAmoSum;
	/**
	 * 首次投资金额汇总数
	 */
	private String firstInvAmoSum;
	/**
	 * 首次投资人数汇总数
	 */
	private String firstInvPeoSum;

	public String getInvPeoSum() {
		return invPeoSum;
	}

	public void setInvPeoSum(String invPeoSum) {
		this.invPeoSum = invPeoSum;
	}

	public String getInvAmoSum() {
		return invAmoSum;
	}

	public void setInvAmoSum(String invAmoSum) {
		this.invAmoSum = invAmoSum;
	}

	public String getFirstInvAmoSum() {
		return firstInvAmoSum;
	}

	public void setFirstInvAmoSum(String firstInvAmoSum) {
		this.firstInvAmoSum = firstInvAmoSum;
	}

	public String getFirstInvPeoSum() {
		return firstInvPeoSum;
	}

	public void setFirstInvPeoSum(String firstInvPeoSum) {
		this.firstInvPeoSum = firstInvPeoSum;
	}

}
