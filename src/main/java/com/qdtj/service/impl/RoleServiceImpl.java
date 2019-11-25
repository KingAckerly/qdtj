package com.qdtj.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qdtj.dao.IRoleDao;
import com.qdtj.service.IRoleService;

/**
 * 角色
 * 
 * @author caiang
 *
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao roleDao;

	@Override
	@SuppressWarnings("rawtypes")
	public List getRoleList() {
		return roleDao.getRoleList();
	}

	@Override
	public void updateRole(Map<?, ?> roleMap) {
		// TODO Auto-generated method stub
		roleDao.updateRole(roleMap);
	}

	@Override
	public void deleteRole(int roleId) {
		// TODO Auto-generated method stub
		roleDao.deleteRole(roleId);
		//删除角色关联信息
		roleDao.deleteRoleForChannel(roleId);
		roleDao.deleteRoleForPermission(roleId);
	}

}
