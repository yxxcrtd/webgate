package cn.digitalpublishing.springmvc.form.system;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.SysComponent;
import cn.digitalpublishing.po.SysResource;
import cn.digitalpublishing.po.SysSystem;
import cn.digitalpublishing.springmvc.form.DataTableForm;
import cn.digitalpublishing.springmvc.util.TreeNode;

public class ResourceForm extends DataTableForm<SysResource>{
	
	private Map<String,String> statusMap = new HashMap<String,String>();
	
	private Map<String,String> typeMap = new HashMap<String,String>();
	
	private Map<String,String> fullMap = new HashMap<String,String>();
	
	private List<SysSystem> systemList = new ArrayList<SysSystem>();

	private List<SysComponent> componentList = new ArrayList<SysComponent>();

	private SysResource obj = new SysResource();
	
	private TreeNode node;
	
	private String name;
	
	private String code;
	
	private String sysId;
	
	private String roleId;
	
	private Integer status;
	
	/**
	 * 当前页面所有ID
	 */
	private Object[] allId;
	
	/**
	 * checkbox集合
	 */
	private Object[] sela;
    
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

	public SysResource getObj() {
		return obj;
	}

	public void setObj(SysResource obj) {
		this.obj = obj;
	}

	public TreeNode getNode() {
		return node;
	}

	public void setNode(TreeNode node) {
		this.node = node;
	}
	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}
	
	public List<SysComponent> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<SysComponent> list) {
		this.componentList = list;
	}

	public List<SysSystem> getSystemList() {
		return systemList;
	}

	public void setSystemList(List<SysSystem> systemList) {
		this.systemList = systemList;
	}

	public Map<String, String> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<String, String> typeMap) {
		this.typeMap = typeMap;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object[] getAllId() {
		return allId;
	}

	public void setAllId(Object[] allId) {
		this.allId = allId;
	}

	public Object[] getSela() {
		return sela;
	}

	public void setSela(Object[] sela) {
		this.sela = sela;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Map<String, String> getFullMap() {
		return fullMap;
	}

	public void setFullMap(Map<String, String> fullMap) {
		this.fullMap = fullMap;
	}

}
