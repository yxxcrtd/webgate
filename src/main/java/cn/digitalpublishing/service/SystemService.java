package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

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
import cn.digitalpublishing.po.SysSystem;

public interface SystemService extends BaseService {
	
	/****System Start************************************************************************************************************************/

	/**
	 * 获取系统分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param countStart
	 * @return
	 * @throws Exception
	 */
	public List<SysSystem> getSystemPagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception;

	/**
	 * 获取系统列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysSystem> getSystemList(Map<String,Object> condition, String sort) throws Exception;

	/**
	 * 获取系统总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getSystemCount(Map<String,Object> condition) throws Exception;

	/**
	 * 获取系统对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysSystem getSystemById(String id) throws Exception;

	/**
	 * 修改系统对象
	 * @param obj
	 * @param id
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public void updateSystem(SysSystem obj, String id, String[] properties) throws Exception;

	/**
	 * 新增系统对象
	 * @param obj
	 * @throws Exception
	 */
	public void insertSystem(SysSystem obj) throws Exception;

	/**
	 * 删除系统
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteSystemById(String id) throws Exception;
	
	/****System End************************************************************************************************************************/
	
	/****Module Start************************************************************************************************************************/

	/**
	 * 获取模块分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param countStart
	 * @return
	 * @throws Exception
	 */
	public List<SysModule> getModulePagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception;

	/**
	 * 获取模块列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysModule> getModuleList(Map<String,Object> condition, String sort) throws Exception;

	/**
	 * 获取模块总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getModuleCount(Map<String,Object> condition) throws Exception;

	/**
	 * 获取模块对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysModule getModuleById(String id) throws Exception;

	/**
	 * 修改模块对象
	 * @param obj
	 * @param id
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public void updateModule(SysModule obj, String id, String[] properties) throws Exception;

	/**
	 * 新增模块对象
	 * @param obj
	 * @throws Exception
	 */
	public void insertModule(SysModule obj) throws Exception;

	/**
	 * 删除模块
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteModuleById(String id) throws Exception;
	
	/****Module End************************************************************************************************************************/
	
	/****Component Start************************************************************************************************************************/

	/**
	 * 获取组件分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param countStart
	 * @return
	 * @throws Exception
	 */
	public List<SysComponent> getComponentPagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception;

	/**
	 * 获取组件列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysComponent> getComponentList(Map<String,Object> condition, String sort) throws Exception;

	/**
	 * 获取组件总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getComponentCount(Map<String,Object> condition) throws Exception;

	/**
	 * 获取组件对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysComponent getComponentById(String id) throws Exception;

	/**
	 * 修改组件对象
	 * @param obj
	 * @param id
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public void updateComponent(SysComponent obj, String id, String[] properties) throws Exception;

	/**
	 * 新增组件对象
	 * @param obj
	 * @throws Exception
	 */
	public void insertComponent(SysComponent obj) throws Exception;

	/**
	 * 删除组件
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteComponentById(String id) throws Exception;
	
	/****Component End************************************************************************************************************************/
	
	/****Account Start************************************************************************************************************************/

	/**
	 * 获取账户分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param countStart
	 * @return
	 * @throws Exception
	 */
	public List<SysAccount> getAccountPagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception;

	/**
	 * 获取账户列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysAccount> getAccountList(Map<String,Object> condition, String sort) throws Exception;

	/**
	 * 获取账户总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getAccountCount(Map<String,Object> condition) throws Exception;

	/**
	 * 获取账户对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysAccount getAccountById(String id) throws Exception;

	/**
	 * 修改账户对象
	 * @param obj
	 * @param id
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public void updateAccount(SysAccount obj, String id, String[] properties) throws Exception;

	/**
	 * 新增账户对象
	 * @param obj
	 * @throws Exception
	 */
	public void insertAccount(SysAccount obj) throws Exception;

