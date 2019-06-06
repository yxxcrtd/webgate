package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Set;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_系统信息
 * @see SYS_SYSTEM
 */
@SuppressWarnings("serial")
public class SysSystem implements Serializable {
	
	/**
	 * 系统ID
	 */
	private String id;
	
	/**
	 * 系统名称
	 */
	private String name;
	
	/**
	 * 系统编号
	 */
	private String code;
	
	/**
	 * 系统状态 1-在用 2-停用 3-废弃
	 */
	private Integer status;
	
	/**
	 * 角色集合
	 */
	private Set<SysRole> roleSet;
	
	/**
	 * 组件集合
	 */
	private Set<SysComponent> componentSet;
	
	/**
	 * 资源集合
	 */
	private Set<SysResource> resourceSet;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<SysRole> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<SysRole> roleSet) {
		this.roleSet = roleSet;
	}

	public Set<SysComponent> getComponentSet() {
		return componentSet;
	}

	public void setComponentSet(Set<SysComponent> componentSet) {
		this.componentSet = componentSet;
	}

	public Set<SysResource> getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(Set<SysResource> resourceSet) {
		this.resourceSet = resourceSet;
	}
	
}