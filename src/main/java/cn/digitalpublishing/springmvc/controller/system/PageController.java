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
import cn.digitalpublishing.po.SysPage;
import cn.digitalpublishing.po.SysResource;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.system.PageForm;

@Controller
@RequestMapping("/pages/page")
public class PageController extends BaseController {
	
	@RequestMapping(value="/form/manager", headers="Accept=application/json")
	@ResponseBody
	public PageForm manager(HttpServletRequest request, HttpServletResponse response, PageForm form) throws Exception {
			
		List<SysPage> pageList = new ArrayList<SysPage>();
		try{
			Map<String, Object> condition = form.getCondition();
			form.setiTotalRecords(this.systemService.getPageCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if(form.getiTotalRecords()>0){
				pageList = this.systemService.getPagePagingList(condition, " order by a.id ", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(pageList);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, PageForm form)throws Exception {
		String forwardString = "resource/page_edit";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			form.setResourceList(this.systemService.getResourceList(condition, null));
			if(!Strings.isNullOrEmpty(request.getParameter("eid"))){
				form.setObj(this.systemService.getPageById(request.getParameter("eid")));
				form.setId(request.getParameter("eid"));
			} else {
				SysPage page = new SysPage();
				SysResource resource = new SysResource();
				resource.setId(request.getParameter("resourceId"));
				page.setResource(resource);
				form.setObj(page);
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
	public PageForm editSubmit(HttpServletRequest request, HttpServletResponse response, PageForm form)throws Exception {
		try{
			if(!Strings.isNullOrEmpty(form.getId())){
				//修改
				this.systemService.updatePage(form.getObj(), form.getId(), null);
				form.setMsg(Lang.getLanguage("Page.Info.Update.Success",request.getSession().getAttribute("lang").toString()));
			}else{
				//新增
				SysPage page = new SysPage();
				page.setName(form.getObj().getName());
				page.setCode(form.getObj().getCode());
				page.setPath(form.getObj().getPath());
				page.setResource(form.getObj().getResource());
				this.systemService.insertPage(page);
				form.setMsg(Lang.getLanguage("Page.Info.Add.Success",request.getSession().getAttribute("lang").toString()));
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
	public PageForm delete(HttpServletRequest request, HttpServletResponse response, PageForm form) throws Exception {
		try{
			
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("pageId", request.getParameter("id"));
			Integer functionCount = this.systemService.getFunctionCount(condition);
			if (functionCount > 0) {
				form.setMsg(Lang.getLanguage("Page.Info.Delete.Exist.Function",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(FAILURE);
			} else {
				this.systemService.deletePageById(request.getParameter("id"));
				form.setMsg(Lang.getLanguage("Page.Info.Delete.Success",request.getSession().getAttribute("lang").toString()));
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
	public PageForm validateCodeUnique(HttpServletRequest request, HttpServletResponse response, PageForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>(); 
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("code", form.getCode());
			condition.put("resourceId", form.getResourceId());
			if(!Strings.isNullOrEmpty(form.getId())){
				condition.put("uniqueId", form.getId());
			}
			form.setiTotalRecords(this.systemService.getPageCount(condition));
			if(form.getiTotalRecords()>0) {
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Page.Info.ValidateCodeUnique.Failure",request.getSession().getAttribute("lang").toString()));
			} else {
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Page.Info.ValidateCodeUnique.Success",request.getSession().getAttribute("lang").toString()));
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
	public PageForm validateNameUnique(HttpServletRequest request, HttpServletResponse response, PageForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>(); 
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("name", form.getName());
			condition.put("resourceId", form.getResourceId());
			if(!Strings.isNullOrEmpty(form.getId())){
				condition.put("uniqueId", form.getId());
			}
			form.setiTotalRecords(this.systemService.getPageCount(condition));
			if(form.getiTotalRecords()>0) {
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Page.Info.ValidateNameUnique.Failure",request.getSession().getAttribute("lang").toString()));
			} else {
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Page.Info.ValidateNameUnique.Success",request.getSession().getAttribute("lang").toString()));
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return form;
	}
	
}