package com.qdtj.service;

import java.util.List;
import java.util.Map;

import com.qdtj.domain.Permission;

public interface IPermissionService {
	
	/**
	 * 根据查看类型和用户ID查看相关权限记录
	 * @param user
	 * @return
	 */
	List<Permission> getPermissionByUserId(Map<?, ?> perUserMap);
	
	/**
	 * 查看所有权限和对应的角色
	 * @param user
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List getAllPermissionForRole(int roleId);
	
	/**
	 * 权限操作处理
	 * @param user
	 * @return
	 */
	void dealPermissionForRole(int roleId, List<Map<String, Object>> allPermissionList, List<Map<String, Object>> allchannelList);
	
}
