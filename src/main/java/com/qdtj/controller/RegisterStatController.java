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
import com.qdtj.domain.RegisterStatCondition;
import com.qdtj.service.impl.RegisterStatServiceImpl;
import com.qdtj.service.impl.UserServiceImpl;
import com.qdtj.util.PagedResult;

/**
 * 注册统计
 * 
 * @author xuyanlin
 *
 */
@Controller
public class RegisterStatController extends BaseController {

	private static Logger logger = Logger.getLogger(RegisterStatController.class);

	@Resource
	UserServiceImpl userServiceImpl;
	@Resource
	RegisterStatServiceImpl registerStatServiceImpl;

	/**
	 * 页面跳转查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/registerStat.do")
	public String doRegisterStat(HttpServletRequest request) throws Exception {
		return "RegisterStat";
	}
	
	/**
	 * 根据查询条件查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getRegisterByCondition.do")
	public String doRegisterStatByCondition(HttpServletRequest request) throws Exception {
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.QDTJ);
		String userId = request.getSession().getAttribute("userId").toString();
		logger.info("---userId---" + userId);
		List userChannelList = userServiceImpl.getUserChannel(Integer.parseInt(userId));
		request.getSession().setAttribute("userChannelList", userChannelList);
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.S61);
		Map<String, Object> dateMap = new HashMap<String, Object>();
		Map<String, Object> registerSearchMap = new HashMap<String, Object>();
		registerSearchMap.put("dataRegisterAgain", request.getParameter("dataRegisterAgain"));
		registerSearchMap.put("dataRegisterEnd", request.getParameter("dataRegisterEnd"));
		registerSearchMap.put("channelNumber", request.getParameter("channelNumber"));
		registerSearchMap.put("terminalType", request.getParameter("terminalType"));
		registerSearchMap.put("isRealName", request.getParameter("isRealName"));
		registerSearchMap.put("isCheckCard", request.getParameter("isCheckCard"));
		registerSearchMap.put("userName", request.getParameter("userName"));
		registerSearchMap.put("phone", request.getParameter("phone"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		Map<String, Object> registerStatSumMap = (Map<String, Object>) registerStatServiceImpl.getRegisterStatSum(dateMap);
		PagedResult<RegisterStatCondition> pageResult = (PagedResult<RegisterStatCondition>) registerStatServiceImpl.getRegisterStat(registerSearchMap, pageNumber, pageSize);
		pageResult.setSumMap(registerStatSumMap);
 		request.getSession().setAttribute("registerStatConditionList", pageResult.getDataList());
        return responseSuccess(pageResult);
	}

}
