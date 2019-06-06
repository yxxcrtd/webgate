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
import cn.digitalpublishing.po.SysComponent;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.system.ComponentForm;
import cn.digitalpublishing.util.SystemConstants;

@Controller
@RequestMapping("/pages/component")
public class ComponentController extends BaseController {

	@RequestMapping(value="/form/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, ComponentForm form) throws Exception {
		String forwardString="component/list";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			// 查询所有系统状态为“在用”状态的系统列表
			condition.put("status", SystemConstants.SYSTEM_STATUS_USING);
			form.setSystemList(this.systemService.getSystemList(condition, null));
			form.setStatusMap(Param.getParam("component.status",true,request.getSession().getAttribute("lang").toString()));
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
	public ComponentForm manager(HttpServletRequest request, HttpServletResponse response, ComponentForm form) throws Exception {
		
		List<SysComponent> componentList = new ArrayList<SysComponent>();
		try{
			Map<String, Object> condition = form.getCondition();
			form.setiTotalRecords(this.systemService.getComponentCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if(form.getiTotalRecords()>0){
				componentList = this.systemService.getComponentPagingList(condition, " order by a.id ", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(componentList);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, ComponentForm form)throws Exception {
		String forwardString = "component/edit";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			// 查询所有系统状态为“在用”状态的系统列表
			condition.put("status", SystemConstants.SYSTEM_STATUS_USING);
			form.setSystemList(this.systemService.getSystemList(condition, null));
			form.setStatusMap(Param.getParam("component.status",true,request.getSession().getAttribute("lang").toString()));
			if(!Strings.isNullOrEmpty(request.getParameter("eid"))){
				form.setObj(this.systemService.getComponentById(request.getParameter("eid")));
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
	public ComponentForm editSubmit(HttpServletRequest request, HttpServletResponse response, ComponentForm form)throws Exception {
		try{
			if(!Strings.isNullOrEmpty(form.getId())){
				//修改
				this.systemService.updateComponent(form.getObj(), form.getId(), null);
				form.setMsg(Lang.getLanguage("Component.Info.Update.Success",request.getSession().getAttribute("lang").toString()));
			}else{
				//新增
				SysComponent component = new SysComponent();
				component.setCode(form.getObj().getCode());
				component.setName(form.getObj().getName());
				component.setAddress(form.getObj().getAddress());
				component.setPort(form.getObj().getPort());
				component.setStatus(form.getObj().getStatus());
				component.setSystem(form.getObj().getSystem());
				this.systemService.insertComponent(component);
				form.setMsg(Lang.getLanguage("Component.Info.Add.Success",request.getSession().getAttribute("lang").toString()));
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
	public ComponentForm delete(HttpServletRequest request, HttpServletResponse response, ComponentForm form) throws Exception {
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("componentId", request.getParameter("id"));
			Integer count = this.systemService.getResourceCount(condition);
			if (count > 0) {
				form.setMsg(Lang.getLanguage("Component.Info.Delete.Exist.Resource",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(FAILURE);
			} else {
				this.systemService.deleteComponentById(request.getParameter("id"));
				form.setMsg(Lang.getLanguage("Component.Info.Delete.Success",request.getSession().getAttribute("lang").toString()));
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
	public ComponentForm validateCodeUnique(HttpServletRequest request, HttpServletResponse response,ComponentForm form) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("code", form.getCode());
			condition.put("sysId", form.getSysId());
			if(!Strings.isNullOrEmpty(form.getId())){
				condition.put("uniqueId", form.getId());
			}
			form.setiTotalRecords(this.systemService.getComponentCount(condition));
			if(form.getiTotalRecords()>0){
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Component.Info.ValidateCodeUnique.Failure",request.getSession().getAttribute("lang").toString()));
			}else{
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Component.Info.ValidateCodeUnique.Success",request.getSession().getAttribute("lang").toString()));
			}
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return form;
	}
	
	@RequestMapping(value="/form/validateNameUnique")
	@ResponseBody
	public ComponentForm validateNameUnique(HttpServletRequest request,HttpServletResponse response,ComponentForm form) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("name", form.getName());
			condition.put("sysId", form.getSysId());
			if(!Strings.isNullOrEmpty(form.getId())){
				condition.put("uniqueId", form.getId());
			}
			form.setiTotalRecords(this.systemService.getComponentCount(condition));
			if(form.getiTotalRecords()>0){
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Component.Info.ValidateNameUnique.Failure",request.getSession().getAttribute("lang").toString()));
			}else{
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Component.Info.ValidateNameUnique.Success",request.getSession().getAttribute("lang").toString()));
			}
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return form;
	}
	
	@RequestMapping(value="/form/validatePortUnique")
	@ResponseBody
	public ComponentForm validatePortUnique(HttpServletRequest request,HttpServletResponse response,ComponentForm form) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("port", form.getPort());
			form.setiTotalRecords(this.systemService.getComponentCount(condition));
			if(form.getiTotalRecords()>0){
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Component.Info.ValidatePortUnique.Failure", request.getSession().getAttribute("lang").toString()));
			}else{
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Component.Info.ValidatePortUnique.Success", request.getSession().getAttribute("lang").toString()));
			}
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return form;
		
	}
	
	@RequestMapping(value="/form/validateAddressUnique")
	@ResponseBody
	public ComponentForm validateAddressUnique(HttpServletRequest request,HttpServletResponse response,ComponentForm form) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("address", form.getAddress());
			form.setiTotalRecords(this.systemService.getComponentCount(condition));
			if(form.getiTotalRecords()>0){
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Component.Info.ValidateAddressUnique.Failure", request.getSession().getAttribute("lang").toString()));
			}else{
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Component.Info.ValidateAddressUnique.Success", request.getSession().getAttribute("lang").toString()));
			}
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return form;
		
	}
	
}