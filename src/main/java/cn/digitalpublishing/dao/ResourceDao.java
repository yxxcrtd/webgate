package cn.digitalpublishing.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.util.hql.DaoHelper;
import cn.digitalpublishing.po.SysResource;

public class ResourceDao extends CommonDao<SysResource, Serializable>{
	
	public List<SysResource> getPagingList(Map<String,Object> condition,String sort, Integer pageCount, Integer countStart, HqlBean hqlBean) throws Exception{
		System.out.println("---hqlBean hql:" + hqlBean.getHql());
		System.out.println("---hqlBean properties:" + hqlBean.getProperties());
		System.out.println("---hqlBean fields:" + hqlBean.getFields());
		System.out.println("---hqlBean params:" + hqlBean.getParams());
		System.out.println("---hqlBean group:" + hqlBean.getGroup());
		System.out.println("---hqlBean order:" + hqlBean.getOrder());
		System.out.println("---hqlBean className:" + hqlBean.getClassName());
		try {
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(),condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(),condition))) ? helper.getWhere(hqlBean.getConditions(),condition) : " where "+helper.getWhere(hqlBean.getConditions(),condition);
		    List<SysResource> list = hibernateDao.getListByHql(helper.getProperty(hqlBean.getPropertyArray(), condition), helper.getField(hqlBean.getFieldArray(), condition), hqlBean.getHql()+where, helper.getCondition()==null?null:helper.getCondition().toArray(), hqlBean.getOrder(),hqlBean.getClassName(), pageCount, countStart);
		    return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<SysResource> getList(Map<String,Object> condition, String sort, HqlBean hqlBean) throws Exception{
		System.out.println("---hqlBean hql:" + hqlBean.getHql());
		System.out.println("---hqlBean properties:" + hqlBean.getProperties());
		System.out.println("---hqlBean fields:" + hqlBean.getFields());
		System.out.println("---hqlBean params:" + hqlBean.getParams());
		System.out.println("---hqlBean group:" + hqlBean.getGroup());
		System.out.println("---hqlBean order:" + hqlBean.getOrder());
		System.out.println("---hqlBean className:" + hqlBean.getClassName());
		try {
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where " + helper.getWhere(hqlBean.getConditions(), condition);
			List<SysResource> list = hibernateDao.getListByHql(helper.getProperty(hqlBean.getPropertyArray(), condition), helper.getField(hqlBean.getFieldArray(), condition), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	public Integer getCount(Map<String,Object> condition,HqlBean hqlBean) throws Exception {
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
			List<SysResource> list = hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			return list == null ? 0 :Integer.valueOf(list.get(0).getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<SysResource> getResourceListByRoleId(Map<String,Object> condition, String sort, HqlBean hqlBean) throws Exception {
		System.out.println("---hqlBean hql:" + hqlBean.getHql());
		System.out.println("---hqlBean properties:" + hqlBean.getProperties());
		System.out.println("---hqlBean fields:" + hqlBean.getFields());
		System.out.println("---hqlBean params:" + hqlBean.getParams());
		System.out.println("---hqlBean group:" + hqlBean.getGroup());
		System.out.println("---hqlBean order:" + hqlBean.getOrder());
		System.out.println("---hqlBean className:" + hqlBean.getClassName());
		try {
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where " + helper.getWhere(hqlBean.getConditions(), condition);
			List<SysResource> list = hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 获得下一个Order值
	 * @param sysId
	 * @param menuId
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer getNextOrder(Map<String,Object> condition,String sort,HqlBean hqlBean)throws Exception{
		System.out.println("---hqlBean hql:" + hqlBean.getHql());
		System.out.println("---hqlBean properties:" + hqlBean.getProperties());
		System.out.println("---hqlBean fields:" + hqlBean.getFields());
		System.out.println("---hqlBean params:" + hqlBean.getParams());
		System.out.println("---hqlBean group:" + hqlBean.getGroup());
		System.out.println("---hqlBean order:" + hqlBean.getOrder());
		System.out.println("---hqlBean className:" + hqlBean.getClassName());
		Integer order=1;
		try{
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where " + helper.getWhere(hqlBean.getConditions(), condition);
			List<SysResource> list=hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			order=((list==null||list.isEmpty())?1:list.get(0)==null?1:(list.get(0).getOrder()+1));
		}catch(Exception e){
			throw e;
		}
		return order;
	}
	/**
	 * 获取下一个最大Code值
	 * @param sysId
	 * @param menuId
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public String getNextCode(Map<String,Object> condition,String sort,HqlBean hqlBean)throws Exception{
		System.out.println("---hqlBean hql:" + hqlBean.getHql());
		System.out.println("---hqlBean properties:" + hqlBean.getProperties());
		System.out.println("---hqlBean fields:" + hqlBean.getFields());
		System.out.println("---hqlBean params:" + hqlBean.getParams());
		System.out.println("---hqlBean group:" + hqlBean.getGroup());
		System.out.println("---hqlBean order:" + hqlBean.getOrder());
		System.out.println("---hqlBean className:" + hqlBean.getClassName());
		String code="";
		try{
			String parCode="";
			String resourceId = condition.get("resourceId").toString();
			if(resourceId!=null&&!"".equals(resourceId.trim())&&!"0".equals(resourceId)){
				SysResource obj=(SysResource)this.hibernateDao.getById(SysResource.class.getName(),resourceId);
				parCode=obj.getCode();
			}
//			List<SysResource> list=null;
//			Map<String,Object> t=getWhere(menuId,null,sysId,null);
//			String hql=" from SysResource a left join a.menu b ";
//			String _hql = hql+t.get("where").toString();
//			list=this.hibernateDao.getListByHql("code","a.code", _hql, ((List)t.get("condition")).toArray(),sort, SysResource.class.getName(),1,0);
			DaoHelper helper = new DaoHelper();
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where " + helper.getWhere(hqlBean.getConditions(), condition);
			List<SysResource> list=hibernateDao.getListByHql(hqlBean.getProperties(), hqlBean.getFields(), hqlBean.getHql()+where,helper.getCondition()==null?null:helper.getCondition().toArray(),hqlBean.getOrder(), hqlBean.getClassName());
			code=((list==null||list.isEmpty())?"001":list.get(0)==null?"001":list.get(0).getCode());
			if(list!=null&&!list.isEmpty()){
				if(parCode.trim().length()>0){
					code=code.substring(parCode.length());
				}
				int num=Integer.valueOf(code).intValue();
				num++;
				String mid=String.valueOf(num);
				if(mid.length()==1){
					code="00"+mid;
				}
				if(mid.length()==2){
					code="0"+mid;
				}
				if(mid.length()==3){
					code=mid;
				}
			}
			code=parCode+code;
		}catch(Exception e){
			throw e;
		}
		return code;
	}
	
	
	public void updateByHql(Map<String,Object> condition,HqlBean hqlBean)throws Exception{
		System.out.println("---hqlBean hql:" + hqlBean.getHql());
		System.out.println("---hqlBean properties:" + hqlBean.getProperties());
		System.out.println("---hqlBean fields:" + hqlBean.getFields());
		System.out.println("---hqlBean params:" + hqlBean.getParams());
		System.out.println("---hqlBean group:" + hqlBean.getGroup());
		System.out.println("---hqlBean order:" + hqlBean.getOrder());
		System.out.println("---hqlBean className:" + hqlBean.getClassName());
		try{
			DaoHelper helper = new DaoHelper();
			
			String where = (helper.getWhere(hqlBean.getConditions(), condition) != null && "".equals(helper.getWhere(hqlBean.getConditions(), condition))) ? helper.getWhere(hqlBean.getConditions(), condition) : " where " + helper.getWhere(hqlBean.getConditions(), condition);
			hibernateDao.executeHql(hqlBean.getHql()+helper.getField(hqlBean.getFieldArray(), condition)+where, helper.getCondition()==null?null:helper.getCondition().toArray());
		}catch(Exception e){
			throw e;
		}
	}

}
