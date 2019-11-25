package com.qdtj.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.qdtj.dao.IRegisterStatDao;
import com.qdtj.service.IRegisterStatService;
import com.qdtj.util.BeanUtil;
import com.qdtj.util.PagedResult;

/**
 * 注册统计
 * 
 * @author xuyanlin
 *
 */
@Service("registerStatService")
public class RegisterStatServiceImpl implements IRegisterStatService {

	@Resource
	private IRegisterStatDao registerStatDao;

	@Override
	public Map<?, ?> getRegisterStatSum(Map<?, ?> dateMap) {
		// TODO Auto-generated method stub
		return registerStatDao.getRegisterStatSum(dateMap);
	}

	@Override
	public PagedResult<?> getRegisterStat(Map<?, ?> registerSearchMap, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null?1:pageNo;  
	    pageSize = pageSize == null?10:pageSize;  
	    PageHelper.startPage(pageNo,pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。  
	    return BeanUtil.toPagedResult(registerStatDao.getRegisterStat(registerSearchMap));
//		return registerStatDao.getRegisterStat(registerSearchMap);
	}


}
