package cn.digitalpublishing.springmvc.form.system;

import java.util.HashMap;
import java.util.Map;

import cn.digitalpublishing.po.SysSystem;
import cn.digitalpublishing.springmvc.form.DataTableForm;

public class SystemForm extends DataTableForm<SysSystem> {
	
	/**
	 * Status下拉框列表
	 */
	private Map<String,String> statusMap = new HashMap<String,String>();
	
	/**
	 * 系统对象
	 */
	private SysSystem obj = new SysSystem();
	
	/**
	 * 系统名称
	 */
	private String name;
	
	/**
	 * 系统编码
	 */
	private String code;
	
	/**
	 * 系统状态
	 */
	private Integer status;

	public SysSystem getObj() {
		return obj;
	}

	public void setObj(SysSystem obj) {
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.getCondition().put("name", name);
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.getCondition().put("code", code);
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.getCondition().put("status", status);
		this.status = status;
	}

	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}

}
