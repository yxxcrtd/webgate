package cn.digitalpublishing.springmvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import cn.digitalpublishing.service.SystemService;

public class BaseController extends MultiActionController{
	
	public Logger log = Logger.getLogger(this.getClass());
	
	public static final String FAILURE = "false";
	
	public static final String SUCCESS = "true";
	
	@Autowired
	@Qualifier("systemService")
	protected SystemService systemService;
	
	/**
	 * 返回ajax请求后的信息
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public void ajaxReturn(HttpServletResponse response,String result) throws IOException{
		PrintWriter writer = null;
	    try {
	    	response.setContentType("text/html;charset=utf-8");
			//获取输出流
			writer = response.getWriter();
			writer.print(result);
		} catch (IOException e) {
			throw e;
		} finally{
			if(writer != null){
				writer.close();
			}
		}
	}
	
	/**
	 * 返回ajax请求后的json信息
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public void ajaxReturnJson(HttpServletResponse response,JSONObject json) throws IOException{
		PrintWriter writer = null;
	    try {
	    	response.setContentType("text/json;charset=UTF-8");
			//获取输出流
			writer = response.getWriter();
			writer.print(json.toString());
			writer.flush();
		} catch (IOException e) {
			throw e;
		} finally{
			if(writer != null){
				writer.close();
			}
		}
	}
}
