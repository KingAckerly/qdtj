package com.qdtj.domain;

import java.io.Serializable;

/**
 * 投资统计查询条件bean
 * 
 * @author caiang
 *
 */
public class InvSelectCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9772852290210391L;

	/**
	 * 起始时间
	 */
	private String dataInvestAgain;
	/**
	 * 结束时间
	 */
	private String dataInvestEnd;

	/**
	 * 渠道识别号
	 */
	private String channelNumber;
	/**
	 * 投资标的类型
	 */
	private String invBidType;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String phone;

	public String getDataInvestAgain() {
		return dataInvestAgain;
	}

	public void setDataInvestAgain(String dataInvestAgain) {
		this.dataInvestAgain = dataInvestAgain;
	}

	public String getDataInvestEnd() {
		return dataInvestEnd;
	}

	public void setDataInvestEnd(String dataInvestEnd) {
		this.dataInvestEnd = dataInvestEnd;
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public String getInvBidType() {
		return invBidType;
	}

	public void setInvBidType(String invBidType) {
		this.invBidType = invBidType;
	}

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

}
