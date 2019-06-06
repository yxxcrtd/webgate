package cn.digitalpublishing.service.factory;

import cn.digitalpublishing.service.factory.impl.ServiceFactoryImpl;

public class ServiceFactory {
	
	private ServiceFactory() {};

	private static IServiceFactory factory = new ServiceFactoryImpl();

	public static IServiceFactory getInstance() {
		return factory;
	}

}
