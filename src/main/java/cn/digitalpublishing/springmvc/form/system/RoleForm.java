package cn.digitalpublishing.springmvc.form.system;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.SysRole;
import cn.digitalpublishing.po.SysSystem;
import cn.digitalpublishing.springmvc.form.DataTableForm;

public class RoleForm extends DataTableForm<SysRole> {
	
	/**
	 * Status下拉框列表
	 */
	private Map<String,String> statusMap = new LinkedHashMap<String,String>();
	
	/**
	 * 系统下拉菜单
	 */
	private List<SysSystem> systemList = new ArrayList<SysSystem>();
	
	/**
	 * 角色对象
	 */
	private SysRole obj = new SysRole();
	
	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 账户状态 1-在用 2-停用
	 */
	private Integer status;
	
	/**
	 * 系统Id
	 */
	private String sysId;
	
	/**
	 * 资源Id
	 */
	private String resourceId;

	/**
	 * checkbox集合
	 */
	private Object[] sela;
	
	/**
	 * 当前页所有ID
	 */
	private Object[] allId;
	
	public Map<String, String> getStatusMap() {
		return statusMap;
	}
	
	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}
	
	public SysRole getObj() {
		return obj;
	}
	
	public void setObj(SysRole obj) {
		this.obj = obj;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.getCondition().put("name", name);
		this.name = name;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.getCondition().put("status", status);
		this.status = status;
	}
	
	public String getSysId() {
		return sysId;
	}
	
	public void setSysId(String sysId) {
		this.getCondition().put("sysId", sysId);
		this.sysId = sysId;
	}

	public List<SysSystem> getSystemList() {
		return systemList;
	}

	public void setSystemList(List<SysSystem> systemList) {
		this.systemList = systemList;
	}

	public Object[] getSela() {
		return sela;
	}

	public void setSela(Object[] sela) {
		this.sela = sela;
	}

	public Object[] getAllId() {
		return allId;
	}

	public void setAllId(Object[] allId) {
		this.allId = allId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.getCondition().put("resourceId", resourceId);
		this.resourceId = resourceId;
	}

}
