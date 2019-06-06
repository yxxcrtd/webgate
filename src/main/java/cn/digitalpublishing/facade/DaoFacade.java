package cn.digitalpublishing.facade;

import cn.digitalpublishing.dao.AccountDao;
import cn.digitalpublishing.dao.AccountModuleRelationshipDao;
import cn.digitalpublishing.dao.ComponentDao;
import cn.digitalpublishing.dao.FunctionDao;
import cn.digitalpublishing.dao.ModuleDao;
import cn.digitalpublishing.dao.PageDao;
import cn.digitalpublishing.dao.ParameterDao;
import cn.digitalpublishing.dao.ResourceDao;
import cn.digitalpublishing.dao.RoleAccountRelationshipDao;
import cn.digitalpublishing.dao.RoleDao;
import cn.digitalpublishing.dao.RoleFunctionRelationshipDao;
import cn.digitalpublishing.dao.RoleResourceRelationshipDao;
import cn.digitalpublishing.dao.SystemDao;

public class DaoFacade {
	
	//账户信息
	AccountDao accountDao;
	
	//组件
	ComponentDao componentDao;
	
	//功能
	FunctionDao functionDao;
	
	//模块
	ModuleDao moduleDao;
	
	//页面
	PageDao pageDao;
	
	//参数
	ParameterDao parameterDao;

	//资源
	ResourceDao resourceDao;

	//角色
	RoleDao roleDao;

	//系统
	SystemDao systemDao;

	//账户和模块关系
	AccountModuleRelationshipDao accountModuleRelationshipDao;

	//角色和账户关系
	RoleAccountRelationshipDao roleAccountRelationshipDao;

	//角色和功能键关系
	RoleFunctionRelationshipDao roleFunctionRelationshipDao;

	//角色和资源关系
	RoleResourceRelationshipDao roleResourceRelationshipDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public ComponentDao getComponentDao() {
		return componentDao;
	}

	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	public FunctionDao getFunctionDao() {
		return functionDao;
	}

	public void setFunctionDao(FunctionDao functionDao) {
		this.functionDao = functionDao;
	}

	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public SystemDao getSystemDao() {
		return systemDao;
	}

	public void setSystemDao(SystemDao systemDao) {
		this.systemDao = systemDao;
	}

	public AccountModuleRelationshipDao getAccountModuleRelationshipDao() {
		return accountModuleRelationshipDao;
	}

	public void setAccountModuleRelationshipDao(
			AccountModuleRelationshipDao accountModuleRelationshipDao) {
		this.accountModuleRelationshipDao = accountModuleRelationshipDao;
	}

	public RoleAccountRelationshipDao getRoleAccountRelationshipDao() {
		return roleAccountRelationshipDao;
	}

	public void setRoleAccountRelationshipDao(
			RoleAccountRelationshipDao roleAccountRelationshipDao) {
		this.roleAccountRelationshipDao = roleAccountRelationshipDao;
	}

	public RoleFunctionRelationshipDao getRoleFunctionRelationshipDao() {
		return roleFunctionRelationshipDao;
	}

	public void setRoleFunctionRelationshipDao(
			RoleFunctionRelationshipDao roleFunctionRelationshipDao) {
		this.roleFunctionRelationshipDao = roleFunctionRelationshipDao;
	}

	public RoleResourceRelationshipDao getRoleResourceRelationshipDao() {
		return roleResourceRelationshipDao;
	}

	public void setRoleResourceRelationshipDao(
			RoleResourceRelationshipDao roleResourceRelationshipDao) {
		this.roleResourceRelationshipDao = roleResourceRelationshipDao;
	}

	public ParameterDao getParameterDao() {
		return parameterDao;
	}

	public void setParameterDao(ParameterDao parameterDao) {
		this.parameterDao = parameterDao;
	}
	
}
