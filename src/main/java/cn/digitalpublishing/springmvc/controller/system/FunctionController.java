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
import cn.digitalpublishing.po.SysFunction;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.system.FunctionForm;

@Controller
@RequestMapping("/pages/function")
public class FunctionController extends BaseController {

	@RequestMapping(value="/form/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, FunctionForm form) throws Exception {
		String forwardString="function/list";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			form.setPageList(this.systemService.getPageList(condition, null));
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
	public FunctionForm manager(HttpServletRequest request, HttpServletResponse response, FunctionForm form) throws Exception {
			
		List<SysFunction> functionList = new ArrayList<SysFunction>();
		try{
			Map<String, Object> condition = form.getCondition();
			form.setiTotalRecords(this.systemService.getFunctionCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if(form.getiTotalRecords()>0){
				functionList = this.systemService.getFunctionPagingList(condition, " order by a.id ", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(functionList);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, FunctionForm form)throws Exception {
		String forwardString = "function/edit";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			form.setPageList(this.systemService.getPageList(condition, null));
			if(!Strings.isNullOrEmpty(request.getParameter("eid"))){
				form.setObj(this.systemService.getFunctionById(request.getParameter("eid")));
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
	public FunctionForm editSubmit(HttpServletRequest request, HttpServletResponse response, FunctionForm form)throws Exception {
		try{
			if(!Strings.isNullOrEmpty(form.getId())){
				//修改
				this.systemService.updateFunction(form.getObj(), form.getId(), null);
				form.setMsg(Lang.getLanguage("Function.Info.Update.Success",request.getSession().getAttribute("lang").toString()));
			}else{
				//新增
				SysFunction function = new SysFunction();
				function.setName(form.getObj().getName());
				function.setCode(form.getObj().getCode());
				function.setPath(form.getObj().getPath());
				function.setPage(form.getObj().getPage());
				this.systemService.insertFunction(function);
				form.setMsg(Lang.getLanguage("Function.Info.Add.Success",request.getSession().getAttribute("lang").toString()));
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
	public FunctionForm delete(HttpServletRequest request, HttpServletResponse response, FunctionForm form) throws Exception {
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("functionId", request.getParameter("id"));
			Integer count = this.systemService.getRoleFunctionRelationshipCountByRelationId(condition);
			if (count > 0) {
				form.setMsg(Lang.getLanguage("Function.Info.Delete.Exist.Role",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(FAILURE);
			} else {
				this.systemService.deleteFunctionById(request.getParameter("id"));
				form.setMsg(Lang.getLanguage("Function.Info.Delete.Success",request.getSession().getAttribute("lang").toString()));
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
	public FunctionForm validateCodeUnique(HttpServletRequest request, HttpServletResponse response, FunctionForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>(); 
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("code", form.getCode());
			condition.put("pageId", form.getPageId());
			if(!Strings.isNullOrEmpty(form.getId())){
				condition.put("uniqueId", form.getId());
			}
			form.setiTotalRecords(this.systemService.getFunctionCount(condition));
			if(form.getiTotalRecords()>0) {
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Function.Info.ValidateCodeUnique.Failure",request.getSession().getAttribute("lang").toString()));
			} else {
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Function.Info.ValidateCodeUnique.Success",request.getSession().getAttribute("lang").toString()));
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
	public FunctionForm validateNameUnique(HttpServletRequest request, HttpServletResponse response, FunctionForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>(); 
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("name", form.getName());
			condition.put("pageId", form.getPageId());
			if(!Strings.isNullOrEmpty(form.getId())){
				condition.put("uniqueId", form.getId());
			}
			form.setiTotalRecords(this.systemService.getFunctionCount(condition));
			if(form.getiTotalRecords()>0) {
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Function.Info.ValidateNameUnique.Failure",request.getSession().getAttribute("lang").toString()));
			} else {
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Function.Info.ValidateNameUnique.Success",request.getSession().getAttribute("lang").toString()));
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return form;
	}
	
}