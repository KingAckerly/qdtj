package com.qdtj.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qdtj.datasource.DataSourceContextHolder;
import com.qdtj.datasource.DataSourceType;
import com.qdtj.domain.ActiveUser;
import com.qdtj.domain.DataShow;
import com.qdtj.service.impl.DataShowServiceImpl;
/**
 * 数据一览
 * @author caiang
 *
 */
@Controller
public class DataShowController {

	private static Logger logger = Logger.getLogger(DataShowController.class);
	
	@Resource
	DataShowServiceImpl dataShowServiceImpl;
	
	@RequestMapping("/dataShow.do")
	public String doDataShow(HttpServletRequest request)throws Exception{
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.S62);
		
		//数据文件
		DataShow ds = new DataShow();
		ds = dataShowServiceImpl.showData();
		
		//权限文件
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		request.setAttribute("activeUser", activeUser);
		request.setAttribute("dataShow", ds);
		return "dataindex";
	}

}
