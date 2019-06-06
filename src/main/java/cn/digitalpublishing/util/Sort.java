package cn.digitalpublishing.util;

public enum Sort {
	
	Asc("asc"),Desc("desc");

	private final String sort;  

	private Sort(String _day) {  
		this.sort = _day; 
	}
	
	public String getValue(){
		return this.sort;
	}

}
