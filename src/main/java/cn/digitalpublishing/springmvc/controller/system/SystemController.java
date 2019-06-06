package cn.digitalpublishing.springmvc.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.model.Param;
import cn.digitalpublishing.po.SysSystem;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.system.SystemForm;

@Controller
@RequestMapping("/pages/system")
public class SystemController extends BaseController {

	@RequestMapping(value="/form/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, SystemForm form) throws Exception {
		String forwardString="system/list";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			form.setStatusMap(Param.getParam("system.status",true,request.getSession().getAttribute("lang").toString()));
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value="/form/manager", headers="Accept=application/json")
	@ResponseBody
	public SystemForm manager(HttpServletRequest request, HttpServletResponse response, SystemForm form) throws Exception {
			
		List<SysSystem> systemList = new ArrayList<SysSystem>();
		try{
			Map<String, Object> condition = form.getCondition();
			form.setiTotalRecords(this.systemService.getSystemCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if(form.getiTotalRecords()>0){
				systemList = this.systemService.getSystemPagingList(condition, " order by a.id ", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(systemList);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, SystemForm form)throws Exception {
		String forwardString = "system/edit";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			form.setStatusMap(Param.getParam("system.status",true,request.getSession().getAttribute("lang").toString()));
			if(!Strings.isNullOrEmpty(request.getParameter("eid"))){
				form.setObj(this.systemService.getSystemById(request.getParameter("eid")));
				form.setId(request.getParameter("eid"));
			}
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value="/form/editSubmit")
	@ResponseBody
	public SystemForm editSubmit(HttpServletRequest request, HttpServletResponse response, SystemForm form)throws Exception {
		try{
			if(!Strings.isNullOrEmpty(form.getId())){
				//修改
				this.systemService.updateSystem(form.getObj(), form.getId(), null);
				form.setMsg(Lang.getLanguage("System.Info.Update.Success",request.getSession().getAttribute("lang").toString()));
			}else{
				//新增
				SysSystem system = new SysSystem();
				system.setName(form.getObj().getName());
				system.setCode(form.getObj().getCode());
				system.setStatus(form.getObj().getStatus());
				this.systemService.insertSystem(system);
				form.setMsg(Lang.getLanguage("System.Info.Add.Success",request.getSession().getAttribute("lang").toString()));
			}
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/delete")
	@ResponseBody
	public SystemForm delete(HttpServletRequest request, HttpServletResponse response, SystemForm form) throws Exception {
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("sysId", request.getParameter("id"));
			Integer componentCount = this.systemService.getComponentCount(condition);
			Integer resourceCount = this.systemService.getResourceCount(condition);
			Integer roleCount = this.systemService.getRoleCount(condition);
			if (componentCount > 0) {
				form.setMsg(Lang.getLanguage("System.Info.Delete.Exist.Component",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(FAILURE);
			} else if (resourceCount > 0) {
				form.setMsg(Lang.getLanguage("System.Info.Delete.Exist.Resource",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(FAILURE);
			} else if (roleCount > 0) {
				form.setMsg(Lang.getLanguage("System.Info.Delete.Exist.Role",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(FAILURE);
			} else {
				this.systemService.deleteSystemById(request.getParameter("id"));
				form.setMsg(Lang.getLanguage("System.Info.Delete.Success",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(SUCCESS);
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/validateCodeUnique")
	@ResponseBody
	public SystemForm validateCodeUnique(HttpServletRequest request, HttpServletResponse response, SystemForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>(); 
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("code", form.getCode());
			form.setiTotalRecords(this.systemService.getSystemCount(condition));
			if(form.getiTotalRecords()>0) {
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("System.Info.ValidateCodeUnique.Failure",request.getSession().getAttribute("lang").toString()));
			} else {
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("System.Info.ValidateCodeUnique.Success",request.getSession().getAttribute("lang").toString()));
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return form;
	}
	
	@RequestMapping(value="/form/validateNameUnique")
	@ResponseBody
	public SystemForm validateNameUnique(HttpServletRequest request, HttpServletResponse response, SystemForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>(); 
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("name", form.getName());
			form.setiTotalRecords(this.systemService.getSystemCount(condition));
			if(form.getiTotalRecords()>0) {
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("System.Info.ValidateNameUnique.Failure",request.getSession().getAttribute("lang").toString()));
			} else {
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("System.Info.ValidateNameUnique.Success",request.getSession().getAttribute("lang").toString()));
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return form;
	}
	
}