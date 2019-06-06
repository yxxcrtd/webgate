package cn.digitalpublishing.springmvc.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.model.Param;
import cn.digitalpublishing.po.SysFunction;
import cn.digitalpublishing.po.SysResource;
import cn.digitalpublishing.po.SysRole;
import cn.digitalpublishing.po.SysSystem;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.system.FunctionForm;
import cn.digitalpublishing.springmvc.form.system.ResourceForm;
import cn.digitalpublishing.springmvc.form.system.RoleForm;
import cn.digitalpublishing.springmvc.util.TreeNode;
import cn.digitalpublishing.util.SystemConstants;


@Controller
@RequestMapping("/pages/role")

public class RoleController extends BaseController {
	
	@RequestMapping(value="/form/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception {
		String forwardString = "role/list";
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			form.setSystemList(this.systemService.getSystemList(condition, null));
			form.setStatusMap(Param.getParam("role.status",true,request.getSession().getAttribute("lang").toString()));
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	}
	
	@RequestMapping(value="/form/manager", headers="Accept=application/json")
	@ResponseBody
	public RoleForm manager(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception{
		List<SysRole> roleList = new ArrayList<SysRole>();
		try {
			Map<String,Object> condition = form.getCondition();
			form.setiTotalRecords(this.systemService.getRoleCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if(form.getiTotalRecords()>0){
				roleList = this.systemService.getRolePagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(roleList);
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception{
		String forwardString = "role/edit";
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			form.setSystemList(this.systemService.getSystemList(condition, null));
			form.setStatusMap(Param.getParam("role.status",true,request.getSession().getAttribute("lang").toString()));
			if(!Strings.isNullOrEmpty(request.getParameter("eid"))){
				form.setObj(this.systemService.getRoleById(request.getParameter("eid")));
				form.setId(request.getParameter("eid"));
			}
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value="/form/editSubmit")
	@ResponseBody
	public RoleForm editSubmit(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception{
		try {
			if(!Strings.isNullOrEmpty(form.getId())){
				this.systemService.updateRole(form.getObj(), form.getId(), null);
				form.setMsg(Lang.getLanguage("Role.Info.Update.Success", request.getSession().getAttribute("lang").toString()));
			}else{
				SysRole role = new SysRole();
				role.setName(form.getObj().getName());
				role.setSystem(form.getObj().getSystem());
				role.setStatus(form.getObj().getStatus());
				this.systemService.insertRole(role);
				form.setMsg(Lang.getLanguage("Role.Info.Add.Success", request.getSession().getAttribute("lang").toString()));
			}
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
		
	}
	
	@RequestMapping(value="/form/delete")
	@ResponseBody
	public RoleForm delete(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception{
		try {
			Map<String, Object> resourceCondition = new HashMap<String, Object>();
			resourceCondition.put("roleId", request.getParameter("id"));
			Map<String, Object> accountCondition = new HashMap<String, Object>();
			accountCondition.put("roleId", request.getParameter("id"));
			Map<String, Object> functionCondition = new HashMap<String, Object>();
			functionCondition.put("roleId", request.getParameter("id"));
			Integer resourceCount = this.systemService.getRoleResourceRelationshipCountByRelationId(resourceCondition);
			Integer accountCount = this.systemService.getRoleAccountRelationshipCountByRelationId(accountCondition);
			Integer functionCount = this.systemService.getRoleFunctionRelationshipCountByRelationId(functionCondition);
			if (resourceCount > 0) {
				form.setMsg(Lang.getLanguage("Role.Info.Delete.Exist.Resource",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(FAILURE);
			} else if (accountCount > 0) {
				form.setMsg(Lang.getLanguage("Role.Info.Delete.Exist.Account",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(FAILURE);
			} else if (functionCount > 0) {
				form.setMsg(Lang.getLanguage("Role.Info.Delete.Exist.Function",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(FAILURE);
			} else {
				this.systemService.deleteRoleById(request.getParameter("id"));
				form.setMsg(Lang.getLanguage("Role.Info.Delete.Success",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(SUCCESS);
			}
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/validateNameUnique")
	@ResponseBody
	public RoleForm validateNameUnique(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("name",form.getName());
			form.setiTotalRecords(this.systemService.getRoleCount(condition));
			if(form.getiTotalRecords()>0){
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Role.Info.ValidateNameUnique.Failure", request.getSession().getAttribute("lang").toString()));
			}else{
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Role.Info.ValidateNameUnique.Success", request.getSession().getAttribute("lang").toString()));
			}
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return form;
	}
	
	@RequestMapping(value="/form/functionIndex")
	public ModelAndView functionIndex(HttpServletRequest request, HttpServletResponse response, FunctionForm form) throws Exception {
		String forwardString="role/function_list";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			Map<String,Object> condition = new HashMap<String,Object>();
			form.setPageList(this.systemService.getPageList(condition, null));
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}

	@RequestMapping(value="/form/functionManager", headers="Accept=application/json")
	@ResponseBody
	public FunctionForm functionManager(HttpServletRequest request, HttpServletResponse response, FunctionForm form) throws Exception {
			
		List<SysFunction> roleList = new ArrayList<SysFunction>();
		String roleId = request.getParameter("roleId");
		try{
			Map<String, Object> condition = form.getCondition();
			condition.put("roleId", roleId);
			form.setiTotalRecords(this.systemService.getFunctionCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if(form.getiTotalRecords()>0){
				roleList = this.systemService.getFunctionPagingList(condition, " order by a.id ", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(roleList);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	 
	@RequestMapping(value="/form/batchSaveFunction")
	@ResponseBody
	public FunctionForm batchSaveFunction(HttpServletRequest request, HttpServletResponse response, FunctionForm form) throws Exception {
		try{
			this.systemService.batchSaveRoleFunctionRelationship(form.getAllId(),form.getSela(),form.getId());
			form.setMsg(Lang.getLanguage("RoleFunction.Info.Save.Success",request.getSession().getAttribute("lang").toString()));
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value = "/form/resourceTreeIndex")
	public ModelAndView resourceTreeIndex(HttpServletRequest request, HttpServletResponse response, ResourceForm form) throws Exception{
		String forwardString = "role/resource_tree";
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			model.put("form", form);
		} catch (Exception e) {
			e.printStackTrace();
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			forwardString = "msg";
		}
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value = "/form/getResourceZTree")
	public void getResourceZTree(HttpServletRequest request,HttpServletResponse response, ResourceForm form) throws Exception{
		String fatherId = request.getParameter("id");
		String sysId = request.getParameter("sysId");
		String roleId = request.getParameter("roleId");
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		
		if(fatherId.equals("root")) {
			// 打开的是根节点，获取系统集合列表
			Map<String,Object> systemCondition = new HashMap<String,Object>();
			systemCondition.put("status", SystemConstants.SYSTEM_STATUS_USING);
			List<SysSystem> sysList = systemService.getSystemList(systemCondition, null);
			if(sysList!=null && sysList.size()>0) {
				TreeNode node = null;
				for(SysSystem system : sysList){
					Map<String,Object> resourceCondition = new HashMap<String,Object>();
					resourceCondition.put("sysId", system.getId());
					resourceCondition.put("roleId", roleId);
					resourceCondition.put("status", SystemConstants.RESOURCE_STATUS_USED);
					int count = systemService.getResourceCount(resourceCondition);
					if(count != 0) {
						// 存在可用的子资源
						node = new TreeNode(
							system.getId(),
							//system.getName()+"("+(system.getStatus()==1?Lang.getLanguage("Global.Prompt.InUse",request.getSession().getAttribute("lang").toString()):Lang.getLanguage("Global.Prompt.Disabled",request.getSession().getAttribute("lang").toString()))+")",
							system.getName(),
							true,
							false,
							system.getId(),
							roleId
						);
					} else {
						// 不存在可用的子资源
						node = new TreeNode(
							system.getId(),
							//system.getName()+"("+(system.getStatus()==1?Lang.getLanguage("Global.Prompt.InUse",request.getSession().getAttribute("lang").toString()):Lang.getLanguage("Global.Prompt.Disabled",request.getSession().getAttribute("lang").toString()))+")",
							system.getName(),
							false,
							false,
							system.getId(),
							roleId
						);
					}
					nodes.add(node);
				}
			}
		} else {
			// 打开的不是根节点，获取资源列表
			List<SysResource> resourceList = null;
			Map<String,Object> resourceCondition = new HashMap<String,Object>();
			resourceCondition.put("sysId", sysId);
			resourceCondition.put("roleId", roleId);
			if(fatherId.equals(sysId)){
				resourceCondition.put("parentResource", SystemConstants.TREE_ROOT);
				resourceList = systemService.getResourceList(resourceCondition, " order by a.code");
			} else {
				resourceCondition.put("parentResource", fatherId);
				resourceList = systemService.getResourceList(resourceCondition, " order by a.code");
			}
			if(resourceList != null && resourceList.size()>0) {
				TreeNode node = null;
				for(SysResource resource : resourceList) {
					resourceCondition.put("parentResource", resource.getId());
					resourceCondition.put("status", SystemConstants.RESOURCE_STATUS_USED);
					resourceCondition.put("sysId", sysId);
					int count = systemService.getResourceCount(resourceCondition);
					boolean checked = (resource.getChooseType() > 0) ? true : false;
					if(count!=0){
						node = new TreeNode(
							resource.getId(),
							resource.getName(),
							//("".equals(Lang.getLanguage(resource.getName(),request.getSession().getAttribute("lang").toString()))?resource.getName():Lang.getLanguage(resource.getName(),request.getSession().getAttribute("lang").toString()))+"("+(resource.getStatus()==1?Lang.getLanguage("Global.Prompt.InUse",request.getSession().getAttribute("lang").toString()):Lang.getLanguage("Global.Prompt.Disabled",request.getSession().getAttribute("lang").toString()))+")",
							true,
							checked,
							sysId,
							roleId
						);
					} else {
						node = new TreeNode(
							resource.getId(),
							resource.getName(),
							//("".equals(Lang.getLanguage(resource.getName(),request.getSession().getAttribute("lang").toString()))?resource.getName():Lang.getLanguage(resource.getName(),request.getSession().getAttribute("lang").toString()))+"("+(resource.getStatus()==1?Lang.getLanguage("Global.Prompt.InUse",request.getSession().getAttribute("lang").toString()):Lang.getLanguage("Global.Prompt.Disabled",request.getSession().getAttribute("lang").toString()))+")",
							false,
							checked,
							sysId,
							roleId
						);
					}
					nodes.add(node);
				}
			}
		}
		JSONArray nodesString = JSONArray.fromObject(nodes);
		response.setContentType("text/Xml;charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.print(nodesString.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	 
	@RequestMapping(value="/form/batchSaveResource")
	@ResponseBody
	public ResourceForm batchSaveResource(HttpServletRequest request, HttpServletResponse response, ResourceForm form) throws Exception {
		try{
			this.systemService.batchSaveRoleResourceRelationship(form.getSela(),form.getId());
			form.setMsg(Lang.getLanguage("RoleResource.Info.Save.Success",request.getSession().getAttribute("lang").toString()));
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
}
