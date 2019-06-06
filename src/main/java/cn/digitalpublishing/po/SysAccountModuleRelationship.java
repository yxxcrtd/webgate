package cn.digitalpublishing.po;

import java.io.Serializable;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_账户和模块关系
 * @see SYS_AM_RELATIONSHIP
 */
@SuppressWarnings("serial")
public class SysAccountModuleRelationship implements Serializable {
	
	/**
	 * 账户和模块ID
	 */
	private String id;
	
	/**
	 * 位置
	 */
	private Integer location;
	
	/**
	 * 是否可以移动 1-可以 2-不可以
	 */
	private Integer move;
	
	/**
	 * 账户
	 */
	private SysAccount account;
	
	/**
	 * 模块
	 */
	private SysModule module;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysAccount getAccount() {
		return account;
	}

	public void setAccount(SysAccount account) {
		this.account = account;
	}

	public SysModule getModule() {
		return module;
	}

	public void setModule(SysModule module) {
		this.module = module;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public Integer getMove() {
		return move;
	}

	public void setMove(Integer move) {
		this.move = move;
	}
	
}