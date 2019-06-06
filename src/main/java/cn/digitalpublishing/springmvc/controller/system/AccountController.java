package cn.digitalpublishing.springmvc.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.model.Param;
import cn.digitalpublishing.po.SysAccount;
import cn.digitalpublishing.po.SysRole;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.system.AccountForm;
import cn.digitalpublishing.springmvc.form.system.RoleForm;

@Controller
@RequestMapping("/pages/account")
public class AccountController extends BaseController {
	
	public static final Integer ENCRYPTION_NO = 1;
	
	public static final Integer ENCRYPTION_YES = 2;

	@RequestMapping(value="/form/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, AccountForm form) throws Exception {
		String forwardString="account/list";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			form.setStatusMap(Param.getParam("account.status",true,request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			form.setTypeMap(Param.getParam("account.type",true,request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			form.setLevelMap(Param.getParam("account.level",true,request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}

	@RequestMapping(value="/form/manager", headers="Accept=application/json")
	@ResponseBody
	public AccountForm manager(HttpServletRequest request, HttpServletResponse response, AccountForm form) throws Exception {
			
		List<SysAccount> accountList = new ArrayList<SysAccount>();
		try{
			Map<String, Object> condition = form.getCondition();
			form.setiTotalRecords(this.systemService.getAccountCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if(form.getiTotalRecords()>0){
				accountList = this.systemService.getAccountPagingList(condition, " order by a.id ", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(accountList);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, AccountForm form)throws Exception {
		String forwardString = "account/edit";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			form.setStatusMap(Param.getParam("account.status",true,request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			form.setTypeMap(Param.getParam("account.type",true,request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			form.setLevelMap(Param.getParam("account.level",true,request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			if(!Strings.isNullOrEmpty(request.getParameter("eid"))){
				form.setObj(this.systemService.getAccountById(request.getParameter("eid")));
				form.setId(request.getParameter("eid"));
			}
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}

	@RequestMapping(value="/form/editSubmit")
	@ResponseBody
	public AccountForm editSubmit(HttpServletRequest request, HttpServletResponse response, AccountForm form)throws Exception {
		try{
			if(!Strings.isNullOrEmpty(form.getId())){
				//修改
				this.systemService.updateAccount(form.getObj(), form.getId(), null);
				form.setMsg(Lang.getLanguage("Account.Info.Update.Success",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			}else{
				//新增
				SysAccount account = new SysAccount();
				account.setName(form.getObj().getName());
				account.setPass(form.getObj().getPass());
				account.setStatus(form.getObj().getStatus());
				account.setType(form.getObj().getType());
				account.setLevel(form.getObj().getLevel());
				this.systemService.insertAccount(account);
				form.setMsg(Lang.getLanguage("Account.Info.Add.Success",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			}
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/delete")
	@ResponseBody
	public AccountForm delete(HttpServletRequest request, HttpServletResponse response, AccountForm form) throws Exception {
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("accountId", request.getParameter("id"));
			Integer moduleCount = this.systemService.getAccountModuleRelationshipCountByRelationId(condition);
			Integer roleCount = this.systemService.getRoleAccountRelationshipCountByRelationId(condition);
			if (moduleCount > 0) {
				form.setMsg(Lang.getLanguage("Account.Info.Delete.Exist.Module",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
				form.setIsSuccess(FAILURE);
			} else if (roleCount > 0) {
				form.setMsg(Lang.getLanguage("Account.Info.Delete.Exist.Role",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
				form.setIsSuccess(FAILURE);
			} else {
				this.systemService.deleteAccountById(request.getParameter("id"));
				form.setMsg(Lang.getLanguage("Account.Info.Delete.Success",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
				form.setIsSuccess(SUCCESS);
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		return form;
	}

	@RequestMapping(value="/form/roleIndex")
	public ModelAndView roleIndex(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception {
		String forwardString="account/role_list";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			Map<String,Object> condition = new HashMap<String,Object>();
			form.setSystemList(this.systemService.getSystemList(condition, null));
			form.setStatusMap(Param.getParam("role.status",true,request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}

	@RequestMapping(value="/form/roleManager", headers="Accept=application/json")
	@ResponseBody
	public RoleForm roleManager(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception {
			
		List<SysRole> accountList = new ArrayList<SysRole>();
		String accountId = request.getParameter("accountId");
		try{
			Map<String, Object> condition = form.getCondition();
			condition.put("accountId", accountId);
			form.setiTotalRecords(this.systemService.getRoleCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if(form.getiTotalRecords()>0){
				accountList = this.systemService.getRolePagingList(condition, " order by a.id ", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(accountList);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/batchSaveRole")
	@ResponseBody
	public RoleForm batchSaveRole(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception {
		try{
			this.systemService.batchSaveRoleAccountRelationship(form.getAllId(),form.getSela(),form.getId());
			form.setMsg(Lang.getLanguage("Role.Info.Batch.Save.Success",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));//设置类型成功！
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/validateNameUnique")
	@ResponseBody
	public AccountForm validateNameUnique(HttpServletRequest request, HttpServletResponse response, AccountForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>(); 
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("name", form.getName());
			form.setiTotalRecords(this.systemService.getAccountCount(condition));
			if(form.getiTotalRecords()>0) {
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Account.Info.ValidateNameUnique.Failure",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			} else {
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Account.Info.ValidateNameUnique.Success",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		model.put("form", form);
		return form;
	}

	@RequestMapping(value = "/form/resetPassword")
	@ResponseBody
	public AccountForm resetPassword(HttpServletRequest request, HttpServletResponse response, AccountForm form) throws Exception {
		try {
			String id = request.getParameter("id");
			SysAccount s = new SysAccount();
			s.setId(id);
			s.setPass("123456");
			this.systemService.updateAccount(s, s.getId(), null);
			form.setIsSuccess(SUCCESS);
			form.setMsg(Lang.getLanguage("Account.Info.ResetPass.Success" , request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN") + s.getPass());
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/setPasswd")
	public ModelAndView setPasswd(HttpServletRequest request,HttpServletResponse response, AccountForm form)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String forwardString = "account/passwdSet";
		try{
			String accountId = request.getSession().getAttribute("#_uid").toString();
			form.setObj(this.systemService.getAccountById(accountId));
			form.setId(accountId);
			form.setName(form.getObj().getName());
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	}
	
	@RequestMapping(value="/form/setPasswdSubmit", headers="Accept=application/json")
	@ResponseBody
	public AccountForm setPasswdSubmit(HttpServletRequest request,HttpServletResponse response, AccountForm form)throws Exception {
		try{
			String opwd = form.getOrigin_pass();
			String npwd = form.getNew_pass();
			String accountId = form.getId();
			form.setObj(this.systemService.getAccountById(accountId));
			String pwd = opwd;
			if(form.getObj().getEncryption()!=null&&form.getObj().getEncryption()==ENCRYPTION_YES){
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				pwd=md5.encodePassword(opwd,form.getObj().getName());
				npwd = md5.encodePassword(npwd,form.getObj().getName());
			}
			if(pwd.equals(form.getObj().getPass())){
				form.getObj().setPass(npwd);
				this.systemService.updateAccount(form.getObj(),accountId, null);
				form.setMsg(Lang.getLanguage("Account.Info.Passwd.Set.Success",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
				form.setIsSuccess(SUCCESS);
				
			}else{
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Account.Info.Passwd.Set.Failure",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		form.setObj(null);
		return form;
	}
	
	@RequestMapping(value="/form/validatePassCorrect",headers="Accept=application/json")
	@ResponseBody
	public AccountForm validatePassCorrect(HttpServletRequest request, HttpServletResponse response, AccountForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>(); 
		try{
			String opwd = request.getParameter("opass").toString();
			String accountId = request.getSession().getAttribute("#_uid").toString();
			form.setObj(this.systemService.getAccountById(accountId));
			String pwd = opwd;
			if(form.getObj().getEncryption()!=null&&form.getObj().getEncryption()==ENCRYPTION_YES){
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				pwd=md5.encodePassword(opwd,form.getObj().getName());
			}
			if(pwd.equals(form.getObj().getPass())){
				form.setMsg(Lang.getLanguage("Account.Info.Origin.Passwd.Correct",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
				form.setIsSuccess(SUCCESS);
				
			}else{
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Account.Info.Origin.Passwd.Not.Correct",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
		}
		form.setObj(null);
		model.put("form", form);
		return form;
	}

}