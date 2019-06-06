package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Set;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_系统资源
 * @see SYS_RESOURCE
 */
@SuppressWarnings("serial")
public class SysResource implements Serializable {
	
	/**
	 * 资源ID
	 */
	private String id;
	
	/**
	 * 资源名称
	 */
	private String name;
	
	/**
	 * 资源编号
	 */
	private String code;
	
	/**
	 * 资源链接
	 */
	private String link;
	
	/**
	 * 资源状态 1-在用 2-停用（菜单状态 上级菜单 决定下级菜单的状态）
	 */
	private Integer status;
	
	/**
	 * 资源叶子节点 1-是 2-不是（只有叶子节点可以挂功能页面）
	 */
	private Integer leaf;
	
	/**
	 * 资源排序
	 */
	private Integer order;
	
	/**
	 * 资源类型 1-菜单 2-接口
	 */
	private Integer type;
	
	/**
	 * 或计划参数
	 */
	private String locale;
	
	/**
	 * 系统
	 */
	private SysSystem system;
	
	/**
	 * 组件
	 */
	private SysComponent component;
	/**
	 * 资源层级编号
	 */
	private String treeCode;
	/**
	 * 上级资源
	 */
	private SysResource parentResource;
	
	/**
	 * 页面集合
	 */
	private Set<SysPage> pageSet;
	/**
	 * 资源参数
	 */
	private Set<SysParameter> parameterSet;
	
	/**
	 * 是否全路径 1-否 2-是
	 */
	private Integer full;
	
	/**
	 * 角色和资源关系集合
	 */
	private Set<SysRoleResourceRelationship> roleResourceRelationshipSet;
	
	/**
	 * 用于在页面checkbox选中的
	 */
	private Integer chooseType=0;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Set<SysRoleResourceRelationship> getRoleResourceRelationshipSet() {
		return roleResourceRelationshipSet;
	}

	public void setRoleResourceRelationshipSet(
			Set<SysRoleResourceRelationship> roleResourceRelationshipSet) {
		this.roleResourceRelationshipSet = roleResourceRelationshipSet;
	}

	public SysSystem getSystem() {
		return system;
	}

	public void setSystem(SysSystem system) {
		this.system = system;
	}

	public SysComponent getComponent() {
		return component;
	}

	public void setComponent(SysComponent component) {
		this.component = component;
	}

	public SysResource getParentResource() {
		return parentResource;
	}

	public void setParentResource(SysResource parentResource) {
		this.parentResource = parentResource;
	}

	public Set<SysPage> getPageSet() {
		return pageSet;
	}

	public void setPageSet(Set<SysPage> pageSet) {
		this.pageSet = pageSet;
	}

	public Integer getChooseType() {
		return chooseType;
	}

	public void setChooseType(Integer chooseType) {
		this.chooseType = chooseType;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Set<SysParameter> getParameterSet() {
		return parameterSet;
	}

	public void setParameterSet(Set<SysParameter> parameterSet) {
		this.parameterSet = parameterSet;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	public Integer getFull() {
		return full;
	}

	public void setFull(Integer full) {
		this.full = full;
	}
	
}