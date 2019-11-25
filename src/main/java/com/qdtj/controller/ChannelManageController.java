package com.qdtj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qdtj.datasource.DataSourceContextHolder;
import com.qdtj.datasource.DataSourceType;
import com.qdtj.domain.Channel;
import com.qdtj.domain.DataShow;
import com.qdtj.domain.Role;
import com.qdtj.service.impl.ChannelManageServiceImpl;
import com.qdtj.service.impl.DataShowServiceImpl;

/**
 * 渠道管理
 * 
 * @author caiang
 *
 */
@Controller
public class ChannelManageController {

	private static Logger logger = Logger.getLogger(ChannelManageController.class);

	@Resource
	ChannelManageServiceImpl channelManageServiceImpl;

	@RequestMapping("/addChannel.do")
	public String doAddChannel(HttpServletRequest request) throws Exception {
		String channelName = request.getParameter("channelName");
		String channelNumber = request.getParameter("channelNumber");
		Channel channel = new Channel();
		channel.setChannelName(channelName);
		channel.setChannelNumber(channelNumber);
		channelManageServiceImpl.addChannel(channel);
		List channelList = channelManageServiceImpl.getChannelList(null);
		request.setAttribute("channelList", channelList);
		return "channelManager";
	}
	
	@RequestMapping("/updateChannel.do")
	public String doUpdateChannel(HttpServletRequest request) throws Exception {
		String channelId = request.getParameter("channelId");
		String channelName = request.getParameter("channelName");
		String channelNumber = request.getParameter("channelNumber");
		Channel channel = new Channel();
		channel.setChannelId(Integer.parseInt(channelId));
		channel.setChannelName(channelName);
		channel.setChannelNumber(channelNumber);
		channelManageServiceImpl.updateChannel(channel);
		List channelList = channelManageServiceImpl.getChannelList(null);
		request.setAttribute("channelList", channelList);
		return "channelManager";
	}
	
	@RequestMapping("/getChannelList.do")
	public String doGetChannelList(HttpServletRequest request) throws Exception {
		// 切换数据库
		DataSourceContextHolder.setDbType(DataSourceType.QDTJ);
		String channelName = request.getParameter("channelName");
		List channelList = channelManageServiceImpl.getChannelList(channelName);
		request.setAttribute("channelList", channelList);
		logger.info(channelList);
		return "channelManager";
	}
}
