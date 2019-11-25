package com.qdtj.domain;

import java.io.Serializable;

/**
 * 标类型表
 * 
 * @author caiang
 *
 */
public class T6211 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1340873722545162380L;

	/**
	 * 标类型ID
	 */
	private int id;
	/**
	 * 类型名称
	 */
	private String type;
	/**
	 * 状态,QY:启用;TY:停用
	 */
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
