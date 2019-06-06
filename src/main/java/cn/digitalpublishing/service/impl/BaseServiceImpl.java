package cn.digitalpublishing.service.impl;

import java.util.Properties;
import java.util.Vector;

import cn.com.daxtech.framework.exception.CcsException;
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
		Vector<Properties> v = new Vector<Properties>();
		try {
			v = this.daoFacade.getSystemDao().getVector(hql, condition, sort,
					className);
		} catch (Exception e) {
			throw new CcsException("获取信息列表失败！", e);
		}
		return v;
	}

	public Vector<Properties> getVector(String property, String field,
			String hql, Object[] condition, String sort, String className)
			throws Exception {
		Vector<Properties> v = new Vector<Properties>();
		try {
			v = this.daoFacade.getSystemDao().getVector(property, field, hql,
					condition, sort, className);
		} catch (Exception e) {
			throw new CcsException("获取信息列表失败！", e);
		}
		return v;
	}

}
