package cn.digitalpublishing.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SuppressWarnings("serial")
public class LanguageFilter extends HttpServlet implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chin) throws IOException, ServletException {
		//初始化语言
		HttpServletRequest req = (HttpServletRequest)request;
		if(req.getSession().getAttribute("lang")==null||"".equals(req.getSession().getAttribute("lang").toString())){
			SessionLocaleResolver sl = new SessionLocaleResolver();
			Locale locale=sl.resolveLocale(req);
			String lang = locale.getLanguage()+"_"+locale.getCountry();
			req.getSession().setAttribute("lang",lang);
		}
		chin.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
