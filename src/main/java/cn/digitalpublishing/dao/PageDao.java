package cn.digitalpublishing.dao;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.util.hql.DaoHelper;
import cn.digitalpublishing.po.SysPage;

public class PageDao extends CommonDao<SysPage, String> {
	
	public List<SysPage> getList(Map<String,Object> condition, String sort, HqlBean hqlBean) throws Exception{
		System.out.println("---hqlBean hql:" + hqlBean.getHql());
		System.out.println("---hqlBean properties:" + hqlBean.getProperties());
		System.out.println("---hqlBean fields:" + hqlBean.getFields());
		System.out.println("---hqlBean params:" + hqlBean.getParams());
		System.out.println("---hqlBean group:" + hqlBean.getGroup());
		System.out.println("---hqlBean order:" + hqlBean.getOrder());
		System.out.println("---hqlBean className:" + hqlBean.getClassName());
		try {
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where "+helper.getWhere(hqlBean.getConditions(), condition);
			List<SysPage> list = hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			return list;
		} catch (Exception re) {
			throw re;
		}
	}
	
	public List<SysPage> getPagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart, HqlBean hqlBean)throws Exception{
		System.out.println("---hqlBean hql:" + hqlBean.getHql());
		System.out.println("---hqlBean properties:" + hqlBean.getProperties());
		System.out.println("---hqlBean fields:" + hqlBean.getFields());
		System.out.println("---hqlBean params:" + hqlBean.getParams());
		System.out.println("---hqlBean group:" + hqlBean.getGroup());
		System.out.println("---hqlBean order:" + hqlBean.getOrder());
		System.out.println("---hqlBean className:" + hqlBean.getClassName());
		try {
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where "+helper.getWhere(hqlBean.getConditions(), condition);
			List<SysPage> list = hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName(), pageCount, countStart);
			return list;
		} catch (Exception re) {
			throw re;
		}
	}
	
	public Integer getCount(Map<String,Object> condition, HqlBean hqlBean)throws Exception{
		System.out.println("---hqlBean hql:" + hqlBean.getHql());
		System.out.println("---hqlBean properties:" + hqlBean.getProperties());
		System.out.println("---hqlBean fields:" + hqlBean.getFields());
		System.out.println("---hqlBean params:" + hqlBean.getParams());
		System.out.println("---hqlBean group:" + hqlBean.getGroup());
		System.out.println("---hqlBean order:" + hqlBean.getOrder());
		System.out.println("---hqlBean className:" + hqlBean.getClassName());
		try {
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where "+helper.getWhere(hqlBean.getConditions(), condition);
			List<SysPage> list = hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			return list==null?0:Integer.valueOf(list.get(0).getId());
		} catch (Exception re) {
			throw re;
		}
	}
	
}
