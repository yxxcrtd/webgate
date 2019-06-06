package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Set;

/**
 * @since 1.0
 * @author Liu Ben
 * @see 01_模块
 * @see SYS_MODULE
 */
@SuppressWarnings("serial")
public class SysModule implements Serializable {
	
	/**
	 * 模块ID
	 */
	private String id;
	
	/**
	 * 模块名称
	 */
	private String name;
	
	/**
	 * 是否国际化
	 */
	private String i18n;
	
	/**
	 * 模块链接
	 */
	private String link;
	
	/**
	 * 模块图标
	 */
	private String icon;
	
	/**
	 * 模块更多连接
	 */
	private String more;
	
	/**
	 * 模块是否必须 1-是 2-否
	 */
	private Integer must;
	
	/**
	 * 模块高度
	 */
	private Double height;
	
	/**
	 * 模块宽度
	 */
	private Double width;
	/**
	 * 判断 隐藏属性  （只显示用）
	 */
	private String hideShow;
	
	/**
	 * 组件信息
	 */
	private SysComponent component;
	
	private Set<SysAccountModuleRelationship> accountModuleRelationshipSet;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public Integer getMust() {
		return must;
	}

	public void setMust(Integer must) {
		this.must = must;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Set<SysAccountModuleRelationship> getAccountModuleRelationshipSet() {
		return accountModuleRelationshipSet;
	}

	public void setAccountModuleRelationshipSet(
			Set<SysAccountModuleRelationship> accountModuleRelationshipSet) {
		this.accountModuleRelationshipSet = accountModuleRelationshipSet;
	}

	public String getI18n() {
		return i18n;
	}

	public void setI18n(String i18n) {
		this.i18n = i18n;
	}

	public String getHideShow() {
		return hideShow;
	}

	public void setHideShow(String hideShow) {
		this.hideShow = hideShow;
	}

	public SysComponent getComponent() {
		return component;
	}

	public void setComponent(SysComponent component) {
		this.component = component;
	}
	
}