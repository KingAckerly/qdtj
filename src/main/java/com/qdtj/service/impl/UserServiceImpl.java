package com.qdtj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qdtj.dao.IUserDao;
import com.qdtj.domain.User;
import com.qdtj.domain.UserRole;
import com.qdtj.service.IUserService;


@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	@Override
	public Integer login(User user) {
		return userDao.login(user);
	}

	@Override
	public User getUserInfo(User user) {
		return userDao.getUserInfo(user);
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(Integer uesrId) {
		userDao.deleteUser(uesrId);
	}

	@Override
	public List getUserList() {
		return userDao.getUserList();
	}

	@Override
	public List getUserRoleList(Integer uesrId) {
		return userDao.getUserRoleList(uesrId);
	}

	@Override
	public void deleteUserRole(Integer uesrId) {
		userDao.deleteUserRole(uesrId);
	}

	@Override
	public void insertUserRole(UserRole userRole) {
		userDao.insertUserRole(userRole);
	}

	@Override
	public List getUserChannel(Integer uesrId) {
		return userDao.getUserChannel(uesrId);
	}
}
