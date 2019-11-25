package com.qdtj.base.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter{

	/**
	 * 原AuthenticationFilter验证方法
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		//获取正确的验证码和用户输入的验证码进行比对
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		
		HttpSession session = httpServletRequest.getSession();
		
		//从session获取正确验证码
		String validateCode = (String) session.getAttribute("validateCode");
		//取出页面的验证码
		String randomcode = (String) httpServletRequest.getParameter("randomcode");
		if(validateCode!=null && randomcode!=null && !validateCode.equals(randomcode)){
			//验证码不相同,给shiroLoginFailure属性设置值
			request.setAttribute("shiroLoginFailure","randomcodeError");
			//拒绝访问，不再校验账号和密码
			return true;
		}
		
		return super.onAccessDenied(request, response);
	}
	
}
