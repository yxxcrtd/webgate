package cn.digitalpublishing.dao;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.util.Log;
import cn.com.daxtech.framework.util.hql.DaoHelper;
import cn.digitalpublishing.po.SysParameter;
import cn.digitalpublishing.po.SysRoleFunctionRelationship;
import cn.digitalpublishing.po.SysRoleResourceRelationship;

public class RoleFunctionRelationshipDao extends CommonDao<SysRoleFunctionRelationship, String> {
	
	public void delByWhere(Map<String,Object> condition, HqlBean hqlBean)throws Exception{
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
			//Map<String,Object> t=this.getWhere(condition);
			//this.hibernateDao.executeHql("delete from BWordTypeRelationship a "+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray());
			hibernateDao.executeHql(hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray());
		} catch (Exception e) {
			throw e;
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
			List<SysRoleFunctionRelationship> list = hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			return list==null?0:Integer.valueOf(list.get(0).getId());
		} catch (Exception re) {
			throw re;
		}
	}
	
	public List<SysRoleFunctionRelationship> getList(Map<String,Object> condition, String sort, HqlBean hqlBean) throws Exception{
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
			List<SysRoleFunctionRelationship> list = hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			return list;
		} catch (Exception re) {
			throw re;
		}
	}

}
