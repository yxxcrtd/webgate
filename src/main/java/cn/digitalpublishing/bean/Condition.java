package cn.digitalpublishing.bean;

import java.util.List;

public class Condition {
	
	private String operation="AND";
	
	private List<Param> params;
	
	private List<Condition> conditions;

	private String hql;
}
