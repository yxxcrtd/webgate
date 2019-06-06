package cn.digitalpublishing.service.factory;

import cn.digitalpublishing.service.SystemService;

public interface IServiceFactory {
	
	/**
	 * 敏感词服务
	 * @return
	 */
	public SystemService getSystemService();

}
