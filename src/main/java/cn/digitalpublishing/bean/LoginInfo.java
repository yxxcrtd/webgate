package cn.digitalpublishing.bean;

import cn.com.daxtech.perm.po.SysUser;
import cn.digitalpublishing.po.SysAccount;
import cn.digitalpublishing.po.SysRole;

public class LoginInfo {
	
	/**
	 * 角色
	 */
	private SysRole role;
	
	/**
	 * 账户
	 */
	private SysAccount account;
	
	/**
	 * 语言
	 */
	private String lang;
	
	public LoginInfo(){
		
	}
	public LoginInfo(SysAccount account ,SysRole role){
		this.account = account;
		this.role = role ;
	}
	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}
	public SysAccount getAccount() {
		return account;
	}
	public void setAccount(SysAccount account) {
		this.account = account;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
}
