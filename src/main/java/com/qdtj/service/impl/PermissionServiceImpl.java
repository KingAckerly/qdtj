package com.qdtj.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qdtj.dao.IChannelManageDao;
import com.qdtj.dao.IPermissionDao;
import com.qdtj.domain.Permission;
import com.qdtj.service.IPermissionService;


@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {
	@Resource
	private IPermissionDao permissionDao;
	
	@Resource
	private IChannelManageDao channelManageDao;

	@Override
	public List<Permission> getPermissionByUserId(Map<?, ?> perUserMap) {
		// TODO Auto-generated method stub
		return permissionDao.getPermissionByUserId(perUserMap);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllPermissionForRole(int roleId) {
		// TODO Auto-generated method stub
		return permissionDao.getAllPermissionForRole(roleId);
	}

	@Override
	public void dealPermissionForRole(int roleId, List<Map<String, Object>> allPermissionList, List<Map<String, Object>> allChannelList) {
		// TODO Auto-generated method stub
		//先删除，再新增--菜单权限
		permissionDao.deletePermissionForRole(roleId);
		if(allPermissionList != null && !allPermissionList.isEmpty()){
			permissionDao.dealPermissionForRole(allPermissionList);
		}
		//先删除，再新增--渠道权限
		channelManageDao.deleteChannelForRole(roleId);
		if(allChannelList != null && !allChannelList.isEmpty()){
			channelManageDao.dealChannelForRole(allChannelList);
		}
	}

}
