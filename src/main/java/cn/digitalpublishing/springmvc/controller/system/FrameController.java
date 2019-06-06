package cn.digitalpublishing.springmvc.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.SysAccount;
import cn.digitalpublishing.po.SysResource;
import cn.digitalpublishing.po.SysRole;
import cn.digitalpublishing.po.SysRoleAccountRelationship;
import cn.digitalpublishing.po.SysSystem;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.system.AccountForm;
import cn.digitalpublishing.springmvc.form.system.ResourceForm;

@Controller
@RequestMapping("/pages")
public class FrameController extends BaseController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, AccountForm form) throws Exception {
		log.info("index ...");
		String forwardString = "/index";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			SysAccount sessionAccount = (SysAccount) request.getSession().getAttribute("ccsfw_account");
			SysRole sessionRole = (SysRole) request.getSession().getAttribute("ccsfw_role");
			SysSystem sessionSystem = (SysSystem) request.getSession().getAttribute("ccsfw_sys");
			if (sessionAccount == null) {
				throw new CcsException();
			}
			form.setAccountId(sessionAccount.getId());
			form.setSysId(sessionSystem.getId());
			form.setRoleId(sessionRole.getId());
			//获取默认首页
		} catch (Exception e) {
			forwardString = "/login";
		}
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	}
	
	@RequestMapping(value = "/frame/getResourceZTreeByRoleId")
	@ResponseBody
	public ResourceForm getResourceZTreeByRoleId(HttpServletRequest request,HttpServletResponse response, ResourceForm form) throws Exception{
		try {
			Map<String,Object> resourceCondition = new HashMap<String,Object>();
			resourceCondition.put("sysId", form.getSysId());
			resourceCondition.put("roleId", form.getRoleId());
			List<SysResource> resourceList = systemService.getResourceListByRoleId(resourceCondition, " order by a.order,length(a.code) ");
			form.setAaData(resourceList);
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value = "/frame/getRoleListByAccountId")
	@ResponseBody
	public AccountForm getRoleListByAccountId(HttpServletRequest request,HttpServletResponse response, AccountForm form) throws Exception{
		try {
			Map<String,Object> accountCondition = new HashMap<String,Object>();
			accountCondition.put("accountId", form.getId());
			List<SysRole> roleList = new ArrayList<SysRole>();
			List<SysRoleAccountRelationship> roleAccountRelationshipList = this.systemService.getRoleAccountRelationshipListByAccountId(accountCondition, null);
			for (SysRoleAccountRelationship sysRoleAccountRelationship : roleAccountRelationshipList) {
				SysRole role = sysRoleAccountRelationship.getRole();
				roleList.add(role);
			}
			form.setRoleList(roleList);
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
		}
		return form;
	}

	@RequestMapping(value="/frame/switchRole", headers="Accept=application/json")
	@ResponseBody
	public AccountForm switchRole(HttpServletRequest request, HttpServletResponse response, AccountForm form) throws Exception {
		try {
			SysRole role = this.systemService.getRoleById(form.getId());
			HttpSession session = request.getSession();
			session.removeAttribute("ccsfw_role");
			session.removeAttribute("ccsfw_sys");
			session.setAttribute("ccsfw_role", role);
			session.setAttribute("ccsfw_sys", role.getSystem());
			String random = new Date().getTime() +"_"+ Math.random();
			session.setAttribute("ticket", random);
			form.setSysId(role.getSystem().getId());
			form.setRoleId(role.getId());
			form.setMsg(Lang.getLanguage("Login.SwitchRole.Success", request.getSession().getAttribute("lang").toString()) + role.getName());
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			form.setMsg((e instanceof CcsException) ? ((CcsException) e).getPrompt() : e.getMessage());
			form.setIsSuccess(FAILURE);
		}
		return form;
	}
	
	
	@RequestMapping(value="/idx")
	public ModelAndView idx(HttpServletRequest request,HttpServletResponse response, AccountForm form) throws Exception {
		String forwardString = "main";
		Map<String, Object> model = new HashMap<String, Object>();
		/**
		List<SysModule> list = new ArrayList<SysModule>();
		//必设模块
		list.addAll(this.getModuleService().getModulePagging(null,1));
		
		
		SysUser user = (SysUser)request.getSession().getAttribute("ccsfw_user");
		String userId = user.getId();
		List<SysModule> myList = this.getModuleService().getMyModule(userId,1);
		if(myList != null && !myList.isEmpty()){
			list.addAll(myList);
		}
		
		
		model.put("list", list);
		model.put("uid", userId);
		**/
		return new ModelAndView(forwardString,model);
	}
}
