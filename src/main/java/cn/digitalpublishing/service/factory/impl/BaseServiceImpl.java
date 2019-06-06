package cn.digitalpublishing.service.factory.impl;

import java.util.Properties;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.digitalpublishing.facade.DaoFacade;
import cn.digitalpublishing.service.BaseService;

public class BaseServiceImpl implements BaseService {
	
	protected DaoFacade daoFacade;

	public DaoFacade getDaoFacade() {
		return daoFacade;
	}

	public void setDaoFacade(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}

	public Vector<Properties> getVector(String hql, Object[] condition,
			String sort, String className) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Properties> getVector(String property, String field,
			String hql, Object[] condition, String sort, String className)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
