package com.qdtj.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.qdtj.dao.IDataShowDao;
import com.qdtj.domain.DataShow;
import com.qdtj.service.IDataShowService;

/**
 * 数据一览
 * 
 * @author caiang
 *
 */
@Service("dataShowService")
public class DataShowServiceImpl implements IDataShowService {

	@Resource
	private IDataShowDao dataShowDao;

	@Override
	public DataShow showData() {
		return dataShowDao.showData();
	}

}
