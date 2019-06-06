package cn.digitalpublishing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.digitalpublishing.service.factory.IServiceFactory;
import cn.digitalpublishing.service.factory.impl.ServiceFactoryImpl;

@SuppressWarnings("serial")
public class ImportDataServlet extends HttpServlet {
	
	IServiceFactory serviceFactory = null; 
	
	public ImportDataServlet(){
		serviceFactory = (IServiceFactory)new ServiceFactoryImpl();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = getInitParameter("path");
		try {
			this.serviceFactory.getSystemService().initDataByExcel(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
