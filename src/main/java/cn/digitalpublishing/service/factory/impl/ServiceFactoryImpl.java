package cn.digitalpublishing.service.factory.impl;

import cn.com.daxtech.framework.web.service.SpringBeanService;
import cn.digitalpublishing.service.SystemService;
import cn.digitalpublishing.service.factory.IServiceFactory;

public class ServiceFactoryImpl implements IServiceFactory {
	
	public SystemService getSystemService() {
		// TODO Auto-generated method stub
		return (SystemService)SpringBeanService.getService("systemService");
	}

}
