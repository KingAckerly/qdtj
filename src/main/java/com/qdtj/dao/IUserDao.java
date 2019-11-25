package com.qdtj.dao;

import java.util.List;

import com.qdtj.domain.User;
import com.qdtj.domain.UserRole;

public interface IUserDao {

	/**
	 * 后台管理用户登录
	 * 
	 * @param user
	 * @return
	 */
	Integer login(User user);

	/**
	 * 后台管理用户登录
	 * 
	 * @param user
	 * @return
	 */
	User getUserInfo(User user);

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 修改用户(用户名/密码)
	 * 
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param uesrId
	 */
	void deleteUser(Integer uesrId);

	/**
	 * 查看用户列表
	 * 
	 * @return
	 */
	List getUserList();
	
	/**
	 * 根据用户ID获取该用户所拥有的角色列表
	 * @return
	 */
	List getUserRoleList(Integer uesrId);
	
	/**
	 * 根据用户ID删除该用户所拥有的全部角色
	 * @param uesrId
	 */
	void deleteUserRole(Integer uesrId);
	
	/**
	 * 给指定用户新增指定角色
	 */
	void insertUserRole(UserRole userRole);
	
	/**
	 * 根据用户ID查找该用户所能查看的渠道(去重)
	 * @param uesrId
	 * @return
	 */
	List getUserChannel(Integer uesrId);
}
