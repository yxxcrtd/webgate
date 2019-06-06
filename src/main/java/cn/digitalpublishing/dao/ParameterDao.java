package cn.digitalpublishing.dao;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.util.Log;
import cn.com.daxtech.framework.util.hql.DaoHelper;
import cn.digitalpublishing.po.SysParameter;

public class ParameterDao extends CommonDao<SysParameter, String> {
	
	public List<SysParameter> getList(Map<String,Object> condition, String sort, HqlBean hqlBean) throws Exception{
		Log.printInfo("---hqlBean hql:" + hqlBean.getHql());
		Log.printInfo("---hqlBean properties:" + hqlBean.getProperties());
		Log.printInfo("---hqlBean fields:" + hqlBean.getFields());
		Log.printInfo("---hqlBean params:" + hqlBean.getParams());
		Log.printInfo("---hqlBean group:" + hqlBean.getGroup());
		Log.printInfo("---hqlBean order:" + hqlBean.getOrder());
		Log.printInfo("---hqlBean className:" + hqlBean.getClassName());
		try {
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where "+helper.getWhere(hqlBean.getConditions(), condition);
			List<SysParameter> list = hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			return list;
		} catch (Exception re) {
			throw re;
		}
	}
	
	public List<SysParameter> getPagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart, HqlBean hqlBean)throws Exception{
		Log.printInfo("---hqlBean hql:" + hqlBean.getHql());
		Log.printInfo("---hqlBean properties:" + hqlBean.getProperties());
		Log.printInfo("---hqlBean fields:" + hqlBean.getFields());
		Log.printInfo("---hqlBean params:" + hqlBean.getParams());
		Log.printInfo("---hqlBean group:" + hqlBean.getGroup());
		Log.printInfo("---hqlBean order:" + hqlBean.getOrder());
		Log.printInfo("---hqlBean className:" + hqlBean.getClassName());
		try {
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where "+helper.getWhere(hqlBean.getConditions(), condition);
			List<SysParameter> list = hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName(), pageCount, countStart);
			return list;
		} catch (Exception re) {
			throw re;
		}
	}
	
	public Integer getCount(Map<String,Object> condition, HqlBean hqlBean)throws Exception{
		Log.printInfo("---hqlBean hql:" + hqlBean.getHql());
		Log.printInfo("---hqlBean properties:" + hqlBean.getProperties());
		Log.printInfo("---hqlBean fields:" + hqlBean.getFields());
		Log.printInfo("---hqlBean params:" + hqlBean.getParams());
		Log.printInfo("---hqlBean group:" + hqlBean.getGroup());
		Log.printInfo("---hqlBean order:" + hqlBean.getOrder());
		Log.printInfo("---hqlBean className:" + hqlBean.getClassName());
		try {
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where "+helper.getWhere(hqlBean.getConditions(), condition);
			List<SysParameter> list = hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			return list==null?0:Integer.valueOf(list.get(0).getId());
		} catch (Exception re) {
			throw re;
		}
	}
	
}