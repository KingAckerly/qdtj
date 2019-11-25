package com.qdtj.dao;
/**
 * 角色
 * @author caiang
 *
 */

import java.util.List;
import java.util.Map;

public interface IRoleDao {
	/**
	 * 查看角色列表
	 * 
	 * @return
	 */
	List<?> getRoleList();
	
	/**
	 * 更新角色信息
	 * @param roleMap
	 */
	void updateRole(Map<?, ?> roleMap);
	
	/**
	 * 删除角色信息
	 * 
	 * @param uesrId
	 */
	void deleteRole(int roleId);
	
	/**
	 * 删除角色关联渠道表
	 * 
	 * @param uesrId
	 */
	void deleteRoleForChannel(int roleId);
	
	/**
	 * 删除角色关联权限表
	 * 
	 * @param uesrId
	 */
	void deleteRoleForPermission(int roleId);
}
