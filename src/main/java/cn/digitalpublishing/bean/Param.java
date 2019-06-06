package cn.digitalpublishing.bean;

public class Param {
	
	/**
	 * 参数名
	 */
	private String name;
	/**
	 * 参数类型
	 */
	private String type;
	
	private Boolean array;
	
	public Boolean getArray() {
		return array;
	}

	public void setArray(Boolean array) {
		this.array = array;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}
