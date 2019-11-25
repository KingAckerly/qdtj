package com.qdtj.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 注册统计查询条件bean
 * 
 * @author xuyanlin
 *
 */
@SuppressWarnings("serial")
public class RegisterStatCondition implements Serializable {

	/**
	 * 注册渠道
	 */
	private String registerChannel;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 是否实名
	 */
	private String isRealName;
	/**
	 * 是否绑卡
	 */
	private String isCheckCard;
	/**
	 * 注册时间 
	 */
	private Date registerDate;
	public String getRegisterChannel() {
		return registerChannel;
	}
	public void setRegisterChannel(String registerChannel) {
		this.registerChannel = registerChannel;
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
	public String getIsRealName() {
		return isRealName;
	}
	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}
	public String getIsCheckCard() {
		return isCheckCard;
	}
	public void setIsCheckCard(String isCheckCard) {
		this.isCheckCard = isCheckCard;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

}
