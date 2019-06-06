package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Set;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_账户信息
 * @see SYS_ACCOUNT
 */
@SuppressWarnings("serial")
public class SysAccount implements Serializable {
	
	/**
	 * 账户ID
	 */
	private String id;
	
	/**
	 * 账户名称
	 */
	private String name;
	
	/**
	 * 账户密码
	 */
	private String pass;
	
	/**
	 * 账户状态 1-在用 2-停用
	 */
	private Integer status;
	
	/**
	 * 账户类型 1-本地账户 2-Sina账户
	 */
	private Integer type;
	
	/**
	 * 账户级别 1-超级用户（不能维护不能删除） 2-普通用户（可维护可删除）
	 */
	private Integer level;
	/**
	 * 加密 1-不加密 2-加密
	 */
	private Integer encryption;
	/**
	 * 员工Id
	 */
	private String employeeId;
	
	/**
	 * 账户和模块关系集合
	 */
	private Set<SysAccountModuleRelationship> accountModuleRelationshipSet;
	
	/**
	 * 角色和账户关系集合
	 */
	private Set<SysRoleAccountRelationship> roleAccountRelationshipSet;

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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Set<SysAccountModuleRelationship> getAccountModuleRelationshipSet() {
		return accountModuleRelationshipSet;
	}

	public void setAccountModuleRelationshipSet(
			Set<SysAccountModuleRelationship> accountModuleRelationshipSet) {
		this.accountModuleRelationshipSet = accountModuleRelationshipSet;
	}

	public Set<SysRoleAccountRelationship> getRoleAccountRelationshipSet() {
		return roleAccountRelationshipSet;
	}

	public void setRoleAccountRelationshipSet(
			Set<SysRoleAccountRelationship> roleAccountRelationshipSet) {
		this.roleAccountRelationshipSet = roleAccountRelationshipSet;
	}

	public Integer getEncryption() {
		return encryption;
	}

	public void setEncryption(Integer encryption) {
		this.encryption = encryption;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
}