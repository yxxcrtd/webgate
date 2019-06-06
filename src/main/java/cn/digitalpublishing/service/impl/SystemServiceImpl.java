package cn.digitalpublishing.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.digitalpublishing.po.SysAccount;
import cn.digitalpublishing.po.SysComponent;
import cn.digitalpublishing.po.SysFunction;
import cn.digitalpublishing.po.SysModule;
import cn.digitalpublishing.po.SysPage;
import cn.digitalpublishing.po.SysParameter;
import cn.digitalpublishing.po.SysResource;
import cn.digitalpublishing.po.SysRoleAccountRelationship;
import cn.digitalpublishing.po.SysRole;
import cn.digitalpublishing.po.SysRoleFunctionRelationship;
import cn.digitalpublishing.po.SysRoleResourceRelationship;
import cn.digitalpublishing.po.SysSystem;
import cn.digitalpublishing.service.SystemService;

public class SystemServiceImpl extends BaseServiceImpl implements SystemService {
	
	/****System Start************************************************************************************************************************/
	
	public List<SysSystem> getSystemPagingList(Map<String, Object> condition,
			String sort, Integer pageCount, Integer countStart) throws Exception {
		List<SysSystem> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.SystemDao").get("getPagingList");
			list = daoFacade.getSystemDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "System.Info.Paging.Error", e);
		}
		return list;
	}
	
	public List<SysSystem> getSystemList(Map<String, Object> condition,
			String sort) throws Exception {
		List<SysSystem> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.SystemDao").get("getList");
			list = daoFacade.getSystemDao().getList(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "System.Info.List.Error", e);
		}
		return list;
	}

	public Integer getSystemCount(Map<String, Object> condition)
			throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.SystemDao").get("getCount");
			num = daoFacade.getSystemDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "System.Info.Count.Error", e);
		}
		return num;
	}

	public SysSystem getSystemById(String id) throws Exception {
		SysSystem system = null;
		try {
			system = (SysSystem) this.daoFacade.getSystemDao().get(SysSystem.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "System.Info.Get.Error", e);
		}
		return system;
	}

	public void updateSystem(SysSystem obj, String id, String[] properties)
			throws Exception {
		try {
			this.daoFacade.getSystemDao().update(obj, SysSystem.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "System.Info.Update.Error", e);
		}
	}

	public void insertSystem(SysSystem obj) throws Exception {
		try {
			this.daoFacade.getSystemDao().insert(obj);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "System.Info.Add.Error", e);
		}
	}

	public void deleteSystemById(String id) throws Exception {
		try {
			this.daoFacade.getSystemDao().delete(SysSystem.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "System.Info.Delete.Error", e);
		}
	}
	
	/****System End************************************************************************************************************************/
	
	/****Module Start************************************************************************************************************************/
	
	public List<SysModule> getModulePagingList(Map<String, Object> condition,
			String sort, Integer pageCount, Integer countStart) throws Exception {
		List<SysModule> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ModuleDao").get("getPagingList");
			list = daoFacade.getModuleDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Module.Info.Paging.Error", e);
		}
		return list;
	}
	
	public List<SysModule> getModuleList(Map<String, Object> condition,
			String sort) throws Exception {
		List<SysModule> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ModuleDao").get("getList");
			list = daoFacade.getModuleDao().getList(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Module.Info.List.Error", e);
		}
		return list;
	}

	public Integer getModuleCount(Map<String, Object> condition)
			throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ModuleDao").get("getCount");
			num = daoFacade.getModuleDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Module.Info.Count.Error", e);
		}
		return num;
	}

	public SysModule getModuleById(String id) throws Exception {
		SysModule module = null;
		try {
			module = (SysModule) this.daoFacade.getModuleDao().get(SysModule.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Module.Info.Get.Error", e);
		}
		return module;
	}

	public void updateModule(SysModule obj, String id, String[] properties)
			throws Exception {
		try {
			this.daoFacade.getModuleDao().update(obj, SysModule.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Module.Info.Update.Error", e);
		}
	}

	public void insertModule(SysModule obj) throws Exception {
		try {
			this.daoFacade.getModuleDao().insert(obj);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Module.Info.Add.Error", e);
		}
	}

	public void deleteModuleById(String id) throws Exception {
		try {
			this.daoFacade.getModuleDao().delete(SysModule.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Module.Info.Delete.Error", e);
		}
	}
	
	/****Module End************************************************************************************************************************/
	
	/****Component Start************************************************************************************************************************/
	
	public List<SysComponent> getComponentPagingList(Map<String, Object> condition,
			String sort, Integer pageCount, Integer countStart) throws Exception {
		List<SysComponent> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ComponentDao").get("getPagingList");
			list = daoFacade.getComponentDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Component.Info.Paging.Error", e);
		}
		return list;
	}
	
	public List<SysComponent> getComponentList(Map<String, Object> condition,
			String sort) throws Exception {
		List<SysComponent> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ComponentDao").get("getList");
			list = daoFacade.getComponentDao().getList(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Component.Info.List.Error", e);
		}
		return list;
	}

	public Integer getComponentCount(Map<String, Object> condition)
			throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ComponentDao").get("getCount");
			num = daoFacade.getComponentDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Component.Info.Count.Error", e);
		}
		return num;
	}

	public SysComponent getComponentById(String id) throws Exception {
		SysComponent component = null;
		try {
			component = (SysComponent) this.daoFacade.getComponentDao().get(SysComponent.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Component.Info.Get.Error", e);
		}
		return component;
	}

	public void updateComponent(SysComponent obj, String id, String[] properties)
			throws Exception {
		try {
			this.daoFacade.getComponentDao().update(obj, SysComponent.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Component.Info.Update.Error", e);
		}
	}

	public void insertComponent(SysComponent obj) throws Exception {
		try {
			this.daoFacade.getComponentDao().insert(obj);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Component.Info.Add.Error", e);
		}
	}

	public void deleteComponentById(String id) throws Exception {
		try {
			this.daoFacade.getComponentDao().delete(SysComponent.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Component.Info.Delete.Error", e);
		}
	}
	
	/****Component End************************************************************************************************************************/
	
	/****Account Start************************************************************************************************************************/
	
	public List<SysAccount> getAccountPagingList(Map<String, Object> condition,
			String sort, Integer pageCount, Integer countStart) throws Exception {
		List<SysAccount> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AccountDao").get("getPagingList");
			list = daoFacade.getAccountDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Paging.Error", e);
		}
		return list;
	}
	
	public List<SysAccount> getAccountList(Map<String, Object> condition,
			String sort) throws Exception {
		List<SysAccount> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AccountDao").get("getList");
			list = daoFacade.getAccountDao().getList(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.List.Error", e);
		}
		return list;
	}

	public Integer getAccountCount(Map<String, Object> condition)
			throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AccountDao").get("getCount");
			num = daoFacade.getAccountDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Count.Error", e);
		}
		return num;
	}

	public SysAccount getAccountById(String id) throws Exception {
		SysAccount account = null;
		try {
			account = (SysAccount) this.daoFacade.getAccountDao().get(SysAccount.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Get.Error", e);
		}
		return account;
	}

	public void updateAccount(SysAccount obj, String id, String[] properties)
			throws Exception {
		try {
			this.daoFacade.getAccountDao().update(obj, SysAccount.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Update.Error", e);
		}
	}

	public void insertAccount(SysAccount obj) throws Exception {
		try {
			this.daoFacade.getAccountDao().insert(obj);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Add.Error", e);
		}
	}

	public void deleteAccountById(String id) throws Exception {
		try {
			this.daoFacade.getAccountDao().delete(SysAccount.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Delete.Error", e);
		}
	}

	public SysAccount login(Map<String, Object> condition, String sort, boolean isEncrypt)
			throws Exception {
		SysAccount obj = null;
		try {
			if(isEncrypt){
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				condition.put("password", md5.encodePassword((String)condition.get("password"), (String)condition.get("username")));
			}
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AccountDao").get("getByUidAndPwd");
			obj = this.daoFacade.getAccountDao().getByUidAndPwd(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.GetByUidAndPwd.Error", e);
		}
		return obj;
	}
	
	/**
	 * 获取账户对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysAccount getAccountByEmpId(String id) throws Exception{
		SysAccount obj = null;
		try {
			Map<String, Object> condition = new HashMap<String,Object>();
			condition.put("employeeId",id);
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AccountDao").get("getByEmpId");
			obj = this.daoFacade.getAccountDao().getByUidAndPwd(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.GetByEmpId.Error", e);
		}
		return obj;
	}
	
	/****Account End************************************************************************************************************************/
	
	/****RoleAccountRelationship Start************************************************************************************************************************/

	public List<SysRoleAccountRelationship> getRoleAccountRelationshipListByAccountId(Map<String, Object> condition, String sort) throws Exception {
		List<SysRoleAccountRelationship> roleAccountRelationshipList = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleAccountRelationshipDao").get("getRoleAccountRelationshipListByAccountId");
			roleAccountRelationshipList = this.daoFacade.getRoleAccountRelationshipDao().getRoleAccountRelationshipListByAccountId(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException("Account.Info.GetRoleAccountRelationshipListByAccountId.Error", e);
		}
		return roleAccountRelationshipList;
	}

	public Integer getRoleAccountRelationshipCountByRelationId(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleAccountRelationshipDao").get("getCount");
			num = this.daoFacade.getRoleAccountRelationshipDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Count.Error", e);
		}
		return num;
	}

	public void batchSaveRoleAccountRelationship(Object[] allId, Object[] sela, String id)
			throws Exception {
		try {
			//根据allID先进行删除
			if(allId!=null&&allId.length>0){
				for(int i=0;i<allId.length;i++){
					Map<String,Object> condition = new HashMap<String, Object>();
					condition.put("roleId", allId[i].toString());
					condition.put("accountId", id);
					HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleAccountRelationshipDao").get("delByWhere");
					this.daoFacade.getRoleAccountRelationshipDao().delByWhere(condition, hqlBean);
				}
			}
			//在根据选择的id进行插入
			if(sela!=null&&sela.length>0){
				for(int i=0;i<sela.length;i++){
					SysRoleAccountRelationship obj = new SysRoleAccountRelationship();
					SysRole role = new SysRole();
					SysAccount account = new SysAccount();
					role.setId(sela[i].toString());
					account.setId(id);
					obj.setAccount(account);
					obj.setRole(role);
					this.daoFacade.getRoleAccountRelationshipDao().insert(obj);
				}
			}
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "WordTypeRelationship.info.batch.save.error", e);//为关键词设置类型失败！
		}
	}
	
	/****RoleAccountRelationship End************************************************************************************************************************/
	
	/****RoleFunctionRelationship Start************************************************************************************************************************/

	public Integer getRoleFunctionRelationshipCountByRelationId(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleFunctionRelationshipDao").get("getCount");
			num = this.daoFacade.getRoleFunctionRelationshipDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Count.Error", e);
		}
		return num;
	}
	
	public void batchSaveRoleFunctionRelationship(Object[] allId, Object[] sela, String id) throws Exception {
		try {
			//根据allID先进行删除
			if(allId!=null&&allId.length>0){
				for(int i=0;i<allId.length;i++){
					Map<String,Object> condition = new HashMap<String, Object>();
					condition.put("functionId", allId[i].toString());
					condition.put("roleId", id);
					HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleFunctionRelationshipDao").get("delByWhere");
					this.daoFacade.getRoleFunctionRelationshipDao().delByWhere(condition, hqlBean);
				}
			}
			//在根据选择的id进行插入
			if(sela!=null&&sela.length>0){
				for(int i=0;i<sela.length;i++){
					SysRoleFunctionRelationship obj = new SysRoleFunctionRelationship();
					SysRole role = new SysRole();
					SysFunction function = new SysFunction();
					function.setId(sela[i].toString());
					role.setId(id);
					obj.setRole(role);
					obj.setFunction(function);
					this.daoFacade.getRoleFunctionRelationshipDao().insert(obj);
					
				}
			}
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "WordTypeRelationship.info.batch.save.error", e);//为关键词设置类型失败！
		}
		
	}
	
	/****RoleFunctionRelationship End************************************************************************************************************************/
	
	/****RoleResourceRelationship Start************************************************************************************************************************/

	public Integer getRoleResourceRelationshipCountByRelationId(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleResourceRelationshipDao").get("getCount");
			num = this.daoFacade.getRoleResourceRelationshipDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Count.Error", e);
		}
		return num;
	}
	
	public void batchSaveRoleResourceRelationship(Object[] allId, Object[] sela, String id)
			throws Exception {
		try {
			//根据allID先进行删除
			if(allId!=null&&allId.length>0){
				for(int i=0;i<allId.length;i++){
					Map<String,Object> condition = new HashMap<String, Object>();
					condition.put("roleId", allId[i].toString());
					condition.put("resourceId", id);
					HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleResourceRelationshipDao").get("delByWhere");
					this.daoFacade.getRoleResourceRelationshipDao().delByWhere(condition, hqlBean);
				}
			}
			//在根据选择的id进行插入
			if(sela!=null&&sela.length>0){
				for(int i=0;i<sela.length;i++){
					SysRoleResourceRelationship obj = new SysRoleResourceRelationship();
					SysRole role = new SysRole();
					SysResource resource = new SysResource();
					role.setId(sela[i].toString());
					resource.setId(id);
					obj.setResource(resource);
					obj.setRole(role);
					this.daoFacade.getRoleResourceRelationshipDao().insert(obj);
				}
			}
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "WordTypeRelationship.info.batch.save.error", e);//为关键词设置类型失败！
		}
	}

	public void batchSaveRoleResourceRelationship(Object[] sela, String id)
			throws Exception {
		try {
			//删除原有的资源设置			
			Map<String,Object> condition = new HashMap<String, Object>();
			condition.put("roleId", id);
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleResourceRelationshipDao").get("delByWhere");
			this.daoFacade.getRoleResourceRelationshipDao().delByWhere(condition, hqlBean);
			
			//在根据选择的id进行插入
			if(sela!=null&&sela.length>0){
				for(int i=0;i<sela.length;i++){
					SysRoleResourceRelationship obj = new SysRoleResourceRelationship();
					SysRole role = new SysRole();
					SysResource resource = new SysResource();
					resource.setId(sela[i].toString());
					role.setId(id);
					obj.setResource(resource);
					obj.setRole(role);
					this.daoFacade.getRoleResourceRelationshipDao().insert(obj);
				}
			}
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "WordTypeRelationship.info.batch.save.error", e);//为关键词设置类型失败！
		}
	}
	
	/****RoleResourceRelationship End************************************************************************************************************************/

	/****AccountModuleRelationship Start************************************************************************************************************************/

	public Integer getAccountModuleRelationshipCountByRelationId(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AccountModuleRelationshipDao").get("getCount");
			num = this.daoFacade.getAccountModuleRelationshipDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Count.Error", e);
		}
		return num;
	}
	
	/****AccountModuleRelationship End************************************************************************************************************************/
	
	/****Role Start************************************************************************************************************************/
	
	public List<SysRole> getRolePagingList(Map<String, Object> condition,
			String sort, Integer pageCount, Integer countStart)
			throws Exception {
		List<SysRole> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleDao").get("getPagingList");
			list = daoFacade.getRoleDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Role.Info.Paging.Error", e);
		}
		return list;
	}

	public List<SysRole> getRoleList(Map<String, Object> condition, String sort)
			throws Exception {
		List<SysRole> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleDao").get("getList");
			list = daoFacade.getRoleDao().getList(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Role.Info.List.Error", e);
		}
		return list;
	}

	public Integer getRoleCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleDao").get("getCount");
			num = daoFacade.getRoleDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Role.Info.Count.Error", e);
		}
		return num;
	}

	public SysRole getRoleById(String id) throws Exception {
		SysRole obj = null;
		try {
			obj = (SysRole) this.daoFacade.getRoleDao().get(SysRole.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Role.Info.Get.Error", e);
		}
		return obj;
	}

	public void updateRole(SysRole obj, String id, String[] properties)
			throws Exception {
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleDao").get("getCount");
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("name", obj.getName());
			condition.put("uniqueId", id);
			if(daoFacade.getRoleDao().getCount(condition, hqlBean)>0){
				throw new CcsException("role.name.duplicate.ban.update");
			}
			this.daoFacade.getRoleDao().update(obj, SysRole.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Role.Info.Update.Error", e);
		}
		
	}

	public void insertRole(SysRole obj) throws Exception {
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleDao").get("getCount");
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("name", obj.getName());
			if(daoFacade.getRoleDao().getCount(condition, hqlBean)>0){
				throw new CcsException("role.name.duplicate.ban.insert");
			}
			this.daoFacade.getRoleDao().insert(obj);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Role.Info.Add.Error", e);
		}
	}

	public void deleteRoleById(String id) throws Exception {
		try {
			this.daoFacade.getRoleDao().delete(SysRole.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Role.Info.Delete.Error", e);
		}
	}
	
	/****Role End**************************************************************************************************************************/
	
	/****Function Start************************************************************************************************************************/

	public List<SysFunction> getFunctionPagingList(
			Map<String, Object> condition, String sort, Integer pageCount,
			Integer countStart) throws Exception {
		List<SysFunction> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.FunctionDao").get("getPagingList");
			list = daoFacade.getFunctionDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "SyFunctionnfo.Paging.Error", e);
		}
		return list;
	}

	public List<SysFunction> getFunctionList(Map<String, Object> condition,
			String sort) throws Exception {
		List<SysFunction> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.FunctionDao").get("getList");
			list = daoFacade.getFunctionDao().getList(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Function.Info.List.Error", e);
		}
		return list;
	}

	public Integer getFunctionCount(Map<String, Object> condition)
			throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.FunctionDao").get("getCount");
			num = daoFacade.getFunctionDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Function.Info.Count.Error", e);
		}
		return num;
	}

	public SysFunction getFunctionById(String id) throws Exception {
		SysFunction function = null;
		try {
			function = (SysFunction) this.daoFacade.getFunctionDao().get(SysFunction.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Function.Info.Get.Error", e);
		}
		return function;
	}

	public void updateFunction(SysFunction obj, String id, String[] properties)
			throws Exception {
		try {
			this.daoFacade.getFunctionDao().update(obj, SysFunction.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Function.Info.Update.Error", e);
		}
	}

	public void insertFunction(SysFunction obj) throws Exception {
		try {
			this.daoFacade.getFunctionDao().insert(obj);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Function.Info.Add.Error", e);
		}
	}

	public void deleteFunctionById(String id) throws Exception {
		try {
			this.daoFacade.getFunctionDao().delete(SysFunction.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Function.Info.Delete.Error", e);
		}
	}
	
	/****Function End**************************************************************************************************************************/
	
	/****Page Start************************************************************************************************************************/

	public List<SysPage> getPagePagingList(Map<String, Object> condition,
			String sort, Integer pageCount, Integer countStart)
			throws Exception {
		List<SysPage> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.PageDao").get("getPagingList");
			list = daoFacade.getPageDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "SyPagenfo.Paging.Error", e);
		}
		return list;
	}

	public List<SysPage> getPageList(Map<String, Object> condition, String sort)
			throws Exception {
		List<SysPage> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.PageDao").get("getList");
			list = daoFacade.getPageDao().getList(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Page.Info.List.Error", e);
		}
		return list;
	}

	public Integer getPageCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.PageDao").get("getCount");
			num = daoFacade.getPageDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Page.Info.Count.Error", e);
		}
		return num;
	}

	public SysPage getPageById(String id) throws Exception {
		SysPage page = null;
		try {
			page = (SysPage) this.daoFacade.getPageDao().get(SysPage.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Page.Info.Get.Error", e);
		}
		return page;
	}

	public void updatePage(SysPage obj, String id, String[] properties)
			throws Exception {
		try {
			this.daoFacade.getPageDao().update(obj, SysPage.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Page.Info.Update.Error", e);
		}
	}

	public void insertPage(SysPage obj) throws Exception {
		try {
			this.daoFacade.getPageDao().insert(obj);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Page.Info.Add.Error", e);
		}
	}

	public void deletePageById(String id) throws Exception {
		try {
			this.daoFacade.getPageDao().delete(SysPage.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Page.Info.Delete.Error", e);
		}
	}
	
	/****Page End**************************************************************************************************************************/
	
	/****Resource Start************************************************************************************************************************/

	public List<SysResource> getResourcePagingList(Map<String, Object> condition,
			String sort, Integer pageCount, Integer countStart) throws Exception {
		List<SysResource> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("getPagingList");
			list = daoFacade.getResourceDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Resource.Info.Paging.Error", e);
		}
		return list;
	}

	public List<SysResource> getResourceList(Map<String, Object> condition,
			String sort) throws Exception {
		List<SysResource> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("getList");
			hqlBean.setOrder(sort);
			list = daoFacade.getResourceDao().getList(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Resource.Info.List.Error", e);
		}
		return list;
	}

	public Integer getResourceCount(Map<String, Object> condition)
			throws Exception {
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("getCount");
			num = daoFacade.getResourceDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Resource.Info.Count.Error", e);
		}
		return num;
	}
	
	/**
	 * 获取下一个Order
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	private Integer getNextOrder(Map<String, Object> condition,String sort)throws Exception{
		Integer order = 1;
		try{
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("getNextOrder");
			order = daoFacade.getResourceDao().getNextOrder(condition, sort, hqlBean);
		} catch (Exception e) {
			throw e;
		}
		return order;
		
	}
	/**
	 * 获取下一个编号
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	private String getNextCode(Map<String, Object> condition,String sort)throws Exception{
		String code = "";
		try{
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("getNextCode");
			code = daoFacade.getResourceDao().getNextCode(condition, sort, hqlBean);
		} catch (Exception e) {
			throw e;
		}
		return code;
	}

	public SysResource getResourceById(String id) throws Exception {
		SysResource resource = null;
		try {
			resource = (SysResource) this.daoFacade.getResourceDao().get(SysResource.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Resource.Info.Get.Error", e);
		}
		return resource;
	}

	public void updateResource(SysResource obj, String id, String[] properties)
			throws Exception {
		try {
			this.daoFacade.getResourceDao().update(obj, SysResource.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Resource.Info.Update.Error", e);
		}
	}

	public void insertResource(SysResource obj) throws Exception {
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			if(obj.getParentResource()==null){
				condition.put("resourceId","0");
			}else{
				condition.put("resourceId",obj.getParentResource().getId());
			}
			condition.put("systemId",obj.getSystem().getId());
			condition.put("type", obj.getType());
			condition.put("status", 1);
			obj.setOrder(getNextOrder(condition,null));
			obj.setCode(getNextCode(condition,null));
			this.daoFacade.getResourceDao().insert(obj);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Resource.Info.Add.Error", e);
		}
	}

	public void deleteResourceById(String id) throws Exception {
		try {
			this.daoFacade.getResourceDao().delete(SysResource.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Resource.Info.Delete.Error", e);
		}
	}

	public List<SysResource> getResourceListByRoleId(Map<String, Object> condition, String sort) throws Exception {
		List<SysResource> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("getResourceListByRoleId");
			if(sort!=null&&!"".equals(sort)){
				hqlBean.setOrder(sort);
			}
			list = daoFacade.getResourceDao().getResourceListByRoleId(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Resource.Info.List.Error", e);
		}
		return list;
	}
	
	/**
	 * 交换菜单位置
	 * @param targetType
	 * @param targetId
	 * @param targetSysId
	 * @param sourceType
	 * @param sourceId
	 * @param sourceSysId
	 * @param moveType
	 * @throws Exception
	 * 1.如果moveType==inner,源节点放在目标节点内，Order是目标节点的最大值  和 源节点的父节点TreeCode更新
	 * 2.如果moveType==next,源节点和目标节点同级并放在目标节点下，Order是目标节点下的节点的Order值，更新目标节点下的同级节点的Order 和 源节点的父节点TreeCode更新
	 * 3.如果moveType==prev,源节点和目标节点同级并放在目标节点上，Order是目标节点的节点的Order值，更新目标节点下的同级节点的Order 和 源节点的父节点TreeCode更新
	 */
	public void positionChange(String targetType,String targetId,String targetSysId,String sourceType,String sourceId,String sourceSysId,String moveType)throws Exception{
		try {
			if("next".equals(moveType)){
				//1.获取目标节点 target
				SysResource target = this.getResourceById(targetId);
				//2.获取源节点 source
				SysResource source = this.getResourceById(sourceId);
				//3.找到目标节点的下一个节点  next 
				SysResource target_next = null;
				Integer target_order = target.getOrder();
				Integer target_next_order = 1;
				Map<String, Object> nextCondition = new HashMap<String,Object>();
				nextCondition.put("status",1);
				nextCondition.put("type",1);
				nextCondition.put("order",target_order);
				
				nextCondition.put("sysId",target.getSystem().getId());
				if(target.getParentResource()==null){
					nextCondition.put("resourceId","0");
				}else{
					nextCondition.put("resourceId",target.getParentResource().getId());
				}
				HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("getNextMenu");
				List<SysResource> list = daoFacade.getResourceDao().getPagingList(nextCondition, "", 1, 0, hqlBean);
				if(list!=null&&!list.isEmpty()){
					target_next = list.get(0);
					target_next_order = target_next.getOrder();
				}else{
					target_next_order = target.getOrder();
				}
				//4.更新next以及与其同级别的Order大于next的（包含next）的节点的Order值，全部顺序加1
				nextCondition.put("greaterEqualOrder",target_next_order);
				hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("updateBulkResourceOrder");
				daoFacade.getResourceDao().updateByHql(nextCondition, hqlBean);
				//5.将源节点的Order换成next的Order
				SysResource targetParent = null;
				if(target.getParentResource()!=null){
					targetParent = this.getResourceById(target.getParentResource().getId());
				}
				source.setOrder(target_next_order);
				String[] properties = null;
				if(targetParent==null){
					properties = new String[]{"parentResource"};
				}else{
					source.setParentResource(targetParent);
				}
				Map<String,Object> condition = new HashMap<String,Object>();
				if(targetParent==null){
					condition.put("resourceId","0");
				}else{
					condition.put("resourceId",targetParent.getId());
				}
				condition.put("systemId",target.getSystem().getId());
				condition.put("type", target.getType());
				condition.put("status", 1);
				String targetCode = this.getNextCode(condition, null);
				this.updateResource(source, source.getId(),properties);
				//6.更新源节点以及源节点子节点的treeCode
				String sourceCode = source.getCode();
				nextCondition = new HashMap<String,Object>();
				nextCondition.put("sysId",target.getSystem().getId());
				nextCondition.put("targetCode",targetCode);
				nextCondition.put("index",sourceCode.length()+1);
				nextCondition.put("length",sourceCode.length());
				nextCondition.put("status",1);
				nextCondition.put("type",1);
				nextCondition.put("code",sourceCode+"%");
				nextCondition.put("sourceSysId",source.getSystem().getId());
				hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("updateBulkResourceCode");
				daoFacade.getResourceDao().updateByHql(nextCondition, hqlBean);
			}else if("prev".equals(moveType)){
				//1.获取目标节点 target
				SysResource target = this.getResourceById(targetId);
				//2.获取源节点 source
				SysResource source = this.getResourceById(sourceId);
				//3.更新target以及与其同级别的Order大于target的（包含target）的节点的Order值，全部顺序加1
				Integer target_order = target.getOrder();
				Map<String, Object> nextCondition = new HashMap<String,Object>();
				nextCondition.put("status",1);
				nextCondition.put("type",1);
				nextCondition.put("greaterEqualOrder",target_order);
				nextCondition.put("sysId",target.getSystem().getId());
				if(target.getParentResource()==null){
					nextCondition.put("resourceId","0");
				}else{
					nextCondition.put("resourceId",target.getParentResource().getId());
				}
				HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("updateBulkResourceOrder");
				daoFacade.getResourceDao().updateByHql(nextCondition, hqlBean);
				//3.1//获取targetCode
				SysResource targetParent = null;
				if(target.getParentResource()!=null){
					targetParent=this.getResourceById(target.getParentResource().getId());
				}
				Map<String,Object> condition = new HashMap<String,Object>();
				if(targetParent==null){
					condition.put("resourceId","0");
				}else{
					condition.put("resourceId",targetParent.getId());
				}
				condition.put("systemId",target.getSystem().getId());
				condition.put("type", target.getType());
				condition.put("status", 1);
				String targetCode = this.getNextCode(condition, null);
				//4.将源节点的Order换成target的Order
				source.setOrder(target_order);
				String[] properties = null;
				if(targetParent==null){
					properties = new String[]{"parentResource"};
				}else{
					source.setParentResource(targetParent);
				}
				this.updateResource(source, source.getId(),properties);
				//5.更新源节点以及源节点子节点的treeCode
				String sourceCode = source.getCode();
				nextCondition = new HashMap<String,Object>();
				nextCondition.put("sysId",target.getSystem().getId());
				nextCondition.put("targetCode",targetCode);
				nextCondition.put("index",sourceCode.length()+1);
				nextCondition.put("length",sourceCode.length());
				nextCondition.put("status",1);
				nextCondition.put("type",1);
				nextCondition.put("code",sourceCode+"%");
				nextCondition.put("sourceSysId",source.getSystem().getId());
				hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("updateBulkResourceCode");
				daoFacade.getResourceDao().updateByHql(nextCondition, hqlBean);
			}else{
				if("sys".equals(targetType)){//目标节点是系统
					//1.获取目标节点 target
					SysSystem target = this.getSystemById(targetId);
					//2.获取源节点 source
					SysResource source = this.getResourceById(sourceId);
					//3.获取该系统下最大的Order
					Map<String,Object> condition = new HashMap<String,Object>();
					condition.put("resourceId","0");
					condition.put("systemId",target.getId());
					condition.put("type", 1);
					condition.put("status", 1);
					Integer targetOrder = this.getNextOrder(condition, null);
					//4.获取该系统下最大的Code
					String targetCode = this.getNextCode(condition, null);
					source.setOrder(targetOrder);
					String[] properties = null;
					properties = new String[]{"parentResource"};
					this.updateResource(source, source.getId(),properties);
					//5.更新源节点以及源节点子节点的treeCode
					String sourceCode = source.getCode();
					Map<String, Object> nextCondition = new HashMap<String,Object>();
					nextCondition.put("sysId",target.getId());
					nextCondition.put("targetCode",targetCode);
					nextCondition.put("index",sourceCode.length()+1);
					nextCondition.put("length",sourceCode.length());
					nextCondition.put("status",1);
					nextCondition.put("type",1);
					nextCondition.put("code",sourceCode+"%");
					nextCondition.put("sourceSysId",source.getSystem().getId());
					HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("updateBulkResourceCode");
					daoFacade.getResourceDao().updateByHql(nextCondition, hqlBean);
				}else{//目标节点是menu
					//1.获取目标节点 target
					SysResource target = this.getResourceById(targetId);
					//2.获取源节点 source
					SysResource source = this.getResourceById(sourceId);
					//3.获取该菜单下最大的Order
					Map<String,Object> condition = new HashMap<String,Object>();
					condition.put("resourceId",target.getId());
					condition.put("systemId",target.getSystem().getId());
					condition.put("type", 1);
					condition.put("status", 1);
					Integer targetOrder = this.getNextOrder(condition, null);
					//4.获取该系统下最大的Code
					String targetCode = this.getNextCode(condition, null);
					source.setOrder(targetOrder);
					String[] properties = null;
					source.setParentResource(target);
					this.updateResource(source, source.getId(),properties);
					//5.更新源节点以及源节点子节点的treeCode
					String sourceCode = source.getCode();
					Map<String, Object> nextCondition = new HashMap<String,Object>();
					nextCondition.put("sysId",target.getSystem().getId());
					nextCondition.put("targetCode",targetCode);
					nextCondition.put("index",sourceCode.length()+1);
					nextCondition.put("length",sourceCode.length());
					nextCondition.put("status",1);
					nextCondition.put("type",1);
					nextCondition.put("code",sourceCode+"%");
					nextCondition.put("sourceSysId",source.getSystem().getId());
					HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("updateBulkResourceCode");
					daoFacade.getResourceDao().updateByHql(nextCondition, hqlBean);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Resource.Info.Change.Position.Error", e);
		}
	}
	
	
	public String getTopIndex(String index,String menuId,String locale) throws Exception {
		SysResource menu = this.getResourceById(menuId);
		String menuStr = menu!=null&&menu.getLocale()!=null&&!"".equals(menu.getLocale().trim())&&!"".equals(Lang.getLanguage(menu.getLocale(),locale))?Lang.getLanguage(menu.getLocale(),locale):menu.getName();
		if(menu.getParentResource()==null){
			if("".equals(index.trim())){
				menuStr = "<li><i class=\"icon-home\"></i><a style=\"cursor:pointer\">"+menuStr+"</a></li>";
			}else{
				menuStr = "<li><i class=\"icon-home\"></i><a style=\"cursor:pointer\">"+menuStr+"</a><span class=\"divider\"> <i class=\"icon-angle-right\"></i></span></li>";
			}
		}else{
			if("".equals(index.trim())){
				menuStr = "<li class=\"active\"><a style=\"cursor:pointer\">"+menuStr+"</a></li>";
			}else{
				menuStr = "<li class=\"active\"><a style=\"cursor:pointer\">"+menuStr+"</a><span class=\"divider\"> <i class=\"icon-angle-right\"></i></span></li>";
			}
		}
		index = menuStr + index;
		if (menu.getParentResource() == null)
			return index;
		else
			return getTopIndex(index, menu.getParentResource().getId(),locale);
	}
	
	private SysResource getParentResource(SysResource resource, int skipLevel) {
		SysResource parentResource = null;
		if (skipLevel > 0) {
			// 继续递归   
			parentResource = this.getParentResource(resource.getParentResource(), skipLevel - 1);
		} else {
			// 递归到之前的层
			parentResource = resource.getParentResource();
		}
		return parentResource;
	}

	public void initDataByExcel(String path) throws Exception {
		try {
			InputStream is = new FileInputStream(new File(path));
			XSSFWorkbook xwb = new XSSFWorkbook(is);
			XSSFSheet systemSheet = xwb.getSheetAt(0);
			XSSFSheet componentSheet = xwb.getSheetAt(1);
			XSSFSheet roleSheet = xwb.getSheetAt(2);
			XSSFSheet accountSheet = xwb.getSheetAt(3);
			XSSFSheet resourceSheet = xwb.getSheetAt(4);
            XSSFSheet moduleSheet = xwb.getSheetAt(5);

			// 解析System
			for (int i = systemSheet.getFirstRowNum() + 1; i <= systemSheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = systemSheet.getRow(i);
				if (row != null) {
					XSSFCell systemName = row.getCell(0);
					XSSFCell systemCode = row.getCell(1);
					XSSFCell systemStatus = row.getCell(2);

					// 插入System
					SysSystem system = new SysSystem();
					system.setName(systemName.toString());
					system.setCode(systemCode.toString());
					/** 1-在用 2-停用 */
					if ("在用".equals(systemStatus.toString())) {
						system.setStatus(1);
					} else {
						system.setStatus(2);
					}
					this.daoFacade.getSystemDao().insert(system);
				}
			}
			
			// 解析Component
			for (int i = componentSheet.getFirstRowNum() + 1; i <= componentSheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = componentSheet.getRow(i);
				if (row != null) {
					XSSFCell componentName = row.getCell(0);
					XSSFCell componentCode = row.getCell(1);
					XSSFCell componentAddr = row.getCell(2);
					XSSFCell componentPort = row.getCell(3);
					XSSFCell componentStatus = row.getCell(4);
					XSSFCell componentSystem = row.getCell(5);

					// 插入Component
					SysComponent component = new SysComponent();
					component.setName(componentName.toString());
					component.setCode(componentCode.toString());
					component.setAddress(componentAddr.toString());
					component.setPort(componentPort.toString());
					/** 1-在用 2-停用 */
					if ("在用".equals(componentStatus.toString())) {
						component.setStatus(1);
					} else {
						component.setStatus(2);
					}
					Map<String, Object> condition = new HashMap<String, Object>();
					condition.put("code", componentSystem.toString());
					HqlBean sysHqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.SystemDao").get("getList");
					List<SysSystem> systemList = this.daoFacade.getSystemDao().getList(condition, "", sysHqlBean);
					component.setSystem(systemList.get(0));
					this.daoFacade.getComponentDao().insert(component);
				}
			}
			
			// 解析Role
			for (int i = roleSheet.getFirstRowNum() + 1; i <= roleSheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = roleSheet.getRow(i);
				if (row != null) {
					XSSFCell roleName = row.getCell(0);
					XSSFCell roleStatus = row.getCell(1);
					XSSFCell roleSystem = row.getCell(2);

					// 插入Role
					SysRole role = new SysRole();
					role.setName(roleName.toString());
					/** 1-在用 2-停用 */
					if ("在用".equals(roleStatus.toString())) {
						role.setStatus(1);
					} else {
						role.setStatus(2);
					}
					Map<String, Object> condition = new HashMap<String, Object>();
					condition.put("code", roleSystem.toString());
					HqlBean sysHqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.SystemDao").get("getList");
					List<SysSystem> systemList = this.daoFacade.getSystemDao().getList(condition, "", sysHqlBean);
					role.setSystem(systemList.get(0));
					this.daoFacade.getRoleDao().insert(role);
				}
			}
			
			// 解析Account
			for (int i = accountSheet.getFirstRowNum() + 1; i <= accountSheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = accountSheet.getRow(i);
				if (row != null) {
					XSSFCell accountName = row.getCell(0);
					XSSFCell accountPass = row.getCell(1);
					XSSFCell accountStatus = row.getCell(2);
					XSSFCell accountType = row.getCell(3);
					XSSFCell accountLevel = row.getCell(4);
					XSSFCell accountEncryption = row.getCell(5);
					XSSFCell accountRole = row.getCell(6);

					// 插入Account
					SysAccount account = new SysAccount();
					account.setName(accountName.toString());
					account.setPass(accountPass.toString());
					/** 1-在用 2-停用 */
					if ("在用".equals(accountStatus.toString())) {
						account.setStatus(1);
					} else {
						account.setStatus(2);
					}
					/** 1-本地账户 2-Sina账户 */
					if ("本地账户".equals(accountType.toString())) {
						account.setType(1);
					} else {
						account.setType(2);
					}
					/** 1-超级用户 2-普通用户 */
					if ("超级用户".equals(accountLevel.toString())) {
						account.setLevel(1);
					} else {
						account.setLevel(2);
					}
					/** 1-不加密 2-加密 */
					if ("不加密".equals(accountEncryption.toString())) {
						account.setEncryption(1);
					} else {
						account.setEncryption(2);
					}
					this.daoFacade.getAccountDao().insert(account);
					
					// 插入Role与Account关系
					String[] roleArray = accountRole.toString().split(";");
					for (int m = 0; m < roleArray.length; m ++) {
						String roleName = roleArray[m];
						Map<String, Object> roleCondition = new HashMap<String, Object>();
						roleCondition.put("name", roleName);
						HqlBean roleHqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleDao").get("getList");
						List<SysRole> roleList = this.daoFacade.getRoleDao().getList(roleCondition, "", roleHqlBean);
						SysRoleAccountRelationship roleAccountRelationship = new SysRoleAccountRelationship();
						roleAccountRelationship.setRole(roleList.get(0));
						roleAccountRelationship.setAccount(account);
						this.daoFacade.getRoleAccountRelationshipDao().insert(roleAccountRelationship);
					}
				}
			}
			
			// 解析Resource
			Map<String, String> dataCache = new HashMap<String, String>();
			SysResource sysResource = null;
			//int level = 1;
			String previousResourceCode = null;
			for (int i = resourceSheet.getFirstRowNum() + 1; i <= resourceSheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = resourceSheet.getRow(i);
				if (row != null) {
					XSSFCell resourceName = row.getCell(0);
					XSSFCell resourceCode = row.getCell(1);
					XSSFCell resourceUrl = row.getCell(2);
					XSSFCell resourceType = row.getCell(3);
					XSSFCell resourceStatus = row.getCell(4);
					XSSFCell resourceComponent = row.getCell(5);
					XSSFCell resourcePathFlg = row.getCell(6);
					XSSFCell resourceRole = row.getCell(7);

					// 插入Resource
					SysResource resource = new SysResource();
					resource.setName(resourceName.toString());
					resource.setCode(resourceCode.toString());
					resource.setOrder(Integer.parseInt(resourceCode.toString()));
					resource.setLink(resourceUrl.toString());
					/** 资源类型 1-菜单 2-接口 */
					if ("菜单".equals(resourceType.toString())) {
						resource.setType(1);
					} else {
						resource.setType(2);
					}
					/** 资源状态 1-在用 2-停用（菜单状态 上级菜单 决定下级菜单的状态） */
					if ("在用".equals(resourceStatus.toString())) {
						resource.setStatus(1);
					} else {
						resource.setStatus(2);
					}
					/** 是否全路径 1-否 2-是 */
					if ("否".equals(resourcePathFlg.toString())) {
						resource.setFull(1);
					} else {
						resource.setFull(2);
					}
					Map<String, Object> componentCondition = new HashMap<String, Object>();
					componentCondition.put("code", resourceComponent.toString());
					HqlBean componentHqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ComponentDao").get("getList");
					List<SysComponent> componentList = this.daoFacade.getComponentDao().getList(componentCondition, "", componentHqlBean);
					resource.setComponent(componentList.get(0));
					resource.setSystem(componentList.get(0).getSystem());
					
					if (previousResourceCode == null) {
						resource.setParentResource(null);
					} else {
						int previousLength = previousResourceCode.length();
						int currentLength = resourceCode.toString().length();
						if (previousLength == currentLength) {
							// 与上一个分类是同级
							resource.setParentResource(sysResource.getParentResource());
						} else if (previousLength < currentLength) {
							// 是上一个分类的下级
							resource.setParentResource(sysResource);
							//level ++;
						} else if (previousLength > currentLength) {
							// 是上一个分类的上级（不一定是上面几级）
							int currentLevel = Math.round((currentLength - 1)/3);
							int previousLevel = Math.round((previousLength - 1)/3);
							int skipLevel = previousLevel - currentLevel;
							resource.setParentResource(this.getParentResource(sysResource, skipLevel));
						}
					}
					this.daoFacade.getResourceDao().insert(resource);

					// 插入Role与Resource关系
					String[] roleArray = resourceRole.toString().split(";");
					for (int m = 0; m < roleArray.length; m ++) {
						String roleName = roleArray[m];
						Map<String, Object> roleCondition = new HashMap<String, Object>();
						roleCondition.put("name", roleName);
						HqlBean roleHqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleDao").get("getList");
						List<SysRole> roleList = this.daoFacade.getRoleDao().getList(roleCondition, "", roleHqlBean);
						SysRoleResourceRelationship roleResourceRelationship = new SysRoleResourceRelationship();
						roleResourceRelationship.setRole(roleList.get(0));
						roleResourceRelationship.setResource(resource);
						this.daoFacade.getRoleResourceRelationshipDao().insert(roleResourceRelationship);
					}
					
					dataCache.put(resourceCode.toString(), resource.getId());
					previousResourceCode = resourceCode.toString();
					sysResource = resource;
				}
			}

            // 解析Module
            for (int i = moduleSheet.getFirstRowNum() + 1; i <= moduleSheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = moduleSheet.getRow(i);
                if (row != null) {
                    XSSFCell moduleName = row.getCell(0);
                    XSSFCell moduleLink = row.getCell(1);
                    XSSFCell moduleMust = row.getCell(2);
                    XSSFCell moduleComponent = row.getCell(3);

                    // 插入Module
                    SysModule module = new SysModule();
                    module.setName(moduleName.toString());
                    module.setLink(moduleLink.toString());
                    /** 1-是 2-不是 */
                    if ("是".equals(moduleMust.toString())) {
                        module.setMust(1);
                    } else {
                        module.setMust(2);
                    }
                    Map<String, Object> componentCondition = new HashMap<String, Object>();
                    componentCondition.put("code", moduleComponent.toString());
                    HqlBean componentHqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ComponentDao").get("getList");
                    List<SysComponent> componentList = this.daoFacade.getComponentDao().getList(componentCondition, "", componentHqlBean);
                    module.setComponent(componentList.get(0));
                    this.daoFacade.getModuleDao().insert(module);
                }
            }
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new CcsException("解析Excel出错！");
		}
	}

	/****Resource End************************************************************************************************************************/
	
	/****Parameter Start************************************************************************************************************************/

	/**
	 * 获取参数分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param countStart
	 * @return
	 * @throws Exception
	 */
	public List<SysParameter> getParameterPagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception{
		List<SysParameter> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ParameterDao").get("getPagingList");
			if(sort!=null&&!"".equals(sort)){
				hqlBean.setOrder(sort);
			}
			list = daoFacade.getParameterDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Parameter.Info.Paging.List.Error", e);
		}
		return list;
	}

	/**
	 * 获取参数列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysParameter> getSysParameterList(Map<String,Object> condition, String sort) throws Exception{
		List<SysParameter> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ParameterDao").get("getList");
			if(sort!=null&&!"".equals(sort)){
				hqlBean.setOrder(sort);
			}
			list = daoFacade.getParameterDao().getList(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Parameter.Info.List.Error", e);
		}
		return list;
	}

	/**
	 * 获取参数总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getSysParameterCount(Map<String,Object> condition) throws Exception{
		Integer num = 0;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ParameterDao").get("getCount");
			num = daoFacade.getParameterDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Parameter.Info.Count.Error", e);
		}
		return num;
	}

	/**
	 * 获取参数对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysParameter getSysParameterById(String id) throws Exception{
		SysParameter obj = null;
		try{
			obj = (SysParameter)daoFacade.getParameterDao().get(SysParameter.class.getName(), id);
		}catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Parameter.Info.Get.Error", e);
		}
		return obj;
	}

	/**
	 * 修改参数对象
	 * @param obj
	 * @param id
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public void updateSysParameter(SysParameter obj, String id, String[] properties) throws Exception{
		try{
			daoFacade.getParameterDao().update(obj, SysParameter.class.getName(), id, properties);
		}catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Parameter.Info.Update.Error", e);
		}
	}

	/**
	 * 新增参数对象
	 * @param obj
	 * @throws Exception
	 */
	public void insertSysParameter(SysParameter obj) throws Exception{
		try{
			daoFacade.getParameterDao().insert(obj);
		}catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Parameter.Info.Add.Error", e);
		}
	}

	/**
	 * 删除参数
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteSysParameterById(String id) throws Exception{
		try{
			daoFacade.getParameterDao().delete(SysParameter.class.getName(), id);
		}catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Parameter.Info.Delete.Error", e);
		}
	}

	public List<SysRoleFunctionRelationship> getRoleFunctionRelationshipListByRelationId(Map<String, Object> condition, String sort) throws Exception {

		List<SysRoleFunctionRelationship> list = null;
		try {
			HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleFunctionRelationshipDao").get("getList");
			list = this.daoFacade.getRoleFunctionRelationshipDao().getList(condition, sort, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Account.Info.Count.Error", e);
		}
		return list;
	}
	
	/****Parameter End************************************************************************************************************************/
}
