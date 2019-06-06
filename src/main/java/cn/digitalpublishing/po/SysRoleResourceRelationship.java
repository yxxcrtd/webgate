package cn.digitalpublishing.po;

import java.io.Serializable;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_角色和资源关系
 * @see SYS_RR_RELATIONSHIP
 */
@SuppressWarnings("serial")
public class SysRoleResourceRelationship implements Serializable {
	
	/**
	 * 角色和资源ID
	 */
	private String id;
	
	/**
	 * 角色
	 */
	private SysRole role;
	
	/**
	 * 资源
	 */
	private SysResource resource;

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

	public SysResource getResource() {
		return resource;
	}

	public void setResource(SysResource resource) {
		this.resource = resource;
	}
	
}