	/**
	 * 删除账户
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteAccountById(String id) throws Exception;
	

	/**
	 * 用户登录
	 * @param condition
	 * @param sort
	 * @param isEncrypt
	 * @return
	 * @throws Exception
	 */
	public SysAccount login(Map<String,Object> condition, String sort, boolean isEncrypt) throws Exception;
	
	/**
	 * 获取账户对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysAccount getAccountByEmpId(String id) throws Exception;
	
	/****Account End************************************************************************************************************************/
	
	/****RoleAccountRelationship Start************************************************************************************************************************/

	/**
	 * 根据账户id获取角色账户关系列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysRoleAccountRelationship> getRoleAccountRelationshipListByAccountId(Map<String,Object> condition, String sort) throws Exception;

	/**
	 * 根据RelationId获取角色账户关系列表个数
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer getRoleAccountRelationshipCountByRelationId(Map<String,Object> condition) throws Exception;
	
	/**
	 * 根据账户id批量保存角色账户关系
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public void batchSaveRoleAccountRelationship(Object[] allId, Object[] sela, String id) throws Exception;
	
	/****RoleAccountRelationship End************************************************************************************************************************/
	
	/****RoleResourceRelationship Start************************************************************************************************************************/

	/**
	 * 根据RelationId获取角色资源关系列表个数
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer getRoleResourceRelationshipCountByRelationId(Map<String,Object> condition) throws Exception;

	/**
	 * 根据账户id批量保存角色资源关系
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public void batchSaveRoleResourceRelationship(Object[] allId, Object[] sela, String id) throws Exception;

	/**
	 * 根据账户id批量保存角色资源关系
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public void batchSaveRoleResourceRelationship(Object[] sela, String id) throws Exception;
	
	/****RoleResourceRelationship End************************************************************************************************************************/
	
	/****RoleFunctionRelationship Start************************************************************************************************************************/

	/**
	 * 根据RelationId获取角色功能关系列表个数
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer getRoleFunctionRelationshipCountByRelationId(Map<String,Object> condition) throws Exception;
	
	/**
	 * 根据RelationId获取角色功能关系列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysRoleFunctionRelationship> getRoleFunctionRelationshipListByRelationId(Map<String,Object> condition,String sort) throws Exception;
	
	/**
	 * 根据账户id批量保存角色功能关系
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public void batchSaveRoleFunctionRelationship(Object[] allId, Object[] sela, String id) throws Exception;
	
	/****RoleFunctionRelationship End************************************************************************************************************************/
	
	/****RoleFunctionRelationship Start************************************************************************************************************************/

	/**
	 * 根据RelationId获取账户模块关系列表个数
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer getAccountModuleRelationshipCountByRelationId(Map<String,Object> condition) throws Exception;
	
	/****RoleFunctionRelationship End************************************************************************************************************************/
	
	/****Role Start************************************************************************************************************************/
	
	/**
	 * 获取角色分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param countStart
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> getRolePagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception;

	/**
	 * 获取角色列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> getRoleList(Map<String,Object> condition, String sort) throws Exception;

	/**
	 * 获取角色总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getRoleCount(Map<String,Object> condition) throws Exception;

	/**
	 * 获取角色对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysRole getRoleById(String id) throws Exception;

	/**
	 * 修改角色对象
	 * @param obj
	 * @param id
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public void updateRole(SysRole obj, String id, String[] properties) throws Exception;

	/**
	 * 新增角色对象
	 * @param obj
	 * @throws Exception
	 */
	public void insertRole(SysRole obj) throws Exception;

	/**
	 * 删除角色
	 * @param id
	 * @throws Exception
	 */
	public void deleteRoleById(String id) throws Exception;
	
	/****Role End**************************************************************************************************************************/
	
	/****Function Start************************************************************************************************************************/

	/**
	 * 获取功能分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param countStart
	 * @return
	 * @throws Exception
	 */
	public List<SysFunction> getFunctionPagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception;

