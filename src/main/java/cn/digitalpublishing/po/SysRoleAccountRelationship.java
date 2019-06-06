package cn.digitalpublishing.po;

import java.io.Serializable;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_角色和账户关系
 * @see SYS_RA_RELATIONSHIP
 */
@SuppressWarnings("serial")
public class SysRoleAccountRelationship implements Serializable {
	
	/**
	 * 角色和账户ID
	 */
	private String id;
	
	/**
	 * 角色
	 */
	private SysRole role;
	
	/**
	 * 账户
	 */
	private SysAccount account;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public SysAccount getAccount() {
		return account;
	}

	public void setAccount(SysAccount account) {
		this.account = account;
	}
	
}