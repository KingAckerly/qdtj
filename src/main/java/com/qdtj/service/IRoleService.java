package com.qdtj.service;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * @author caiang
 *
 */
public interface IRoleService {
	/**
	 * 查看角色列表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List getRoleList();
	
	/**
	 * 更新角色信息
	 * @param roleMap
	 */
	void updateRole(Map<?, ?> roleMap);
	
	/**
	 * 更新角色信息
	 * @param roleMap
	 */
	void deleteRole(int roleId);
}
