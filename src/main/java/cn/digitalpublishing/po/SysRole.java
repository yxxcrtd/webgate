package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Set;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_角色
 * @see SYS_ROLE
 */
@SuppressWarnings("serial")
public class SysRole implements Serializable {
	
	/**
	 * 角色ID
	 */
	private String id;
	
	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 角色描述
	 */
	private String desc;
	
	/**
	 * 角色状态 1-在用 2-停用（角色状态决定 用户状态）
	 */
	private Integer status;
	
	/**
	 * 系统
	 */
	private SysSystem system;
	
	/**
	 * 角色和资源关系集合
	 */
	private Set<SysRoleResourceRelationship> roleResourceRelationshipSet;
	
	/**
	 * 角色和功能关系集合
	 */
	private Set<SysRoleFunctionRelationship> roleFunctionRelationshipSet;
	
	/**
	 * 角色和账户关系集合
	 */
	private Set<SysRoleAccountRelationship> roleAccountRelationshipSet;

	/**
	 * 用于在页面account的checkbox选中的
	 */
	private Integer accountChooseType=0;

	/**
	 * 用于在页面resource的checkbox选中的
	 */
	private Integer resourceChooseType=0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<SysRoleResourceRelationship> getRoleResourceRelationshipSet() {
		return roleResourceRelationshipSet;
	}

	public void setRoleResourceRelationshipSet(
			Set<SysRoleResourceRelationship> roleResourceRelationshipSet) {
		this.roleResourceRelationshipSet = roleResourceRelationshipSet;
	}

	public Set<SysRoleFunctionRelationship> getRoleFunctionRelationshipSet() {
		return roleFunctionRelationshipSet;
	}

	public void setRoleFunctionRelationshipSet(
			Set<SysRoleFunctionRelationship> roleFunctionRelationshipSet) {
		this.roleFunctionRelationshipSet = roleFunctionRelationshipSet;
	}

	public Set<SysRoleAccountRelationship> getRoleAccountRelationshipSet() {
		return roleAccountRelationshipSet;
	}

	public void setRoleAccountRelationshipSet(
			Set<SysRoleAccountRelationship> roleAccountRelationshipSet) {
		this.roleAccountRelationshipSet = roleAccountRelationshipSet;
	}

	public SysSystem getSystem() {
		return system;
	}

	public void setSystem(SysSystem system) {
		this.system = system;
	}

	public Integer getAccountChooseType() {
		return accountChooseType;
	}

	public void setAccountChooseType(Integer accountChooseType) {
		this.accountChooseType = accountChooseType;
	}

	public Integer getResourceChooseType() {
		return resourceChooseType;
	}

	public void setResourceChooseType(Integer resourceChooseType) {
		this.resourceChooseType = resourceChooseType;
	}
	
}