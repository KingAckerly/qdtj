package com.qdtj.dao;

import java.util.List;
import java.util.Map;

import com.qdtj.domain.Channel;
import com.qdtj.domain.Role;

/**
 * 渠道管理
 * 
 * @author caiang
 *
 */
public interface IChannelManageDao {
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
	
	/**
	 * 渠道操作处理
	 * @param allPermissionList
	 */
	void dealChannelForRole(List<Map<String, Object>> allChannelList);
	
	/**
	 * 删除角色权限
	 * @param allPermissionList
	 */
	void deleteChannelForRole(int roleId);
}
