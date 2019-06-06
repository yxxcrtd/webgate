package cn.digitalpublishing.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import cn.com.daxtech.framework.orm.hibernate3.dao.IHibernateDAO;
import cn.com.daxtech.framework.util.DataCast;

public class BaseDao<T, ID extends Serializable> {

	protected IHibernateDAO<T, ID> hibernateDao;

	public IHibernateDAO<T, ID> getHibernateDao() {
		return hibernateDao;
	}

	public void setHibernateDao(IHibernateDAO<T, ID> hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	public Vector<Properties> getVector(String hql, Object[] condition,
			String sort, String className) throws Exception {
		Vector<Properties> v = new Vector<Properties>();
		try {
			List<T> list = this.hibernateDao.getListByHql("id,name", hql,
					condition, sort, className);
			List<Object> l = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++) {
				l.add(list.get(i));
			}
			v = DataCast.getVector(l, "name", "id");
		} catch (Exception e) {
			throw e;
		}
		return v;
	}

	public Vector<Properties> getVector(String property, String field,
			String hql, Object[] condition, String sort, String className)
			throws Exception {
		Vector<Properties> v = new Vector<Properties>();
		try {
			List<T> list = this.hibernateDao.getListByHql(property, field, hql,
					condition, sort, className);
			List<Object> l = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++) {
				l.add(list.get(i));
			}
			v = DataCast.getVector(l, "name", "id");
		} catch (Exception e) {
			throw e;
		}
		return v;
	}

	/**
	 * 根据query进行查询
	 * 
	 * @param query
	 * @return List
	 */
	/*public XmlToHqlBeanBuilder getHqlBeanByQueryId(String queryId) throws Exception {
		try {
			//XmlToHqlBeanBuilder x = XmlToHqlBeanBuilder.getInstance();
			//List<XmlToHqlBeanBuilder> hqlList = x.getHqlList();
			//XmlToHqlBeanBuilder hqlBean = XmlToHqlBeanBuilder.find(hqlList, queryId);
			//return hqlBean;
			return null;
		} catch (Exception re) {
			throw re;
		}
	}*/

	/**
	 * 根据query进行查询
	 * 
	 * @param query
	 * @return List
	 */
	/*public HqlBean getHqlBeanById(String queryId) throws Exception {
		try {
			String daoName = this.getClass().getName();
			Map<String, HqlBean> queryMap = HqlBeanCacheUtil.hqlBeanCache.get(daoName);
			HqlBean hqlBean = queryMap.get(queryId);
			System.out.println(hqlBean);
			//List<XmlToHqlBeanBuilder> hqlList = x.getHqlList();
			//XmlToHqlBeanBuilder hqlBean = XmlToHqlBeanBuilder.find(hqlList, queryId);
			//return hqlBean;
			return hqlBean;
		} catch (Exception ex) {
			throw ex;
		}
	}*/
}
