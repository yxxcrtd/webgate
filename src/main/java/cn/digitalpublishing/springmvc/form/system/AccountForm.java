package cn.digitalpublishing.springmvc.form.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.SysAccount;
import cn.digitalpublishing.po.SysRole;
import cn.digitalpublishing.springmvc.form.DataTableForm;

public class AccountForm extends DataTableForm<SysAccount> {
	
	/**
	 * Status下拉框列表
	 */
	private Map<String,String> statusMap = new HashMap<String,String>();
	
	/**
	 * Level下拉框列表
	 */
	private Map<String,String> levelMap = new HashMap<String,String>();
	
	/**
	 * Type下拉框列表
	 */
	private Map<String,String> typeMap = new HashMap<String,String>();
	
	/**
	 * 角色列表
	 */
	private List<SysRole> roleList = new ArrayList<SysRole>();
	
	/**
	 * 角色id
	 */
	private String roleId;
	
	/**
	 * 系统id
	 */
	private String sysId;
	
	/**
	 * 账户id
	 */
	private String accountId;
	
	/**
	 * 验证码
	 */
	private String validateCode;
	
	/**
	 * 账户对象
	 */
	private SysAccount obj = new SysAccount();
	
	/**
	 * 账户名称
	 */
	private String name;
	
	/**
	 * 账户密码
	 */
	private String pass;
	
	/**
	 * 账户状态 1-在用 2-停用
	 */
	private Integer status;
	
	/**
	 * 账户类型 1-本地账户 2-Sina账户
	 */
	private Integer type;
	/**
	 * 源密码
	 */
	private String origin_pass;
	/**
	 * 新密码
	 */
	private String new_pass;
	/**
	 * 确认密码
	 */
	private String confirm_pass;
	
	/**
	 * 账户级别 1-超级用户（不能维护不能删除） 2-普通用户（可维护可删除）
	 */
	private Integer level;

    /**
     * 记住我
     */
    private String remember;

	public SysAccount getObj() {
		return obj;
	}

	public void setObj(SysAccount obj) {
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.getCondition().put("name", name);
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.getCondition().put("status", status);
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.getCondition().put("type", type);
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.getCondition().put("level", level);
		this.level = level;
	}

	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}

	public Map<String, String> getLevelMap() {
		return levelMap;
	}

	public void setLevelMap(Map<String, String> levelMap) {
		this.levelMap = levelMap;
	}

	public Map<String, String> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<String, String> typeMap) {
		this.typeMap = typeMap;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public String getOrigin_pass() {
		return origin_pass;
	}

	public void setOrigin_pass(String origin_pass) {
		this.origin_pass = origin_pass;
	}

	public String getNew_pass() {
		return new_pass;
	}

	public void setNew_pass(String new_pass) {
		this.new_pass = new_pass;
	}

	public String getConfirm_pass() {
		return confirm_pass;
	}

	public void setConfirm_pass(String confirm_pass) {
		this.confirm_pass = confirm_pass;
	}

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }
}
