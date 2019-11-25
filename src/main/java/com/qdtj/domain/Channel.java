package com.qdtj.domain;

import java.io.Serializable;

/**
 * 渠道bean
 * 
 * @author caiang
 *
 */
public class Channel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2155476525344706926L;
	/**
	 * 渠道ID
	 */
	private int channelId;
	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道识别号
	 */
	private String channelNumber;

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

}
