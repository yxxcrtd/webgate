package cn.digitalpublishing.springmvc.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.digitalpublishing.util.SystemConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.bean.LoginInfo;
import cn.digitalpublishing.bean.SessionIdMap;
import cn.digitalpublishing.constants.ConstantsSession;
import cn.digitalpublishing.po.SysAccount;
import cn.digitalpublishing.po.SysRoleAccountRelationship;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.system.AccountForm;
import cn.digitalpublishing.springmvc.form.system.CommonForm;
import cn.digitalpublishing.springmvc.form.system.RoleForm;
import cn.digitalpublishing.springmvc.form.system.SessionForm;

@Controller
@RequestMapping("/pages/common")
public class CommonController extends BaseController {

	public static final String VALIDATE_CODE = "validateCode";
	
	public static final String GLOBAL_LOGIN_URL = "/pages/login.jsp";
	
	private final static String NO_LOGIN="Global.NoLogin";
	
	private final static String LOGIN_FAIL="Global.LoginFail";
	
	private final static String DENY_ACCESS="Global.DenyAccess";
	
	@RequestMapping(value = "/sessionListener")
	public ModelAndView sessionListener(HttpServletRequest request, HttpServletResponse response, SessionForm form) throws Exception {
		String forwardString = "expired";
		Map<String,Object> model = new HashMap<String,Object>();
		form.setMsg(request.getAttribute("errmsg")!=null?request.getAttribute("errmsg").toString():"Global.sign.expired");
		form.setUrl(GLOBAL_LOGIN_URL);
		form.setExist(SessionForm.EXIST_FALSE);
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	} 

	@RequestMapping(value="login", headers="Accept=application/json")
	@ResponseBody
	public AccountForm login(HttpServletRequest request, HttpServletResponse response, AccountForm form) throws Exception {
		try {
			if (!request.getMethod().equals("POST")) {
				throw new CcsException(Lang.getLanguage("Login.Method.Error", request.getSession().getAttribute("lang").toString()));
			}

            //检测验证码
            checkValidateCode(request, form);

            String forwardStr = "/pages/index";

            String username = form.getName() == null ? "" : form.getName();
            String password = form.getPass() == null ? "" : form.getPass();
            Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("username", username);
			condition.put("password", password);
            condition.put("status", SystemConstants.ACCOUNT_STATUS_USING);
			SysAccount account = this.systemService.login(condition, null, false);
			if (account == null) {
				throw new CcsException(Lang.getLanguage("Login.Username.Password.Error", request.getSession().getAttribute("lang").toString()));
			} else {
				Map<String, Object> accountCondition = new HashMap<String, Object>();
				accountCondition.put("accountId", account.getId());
				List<SysRoleAccountRelationship> roleAccountRelationshipList = this.systemService.getRoleAccountRelationshipListByAccountId(accountCondition, null);
				if (roleAccountRelationshipList != null && !roleAccountRelationshipList.isEmpty()) {
					SysRoleAccountRelationship roleAccountRelationship = (SysRoleAccountRelationship) roleAccountRelationshipList.get(0);
					HttpSession session = request.getSession();
					session.setAttribute("ccsfw_account", account);
					session.setAttribute("ccsfw_sys", roleAccountRelationship.getRole().getSystem());
					session.setAttribute("ccsfw_role", roleAccountRelationship.getRole());
					session.setAttribute(ConstantsSession.SESSION_USER, account.getId());
					String random = new Date().getTime() +"_"+ Math.random();
					session.setAttribute(ConstantsSession.SESSION_TICKET, random);
					SessionIdMap.put(random, new LoginInfo(account, roleAccountRelationship.getRole()));

                    //保存Cookie状态
                    if (form.getRemember() != null && !"".equals(form.getRemember())) {
                        Cookie cookie = new Cookie("username", username);
                        // cookie保存一个月
                        cookie.setMaxAge(30*60*60*24);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }

					form.setMsg(Lang.getLanguage("Login.Login.Success", request.getSession().getAttribute("lang").toString()));
					form.setIsSuccess(SUCCESS);
					form.setUrl(forwardStr);
				}else{
					throw new CcsException(Lang.getLanguage("Login.Role.Error", request.getSession().getAttribute("lang").toString()));
				}
			}
		} catch (Exception e) {
			form.setMsg((e instanceof CcsException) ? ((CcsException) e).getPrompt() : e.getMessage());
			form.setIsSuccess(FAILURE);
		}
		return form;
	}
	
	protected void checkValidateCode(HttpServletRequest request, AccountForm form) throws CcsException{
		HttpSession session = request.getSession();
		String sessionValidateCode = null;
		Object obj = session.getAttribute(VALIDATE_CODE);
		if (obj == null) {
			sessionValidateCode = "";
		} else {
			sessionValidateCode = obj.toString();
		}
		//让上一次的验证码失效
		session.setAttribute(VALIDATE_CODE, null);

		String validateCode = form.getValidateCode();
		if (validateCode==null || validateCode.trim().equals("") || !sessionValidateCode.equalsIgnoreCase(validateCode)) {
			throw new CcsException(Lang.getLanguage("Login.Checkcode.Error", request.getSession().getAttribute("lang").toString()));
		}
	}
	
	@RequestMapping(value="logout", headers="Accept=application/json")
	@ResponseBody
	public AccountForm logout(HttpServletRequest request, HttpServletResponse response, AccountForm form) throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("ccsfw_account");
		session.removeAttribute("ccsfw_sys");
		session.removeAttribute("ccsfw_role");
		session.removeAttribute("#_uid");
		String sessionId = session.getAttribute("ticket")!=null?session.getAttribute("ticket").toString():"";
		if(!"".equals(sessionId)){
			SessionIdMap.remove(sessionId);
			session.removeAttribute("ticket");
		}
		form.setIsSuccess(SUCCESS);
		form.setUrl("/pages/login.jsp");
		return form;
	}
	
	@RequestMapping(value = "/prompt")
	public ModelAndView prompt(HttpServletRequest request, HttpServletResponse response, CommonForm form) throws Exception {
		String forwardString = "prompt";
		Map<String,Object> model = new HashMap<String,Object>();
		form.setMsg(request.getParameter("pt_msg")!=null?request.getParameter("pt_msg").toString():"Global.Msg.Error");
		if(NO_LOGIN.equals(form.getMsg())||LOGIN_FAIL.equals(form.getMsg())){
			form.setUrl(GLOBAL_LOGIN_URL);
		}
		form.setCode(request.getParameter("pt_msg_code")!=null?request.getParameter("pt_msg_code").toString():"Global.Code.Error");
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	}
	
	@RequestMapping(value="/index")
	public void index(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception {
		request.setAttribute("pt_msg",DENY_ACCESS);
		request.setAttribute("pt_msg_code","405");
		RequestDispatcher rd = request.getRequestDispatcher("prompt");
		rd.forward(request,response);
		return;
	}

}
