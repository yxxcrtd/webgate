package cn.digitalpublishing.springmvc.form.system;

import java.util.ArrayList;
import java.util.List;

import cn.digitalpublishing.po.SysFunction;
import cn.digitalpublishing.po.SysPage;
import cn.digitalpublishing.springmvc.form.DataTableForm;

public class FunctionForm extends DataTableForm<SysFunction> {
	
	/**
	 * 页面列表
	 */
	private List<SysPage> pageList = new ArrayList<SysPage>();
	
	/**
	 * 功能对象
	 */
	private SysFunction obj = new SysFunction();
	
	/**
	 * 功能名称
	 */
	private String name;
	
	/**
	 * 功能编码
	 */
	private String code;
	
	/**
	 * 功能路径
	 */
	private String path;
	
	/**
	 * 页面ID
	 */
	private String pageId;
	
	/**
	 * 当前页面所有ID
	 */
	private Object[] allId;
	
	/**
	 * checkbox集合
	 */
	private Object[] sela;

	

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

	public SysFunction getObj() {
		return obj;
	}

	public void setObj(SysFunction obj) {
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<SysPage> getPageList() {
		return pageList;
	}

	public void setPageList(List<SysPage> pageList) {
		this.pageList = pageList;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.getCondition().put("pageId", pageId);
		this.pageId = pageId;
	}

}
