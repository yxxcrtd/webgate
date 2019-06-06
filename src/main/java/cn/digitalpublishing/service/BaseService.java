package cn.digitalpublishing.service;

import java.util.Properties;
import java.util.Vector;

public interface BaseService {
	
	public Vector<Properties> getVector(String hql,Object[] condition,String sort, String className)throws Exception;
	
	public Vector<Properties> getVector(String property,String field,String hql,Object[] condition,String sort, String className)throws Exception;
	
}
