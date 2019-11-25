package com.qdtj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdtj.domain.User;
import com.qdtj.domain.UserRole;
import com.qdtj.exception.CustomException;
import com.qdtj.service.impl.UserServiceImpl;

/**
 * 
 * <p>
 * Title: LoginController
 * </p>
 * <p>
 * Description: 登陆和退出
 * </p>
 * 
 * @author xuyanlin
 * @date 2017年9月27日16:38:59
 * @version 1.0
 */
@Controller
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Resource
	UserServiceImpl userServiceImpl;

	// 用户登陆提交方法
	/**
	 * 
	 * <p>
	 * Title: login
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param session
	 * @param randomcode
	 *            输入的验证码
	 * @param usercode
	 *            用户账号
	 * @param password
	 *            用户密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dologin.do")
	public String dologin(HttpServletRequest request) throws Exception {

		// 如果登录失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if (exceptionClassName != null) {
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				throw new CustomException("账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				throw new CustomException("用户名或密码错误");
			} else if ("randomcodeError".equals(exceptionClassName)) {
				throw new CustomException("验证码错误");
			} else {
				throw new Exception();// 最终在异常处理器生成未知错误
			}
		}
		// 此方法不处理登录成功(认证成功)，shiro认证成功会自动跳转到上一个请求路径。
		// 登录失败还到login页面
		return "/login";
	}

	@ResponseBody
	@RequestMapping("/addUser.do")
	public void doAddUser(HttpServletRequest request) throws Exception {
		String account = request.getParameter("account");
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		User user = new User();
		user.setAccount(account);
		user.setUserName(userName);
		user.setPwd(pwd);
		userServiceImpl.addUser(user);
		doGetUserList(request);
	}

	@ResponseBody
	@RequestMapping("/updateUser.do")
	public void doUpdateUser(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setUserName(userName);
		user.setPwd(pwd);
		userServiceImpl.updateUser(user);
		doGetUserList(request);
	}

	@Transactional(noRollbackFor=Exception.class)
	@ResponseBody
	@RequestMapping("/deleteUser.do")
	public void doDeleteUser(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		userServiceImpl.deleteUser(Integer.parseInt(id));
		// 删除该用户的所有角色
		userServiceImpl.deleteUserRole(Integer.parseInt(id));
		doGetUserList(request);
	}

	@ResponseBody
	@RequestMapping("/getUserList.do")
	public void doGetUserList(HttpServletRequest request) throws Exception {
		List userList = userServiceImpl.getUserList();
		request.getSession().setAttribute("userList", userList);
	}

	@Transactional(noRollbackFor=Exception.class)
	@ResponseBody
	@RequestMapping("/updateUserRole.do")
	public void doUpdateUserRole(HttpServletRequest request) throws Exception {
		// 获取上一个请求放在session中存放的用户ID
		String userId = request.getSession().getAttribute("uId").toString();
		// 删除该用户的所有角色
		userServiceImpl.deleteUserRole(Integer.parseInt(userId));
		// 拿到ajax的data中的数组字符串(角色id数组)
		String roleIds = request.getParameter("roleIds");
		// 拆分成数组形式
		String[] role = roleIds.split(",");
		// 根据角色id遍历新增角色
		for (String i : role) {
			UserRole userRole = new UserRole();
			userRole.setUserId(Integer.parseInt(userId));
			userRole.setRoleId(Integer.parseInt(i));
			userServiceImpl.insertUserRole(userRole);
		}
	}

	@ResponseBody
	@RequestMapping("/getUserRoleList.do")
	public void doGetUserRoleList(HttpServletRequest request) throws Exception {
		// 查出所有角色且包含用户拥有角色
		String id = request.getParameter("id");
		List userRoleList = userServiceImpl.getUserRoleList(Integer.parseInt(id));
		request.getSession().setAttribute("userRoleList", userRoleList);
		// 将ID放入session,传给下一个request
		request.getSession().setAttribute("uId", id);
	}

}
