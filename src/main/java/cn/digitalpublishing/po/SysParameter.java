package cn.digitalpublishing.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SysParameter implements Serializable {
	/**
	 * id
	 */
	private String id;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 值
	 */
	private String val;
	/**
	 * 是否为空 1-允许 2-不允许
	 */
	private Integer isNull;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public Integer getIsNull() {
		return isNull;
	}

	public void setIsNull(Integer isNull) {
		this.isNull = isNull;
	}

	public SysResource getResource() {
		return resource;
	}

	public void setResource(SysResource resource) {
		this.resource = resource;
	}

}
