package com.qdtj.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdtj.datasource.DataSourceContextHolder;
import com.qdtj.datasource.DataSourceType;
import com.qdtj.domain.ActiveUser;
import com.qdtj.service.impl.ChannelManageServiceImpl;
import com.qdtj.service.impl.PermissionServiceImpl;

/**
 * @Title: PermissionController
 * @Description:权限查询
 * @author xuyanlin
 * @date 2017年9月27日11:50:04
 * @version 1.0
 */
@Controller
public class PermissionController {

	// private static Logger log = Logger.getLogger(PermissionController.class);

	@Resource
	private PermissionServiceImpl permissionServiceImpl;
	
	@Resource
	private ChannelManageServiceImpl channelManageServiceImpl;

	/**
	 * 权限管理页面
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/getPermissionByUserId.do")
	public String getPermissionByUserId(HttpServletRequest request) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		request.setAttribute("activeUser", activeUser);
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.QDTJ);
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		// 所有权限信息
		List permissionList = permissionServiceImpl.getAllPermissionForRole(roleId);
		request.getSession().setAttribute("permissionList", permissionList);
		request.getSession().setAttribute("roleId", roleId);
		
		// 所有渠道信息
		List channelList = channelManageServiceImpl.getAllChannelForRole(roleId);
		request.getSession().setAttribute("channelList", channelList);
		request.getSession().setAttribute("roleId", roleId);
		return "";
	}

	/**
	 * 权限操作处理
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/dealPermissionForRole.do")
	public void dealPermission(HttpServletRequest request) throws Exception {
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.QDTJ);
		
		List<Map<String, Object>> allPermissionList = null;
		List<Map<String, Object>> allChannelList = null;
		// 拿到ajax的data中的数组字符串(角色id数组)
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		String permissionS = request.getParameter("permissionS");
		if(permissionS != null && !"".equals(permissionS)){
			// 拆分成数组形式
			String[] permissionArray = permissionS.split(",");
	
			allPermissionList = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < permissionArray.length; i++) {
				Map<String, Object> permissionMap = new HashMap<String, Object>();
				if(permissionArray[i] != null && !"".equals(permissionArray[i])){
					permissionMap.put("roleId", roleId);
					permissionMap.put("permissionId", permissionArray[i]);
					allPermissionList.add(permissionMap);
				}
			}
		}
		
		String channelS = request.getParameter("channelS");
		if(channelS != null && !"".equals(channelS)){
			// 拆分成数组形式
			String[] channelArray = channelS.split(",");
	
			allChannelList = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < channelArray.length; i++) {
				Map<String, Object> channelMap = new HashMap<String, Object>();
				if(channelArray[i] != null && !"".equals(channelArray[i])){
					channelMap.put("roleId", roleId);
					channelMap.put("channelId", channelArray[i]);
					allChannelList.add(channelMap);
				}
			}
		}

		permissionServiceImpl.dealPermissionForRole(roleId, allPermissionList, allChannelList);
//		return null;
	}

}
