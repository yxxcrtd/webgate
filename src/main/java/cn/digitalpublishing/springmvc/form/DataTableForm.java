package cn.digitalpublishing.springmvc.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.model.Param;

public class DataTableForm<T> {
	
	private String id;
	
	private String msg;
	
	private String isSuccess;
	
	private String url;
	
	private String limit = Param.getParam("button.limit").get("limit");
	
	private Map<String,Object> condition = new HashMap<String,Object>();

	/**
	 * DataTable请求服务器端次数
	 */
	private int sEcho;

	/**
	 * 每页显示的数量
	 */
	private int iDisplayLength = 10;
	
	/**
	 * 过滤前总记录数
	 */
	private int iTotalRecords;
	
	/**
	 * 过滤后总记录数
	 */
	private int iTotalDisplayRecords;

	/**
	 * 分页时每页跨度数量
	 */
	private int iDisplayStart = 0;
	
	/**
	 * 返回的数据
	 */
	private List<T> aaData;
	
	private Integer total;
	private Integer page;
	private Integer pageSize;
	private List<T> rows;

	/**
	 * 过滤文本
	 */
	//private String sSearch;

	/**
	 * 列数
	 */
	//private int iColumns = 8;

	/**
	 * 排序列的数量
	 */
	//private int iSortingCols;

	/**
	 * 逗号分割所有的列
	 */
	//private String sColumns = "";
	//private String sColumns = "'id','name','link','more','icon','must','height','width'";

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		if (getPage() != null && getPageSize() != null) {
			setiDisplayStart((getPage() - 1) * getPageSize());
		}
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
		setRows(aaData);
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
		setTotal(iTotalRecords);
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		setiDisplayLength(pageSize);
	}

	/*public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}*/

	/*public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}*/

	/*public String[][] getAaData() {
		return aaData;
	}

	public void setAaData(String[][] aaData) {
		this.aaData = aaData;
	}*/
	
}