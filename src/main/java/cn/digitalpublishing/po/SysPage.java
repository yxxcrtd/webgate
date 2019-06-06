package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Set;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_页面
 * @see SYS_PAGE
 */
@SuppressWarnings("serial")
public class SysPage implements Serializable {
	
	/**
	 * 页面ID
	 */
	private String id;
	
	/**
	 * 页面名称
	 */
	private String name;
	
	/**
	 * 页面编号
	 */
	private String code;
	
	/**
	 * 页面路径
	 */
	private String path;
	
	/**
	 * 特殊标识 用于相同页面路径标识
	 */
	private String special;
	
	/**
	 * 资源
	 */
	private SysResource resource;
	
	/**
	 * 功能集合
	 */
	private Set<SysFunction> functionSet;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public SysResource getResource() {
		return resource;
	}

	public void setResource(SysResource resource) {
		this.resource = resource;
	}

	public Set<SysFunction> getFunctionSet() {
		return functionSet;
	}

	public void setFunctionSet(Set<SysFunction> functionSet) {
		this.functionSet = functionSet;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}
	
}