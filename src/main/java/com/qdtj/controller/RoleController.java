package com.qdtj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdtj.datasource.DataSourceContextHolder;
import com.qdtj.datasource.DataSourceType;
import com.qdtj.domain.User;
import com.qdtj.service.impl.RoleServiceImpl;

/**
 * 角色
 * @author caiang
 *
 */
@Controller
public class RoleController {

	private static Logger logger = Logger.getLogger(RoleController.class);

	@Resource
	RoleServiceImpl roleServiceImpl;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getRoleList.do")
	public String doGetRoleList(HttpServletRequest request) throws Exception {
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.QDTJ);
		List roleList = roleServiceImpl.getRoleList();
		request.getSession().setAttribute("roleList", roleList);
		return "/permissionSet";
	}
	
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updateRole.do")
	public void doUpdateUser(HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		String roleCnname = request.getParameter("roleCnname");
		Map<String, Object> roleMap = new HashMap<String, Object>();
		roleMap.put("roleId", roleId);
		roleMap.put("roleCnname", roleCnname);
		roleServiceImpl.updateRole(roleMap);
		List roleList = roleServiceImpl.getRoleList();
		request.getSession().setAttribute("roleList", roleList);
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/deleteRole.do")
	public void doDeleteRole(HttpServletRequest request) throws Exception {
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		roleServiceImpl.deleteRole(roleId);
		List roleList = roleServiceImpl.getRoleList();
		request.getSession().setAttribute("roleList", roleList);
	}

}
