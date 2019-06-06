package cn.digitalpublishing.po;

import java.io.Serializable;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_角色和功能关系
 * @see SYS_RF_RELATIONSHIP
 */
@SuppressWarnings("serial")
public class SysRoleFunctionRelationship implements Serializable {
	
	/**
	 * 角色和功能ID
	 */
	private String id;
	
	/**
	 * 角色
	 */
	private SysRole role;
	
	/**
	 * 功能
	 */
	private SysFunction function;

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

	public SysFunction getFunction() {
		return function;
	}

	public void setFunction(SysFunction function) {
		this.function = function;
	}
	
}