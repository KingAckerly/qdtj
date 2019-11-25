package com.qdtj.domain;

import java.io.Serializable;

/**
 * 投资数据bean
 * 
 * @author caiang
 *
 */
public class Investment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2790471661989201814L;

	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 投资标的类型
	 */
	private String invBidType;
	/**
	 * 投资金额
	 */
	private String invAmount;
	/**
	 * 投资时间
	 */
	private String invDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInvBidType() {
		return invBidType;
	}

	public void setInvBidType(String invBidType) {
		this.invBidType = invBidType;
	}

	public String getInvAmount() {
		return invAmount;
	}

	public void setInvAmount(String invAmount) {
		this.invAmount = invAmount;
	}

	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

}
