package com.qdtj.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.qdtj.dao.IChannelManageDao;
import com.qdtj.domain.Channel;
import com.qdtj.service.IChannelManageService;

/**
 * 渠道管理
 * 
 * @author caiang
 *
 */
@Service("channelManageService")
public class ChannelManageServiceImpl implements IChannelManageService {
	@Resource
	private IChannelManageDao channelManageDao;

	@Override
	public void addChannel(Channel channel) {
		channelManageDao.addChannel(channel);
	}

	@Override
	public void updateChannel(Channel channel) {
		channelManageDao.updateChannel(channel);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List getChannelList(String channelName) {
		return channelManageDao.getChannelList(channelName);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List getAllChannelForRole(int roleId) {
		// TODO Auto-generated method stub
		return channelManageDao.getAllChannelForRole(roleId);
	}

}