	/**
	 * 获取功能列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysFunction> getFunctionList(Map<String,Object> condition, String sort) throws Exception;

	/**
	 * 获取功能总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getFunctionCount(Map<String,Object> condition) throws Exception;

	/**
	 * 获取功能对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysFunction getFunctionById(String id) throws Exception;

	/**
	 * 修改功能对象
	 * @param obj
	 * @param id
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public void updateFunction(SysFunction obj, String id, String[] properties) throws Exception;

	/**
	 * 新增功能对象
	 * @param obj
	 * @throws Exception
	 */
	public void insertFunction(SysFunction obj) throws Exception;

	/**
	 * 删除功能
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteFunctionById(String id) throws Exception;
	
	/****Function End************************************************************************************************************************/
	
	/****Page Start************************************************************************************************************************/

	/**
	 * 获取页面分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param countStart
	 * @return
	 * @throws Exception
	 */
	public List<SysPage> getPagePagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception;

	/**
	 * 获取页面列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysPage> getPageList(Map<String,Object> condition, String sort) throws Exception;

	/**
	 * 获取页面总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getPageCount(Map<String,Object> condition) throws Exception;

	/**
	 * 获取页面对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysPage getPageById(String id) throws Exception;

	/**
	 * 修改页面对象
	 * @param obj
	 * @param id
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public void updatePage(SysPage obj, String id, String[] properties) throws Exception;

	/**
	 * 新增页面对象
	 * @param obj
	 * @throws Exception
	 */
	public void insertPage(SysPage obj) throws Exception;

	/**
	 * 删除页面
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deletePageById(String id) throws Exception;
	
	/****Page End************************************************************************************************************************/
	
	/****Resource Start************************************************************************************************************************/

	/**
	 * 获取资源分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param countStart
	 * @return
	 * @throws Exception
	 */
	public List<SysResource> getResourcePagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception;

	/**
	 * 获取资源列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysResource> getResourceList(Map<String,Object> condition, String sort) throws Exception;

	/**
	 * 获取资源总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getResourceCount(Map<String,Object> condition) throws Exception;

	/**
	 * 获取资源对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysResource getResourceById(String id) throws Exception;

	/**
	 * 修改资源对象
	 * @param obj
	 * @param id
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public void updateResource(SysResource obj, String id, String[] properties) throws Exception;

	/**
	 * 新增资源对象
	 * @param obj
	 * @throws Exception
	 */
	public void insertResource(SysResource obj) throws Exception;

	/**
	 * 删除资源
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteResourceById(String id) throws Exception;

	/**
	 * 根据角色id获取资源列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysResource> getResourceListByRoleId(Map<String,Object> condition, String sort) throws Exception;
	
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
	public void positionChange(String targetType,String targetId,String targetSysId,String sourceType,String sourceId,String sourceSysId,String moveType)throws Exception;
	
	/**
	 * 获取导航
	 * @param index
	 * @param menuId
	 * @param getTopIndex
	 * @return
	 * @throws Exception
	 */
	public String getTopIndex(String index,String menuId,String getTopIndex) throws Exception;
	
	/**
	 * 上传Excel
	 * @param path
	 * @throws Exception
	 */
	public void initDataByExcel(String path) throws Exception;
	
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
	public List<SysParameter> getParameterPagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception;

	/**
	 * 获取参数列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<SysParameter> getSysParameterList(Map<String,Object> condition, String sort) throws Exception;

	/**
	 * 获取参数总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getSysParameterCount(Map<String,Object> condition) throws Exception;

	/**
	 * 获取参数对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysParameter getSysParameterById(String id) throws Exception;

	/**
	 * 修改参数对象
	 * @param obj
	 * @param id
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public void updateSysParameter(SysParameter obj, String id, String[] properties) throws Exception;

	/**
	 * 新增参数对象
	 * @param obj
	 * @throws Exception
	 */
	public void insertSysParameter(SysParameter obj) throws Exception;

	/**
	 * 删除参数
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteSysParameterById(String id) throws Exception;
	
	/****Parameter End************************************************************************************************************************/
	
}
