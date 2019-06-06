package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Set;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_系统组件
 * @see SYS_COMPONENT
 */
@SuppressWarnings("serial")
public class SysComponent implements Serializable {
	
	/**
	 * 组件ID
	 */
	private String id;
	
	/**
	 * 组件名称
	 */
	private String name;
	
	/**
	 * 组件编号
	 */
	private String code;
	
	/**
	 * 组件地址
	 */
	private String address;
	
	/**
	 * 组件端口
	 */
	private String port;
	
	/**
	 * 组件状态 1-有效 2-无效
	 */
	private Integer status;
	
	/**
	 * 系统
	 */
	private SysSystem system;
	
	/**
	 * 资源集合
	 */
	private Set<SysResource> resourceSet;
	
	/**
	 * 模块信息
	 */
	private Set<SysModule> modules;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public SysSystem getSystem() {
		return system;
	}

	public void setSystem(SysSystem system) {
		this.system = system;
	}

	public Set<SysResource> getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(Set<SysResource> resourceSet) {
		this.resourceSet = resourceSet;
	}

	public Set<SysModule> getModules() {
		return modules;
	}

	public void setModules(Set<SysModule> modules) {
		this.modules = modules;
	}
	
}