package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Set;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_功能
 * @see SYS_FUNCTION
 */
@SuppressWarnings("serial")
public class SysFunction implements Serializable {
	
	/**
	 * 功能ID
	 */
	private String id;
	
	/**
	 * 功能名称
	 */
	private String name;
	
	/**
	 * 功能编号
	 */
	private String code;
	
	/**
	 * 功能路径
	 */
	private String path;
	
	/**
	 * 页面
	 */
	private SysPage page;
	
	/**
	 * 角色和功能关系集合
	 */
	private Set<SysRoleFunctionRelationship> roleFunctionRelationshipSet;
	
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public SysPage getPage() {
		return page;
	}

	public void setPage(SysPage page) {
		this.page = page;
	}

	public Set<SysRoleFunctionRelationship> getRoleFunctionRelationshipSet() {
		return roleFunctionRelationshipSet;
	}

	public void setRoleFunctionRelationshipSet(
			Set<SysRoleFunctionRelationship> roleFunctionRelationshipSet) {
		this.roleFunctionRelationshipSet = roleFunctionRelationshipSet;
	}

	public Integer getChooseType() {
		return chooseType;
	}

	public void setChooseType(Integer chooseType) {
		this.chooseType = chooseType;
	}
	
}