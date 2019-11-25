package com.qdtj.service;

import java.util.List;

import com.qdtj.domain.Channel;
import com.qdtj.domain.Role;

/**
 * 渠道统计
 * 
 * @author caiang
 *
 */
public interface IChannelManageService {
	/**
	 * 新增渠道
	 */
	void addChannel(Channel channel);

	/**
	 * 修改渠道
	 * 
	 * @param role
	 */
	void updateChannel(Channel channel);

	/**
	 * 查看渠道列表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List getChannelList(String channelName);
	
	/**
	 * 查看所有渠道和对应的角色
	 * @param user
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List getAllChannelForRole(int roleId);
}
