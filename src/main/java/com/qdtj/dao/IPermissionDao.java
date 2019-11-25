package com.qdtj.dao;

import java.util.List;
import java.util.Map;

import com.qdtj.domain.Permission;

public interface IPermissionDao {

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
	List<?> getAllPermissionForRole(int roleId);
	
	
	/**
	 * 权限操作处理
	 * @param allPermissionList
	 */
	void dealPermissionForRole(List<Map<String, Object>> allPermissionList);
	
	/**
	 * 删除角色权限
	 * @param allPermissionList
	 */
	void deletePermissionForRole(int roleId);
}
