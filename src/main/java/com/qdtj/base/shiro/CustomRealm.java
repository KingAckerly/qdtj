package com.qdtj.base.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.qdtj.domain.ActiveUser;
import com.qdtj.domain.Permission;
import com.qdtj.domain.User;
import com.qdtj.service.impl.PermissionServiceImpl;
import com.qdtj.service.impl.UserServiceImpl;


public class CustomRealm extends AuthorizingRealm{
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private PermissionServiceImpl permissionServiceImpl;
	
	/**
	 * 用于认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		//第一步：通过token获取身份信息
		// 把token转换成User对象
		User userLogin = tokenToUser((UsernamePasswordToken) token);
		//从数据库中查询账号信息是否存在
		User user = null;
		try {
			user = userServiceImpl.getUserInfo(userLogin);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		//如果查询不到返回null
		if(user==null){
			return null;
		}
		
		//第二步：通过获取的身份信息进行数据库查询
		String password = user.getPwd();

		
		//组装ActiveUser类
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(user.getId());
		activeUser.setUsercode(user.getAccount());
		activeUser.setUsername(user.getUserName());
		Subject currentUser = SecurityUtils.getSubject(); 
		Session session = currentUser.getSession();
		session.setAttribute("activeUser", activeUser);
		session.setAttribute("userId", user.getId());
		session.setAttribute("username", user.getUserName());
		//查询菜单信息
		List<Permission> menus = null;
		Map<String, Object> perUserMap = new HashMap<String, Object>();
		perUserMap.put("permission_type", "menu");
		perUserMap.put("user_id", user.getId());
		try {
			menus = permissionServiceImpl.getPermissionByUserId(perUserMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		activeUser.setMenus(menus);
		
		//得到盐
//		String salt = sysUser.getSalt();
		
		//当前 Realm 的 name
		String realmName = this.getName();
				
		//如果查询到结果返回AuthenticationInfo
		AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(activeUser, password, realmName);
//		AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(activeUser, password,ByteSource.Util.bytes(salt), "");
//		Object principal = token.getPrincipal();
		
//		AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, password, realmName);
		
		return authenticationInfo;
	}
	
	private User tokenToUser(UsernamePasswordToken authcToken) {
		User user = new User();
		user.setAccount(authcToken.getUsername());
		user.setPwd(String.valueOf(authcToken.getPassword()));
		return user;
	}
	
	/**
	 * 用于授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//获取身份信息，这个字段是在认证通过后返回的，也就是通过执行认证方法返回的AuthenticationInfo类的第一个属性
		ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
		
		//通过userId查询数据库获取该身份信息的所有权限。
		List<Permission> permissionList = null;
		try {
			Map<String, Object> perUserMap = new HashMap<String, Object>();
			perUserMap.put("permission_type", "permission");
			perUserMap.put("user_id", activeUser.getUserid());
			permissionList = permissionServiceImpl.getPermissionByUserId(perUserMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> permissions = new ArrayList<>();
		if(permissionList!=null){
			for(Permission p:permissionList){
				permissions.add(p.getPercode());
			}
		}
		
		//查询到权限信息，然后返回权限信息
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//将查询到的授权信息填充到SimpleAuthorizationInfo中
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}
	
	/**
	 * 清除缓存方法
	 */
	public void clearCache(){
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);	

	}

	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public PermissionServiceImpl getPermissionServiceImpl() {
		return permissionServiceImpl;
	}

	public void setPermissionServiceImpl(PermissionServiceImpl permissionServiceImpl) {
		this.permissionServiceImpl = permissionServiceImpl;
	}
	
}
