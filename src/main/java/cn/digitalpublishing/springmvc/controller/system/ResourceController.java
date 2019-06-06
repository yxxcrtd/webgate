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

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.model.Param;
import cn.digitalpublishing.po.SysResource;
import cn.digitalpublishing.po.SysRole;
import cn.digitalpublishing.po.SysSystem;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.system.ResourceForm;
import cn.digitalpublishing.springmvc.form.system.RoleForm;
import cn.digitalpublishing.springmvc.util.TreeNode;
import cn.digitalpublishing.util.SystemConstants;

@Controller
@RequestMapping(value = "/pages/resource")
public class ResourceController extends BaseController {
	
	@RequestMapping(value = "/form/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, ResourceForm form) throws Exception{
		String forwardString = "resource/tree";
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			model.put("form", form);
		} catch (Exception e) {
			e.printStackTrace();
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")): e.getMessage());
			forwardString = "msg";
		}
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value = "/form/manager", headers = "Accept=application/json")
	@ResponseBody
	public ResourceForm manager(HttpServletRequest request, HttpServletResponse response, ResourceForm form) throws Exception {
		Map<String, Object> condition = form.getCondition(); 
		List<SysResource> list = new ArrayList<SysResource>();
		try {
			condition.put("name", form.getName()==null?"":form.getName());
			condition.put("code", form.getCode()==null?"":form.getCode());
			condition.put("parentResource", "1");
			form.setAaData(list);
		} catch (Exception e) {
			e.printStackTrace();
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ?(Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")) : e.getMessage());
			return form;
		}
		return form;
	}
	
	@RequestMapping(value = "/form/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, ResourceForm form) throws Exception{
		String forwardString = "resource/edit";
		Map<String,Object> model = new HashMap<String,Object>();
		String eid = request.getParameter("eid");
		String fatherId = request.getParameter("fatherId");
		String sysId = request.getParameter("sysId");
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("sysId", sysId);
			form.setComponentList(this.systemService.getComponentList(condition, ""));
			form.setSystemList(this.systemService.getSystemList(condition, ""));
			form.setStatusMap(Param.getParam("resource.status",true,request.getSession().getAttribute("lang").toString()));
			form.setTypeMap(Param.getParam("resource.type",true,request.getSession().getAttribute("lang").toString()));
			form.setFullMap(Param.getParam("resource.full",true,request.getSession().getAttribute("lang").toString()));
			if (eid == null){
				// 新增
				SysResource parentResource = new SysResource();
				SysResource obj = new SysResource();
				SysSystem system = new SysSystem();
				system.setId(sysId);
				obj.setSystem(system);
				parentResource.setId(fatherId);
				obj.setParentResource(parentResource);
				form.setObj(obj);
				model.put("form", form);
			} else{
				// 修改
				SysResource r = this.systemService.getResourceById(eid);
				form.setObj(r);
				form.setId(eid);
				if (r.getParentResource() == null) {
					SysResource parentResource = new SysResource();
					parentResource.setId(SystemConstants.TREE_ROOT);
					r.setParentResource(parentResource);
				}
				model.put("form", form);
			}
		} catch (Exception e) {
			e.printStackTrace();
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")) : e.getMessage());
		}
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value = "/form/editSubmit")
	@ResponseBody
    public ResourceForm editSubmit(HttpServletRequest request, HttpServletResponse response, ResourceForm form) throws Exception{
		try {
			SysResource r = form.getObj();
			if(SystemConstants.TREE_ROOT.equals(r.getParentResource().getId())) {
				r.setParentResource(null);
			}
			if(r.getId() == null || "".equals(r.getId())){
				this.systemService.insertResource(r);
				TreeNode node = new TreeNode(r.getId(),r.getName(),false,false,r.getSystem().getId(),null);
				form.setNode(node);
				form.setMsg(Lang.getLanguage("Resource.Info.Add.Success", request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			}else{
				this.systemService.updateResource(r, r.getId(), null);
				TreeNode node = new TreeNode(null,r.getName(),false,false,r.getSystem().getId(),null);
				form.setNode(node);
				form.setMsg(Lang.getLanguage("Resource.Info.Update.Success", request.getSession().getAttribute("lang").toString()));
			}
			form.setIsSuccess(SUCCESS);
			
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")) : e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value = "/form/getMenuZTree")
	public ModelAndView getList(HttpServletRequest request,HttpServletResponse response, ResourceForm form) throws Exception{
		String forwardString = "resource/tree";
		Map<String,Object> model = new HashMap<String,Object>();
		String fatherId = request.getParameter("id");
		String sysId = request.getParameter("sysId");
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
							null,
							"sys"
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
							null,
							"sys"
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
			if(fatherId.equals(sysId)){
				resourceCondition.put("parentResource", SystemConstants.TREE_ROOT);
				resourceList = systemService.getResourceList(resourceCondition, " order by a.order");
			} else {
				resourceCondition.put("parentResource", fatherId);
				resourceList = systemService.getResourceList(resourceCondition, " order by a.order");
			}
			if(resourceList != null && resourceList.size()>0) {
				TreeNode node = null;
				for(SysResource resource : resourceList) {
					resourceCondition.put("parentResource", resource.getId());
					resourceCondition.put("status", SystemConstants.RESOURCE_STATUS_USED);
					resourceCondition.put("sysId", sysId);
					int count = systemService.getResourceCount(resourceCondition);
					if(count!=0){
						node = new TreeNode(
							resource.getId(),
							resource.getName(),
							//("".equals(Lang.getLanguage(resource.getName(),request.getSession().getAttribute("lang").toString()))?resource.getName():Lang.getLanguage(resource.getName(),request.getSession().getAttribute("lang").toString()))+"("+(resource.getStatus()==1?Lang.getLanguage("Global.Prompt.InUse",request.getSession().getAttribute("lang").toString()):Lang.getLanguage("Global.Prompt.Disabled",request.getSession().getAttribute("lang").toString()))+")",
							true,
							false,
							sysId,
							null,
							"menu"
						);
					} else {
						node = new TreeNode(
							resource.getId(),
							resource.getName(),
							//("".equals(Lang.getLanguage(resource.getName(),request.getSession().getAttribute("lang").toString()))?resource.getName():Lang.getLanguage(resource.getName(),request.getSession().getAttribute("lang").toString()))+"("+(resource.getStatus()==1?Lang.getLanguage("Global.Prompt.InUse",request.getSession().getAttribute("lang").toString()):Lang.getLanguage("Global.Prompt.Disabled",request.getSession().getAttribute("lang").toString()))+")",
							false,
							false,
							sysId,
							null,
							"menu"
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
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value = "/form/delete")
	@ResponseBody
	public ResourceForm delete(HttpServletRequest request, HttpServletResponse response, ResourceForm form) throws Exception{
		Map<String,Object> model = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
		    Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("resourceId", request.getParameter("id"));
			Integer roleCount = this.systemService.getRoleResourceRelationshipCountByRelationId(condition);
			Integer pageCount = this.systemService.getPageCount(condition);
			if (roleCount > 0) {
				form.setMsg(Lang.getLanguage("Resource.Info.Delete.Exist.Role",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
				form.setIsSuccess(FAILURE);
			} else if (pageCount > 0) {
				form.setMsg(Lang.getLanguage("Resource.Info.Delete.Exist.Page",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
				form.setIsSuccess(FAILURE);
			} else {
			    this.systemService.deleteResourceById(id);
			    form.setIsSuccess(SUCCESS);
			    form.setMsg(Lang.getLanguage("Resource.Info.Delete.Success", request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")) : e.getMessage());
		}
		model.put("form", form);
		return form;
	}
	
	@RequestMapping(value = "/form/loadRoleData", headers = "Accept=application/json")
	@ResponseBody
	public ResourceForm loadRoleData(HttpServletRequest request, HttpServletResponse response, ResourceForm form) throws Exception {
		String resourceId = request.getParameter("resourceId");
		Map<String, Object> condition = form.getCondition(); 
		try {
			condition.put("resourceId", resourceId);
			SysResource resource = this.systemService.getResourceById(resourceId);
			SysSystem system = resource.getSystem();
			List<SysSystem> systemList = new ArrayList<SysSystem>();
			SysSystem newSystem = new SysSystem();
			newSystem.setId(system.getId());
			newSystem.setName(system.getName());
			systemList.add(newSystem);
			form.setSystemList(systemList);
			form.setStatusMap(Param.getParam("role.status",true,request.getSession().getAttribute("lang").toString()));
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException) ? (Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")) : e.getMessage());
			return form;
		}
		return form;
	}

	@RequestMapping(value="/form/roleManager", headers="Accept=application/json")
	@ResponseBody
	public RoleForm roleManager(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception {
			
		List<SysRole> roleList = new ArrayList<SysRole>();
		try{
			Map<String, Object> condition = form.getCondition();
			form.setiTotalRecords(this.systemService.getRoleCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if(form.getiTotalRecords()>0){
				roleList = this.systemService.getRolePagingList(condition, " order by a.id ", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(roleList);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?(Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")):e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/batchSaveRole")
	@ResponseBody
	public RoleForm batchSaveRole(HttpServletRequest request, HttpServletResponse response, RoleForm form) throws Exception {
		try{
			this.systemService.batchSaveRoleResourceRelationship(form.getAllId(),form.getSela(),form.getId());
			form.setMsg(Lang.getLanguage("Role.Info.Batch.Save.Success",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"));//设置类型成功！
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?(Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")):e.getMessage());
		}
		return form;
	}
	
	//拖动菜单位置
	@RequestMapping(value="/form/positionChange")
	public ResourceForm positionChange(HttpServletRequest request,HttpServletResponse response,ResourceForm form) throws Exception {
		String targetType = request.getParameter("targetType"); 			//目标节点类型
		String targetId = request.getParameter("targetId");				//目标节点ID
		String targetSysId = request.getParameter("targetSysId");			//目标结点系统ID
		String sourceType = request.getParameter("sourceType");				//源节点类型
		String sourceId = request.getParameter("sourceId");				//源节点ID
		String sourceSysId=request.getParameter("sourceSysId");				//源节点系统ID
		String moveType=request.getParameter("moveType");				//移动类型 inner-放在内部 next--放在目标节点的下一个节点上  prev--放在目标节点的上一个节点上 
		try{
			this.systemService.positionChange(targetType, targetId, targetSysId, sourceType, sourceId, sourceSysId, moveType);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?(Lang.getLanguage(((CcsException)e).getPrompt(), request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN")):e.getMessage());
		}
		return form;
	}
	
	/**
	 * 向客户程序提供导航信息
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/form/getNavigation")
	public ResourceForm getNavigation(HttpServletRequest request,HttpServletResponse response,ResourceForm form) throws Exception {
		String result="";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			String menuId=request.getParameter("menuId")!=null&&request.getParameter("menuId").toString().trim().length()>0?request.getParameter("menuId").toString():null;
			if(menuId!=null){
				result=this.systemService.getTopIndex("", menuId,request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN");
				form.setMsg(result);
				form.setIsSuccess(SUCCESS);
			}else{
				form.setIsSuccess(FAILURE);
				result=Lang.getLanguage("Resource.Navigation.Not.Exist",request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN");
				form.setMsg(result);
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			result=(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang")!=null?request.getSession().getAttribute("lang").toString():"zh_CN"):e.getMessage();
			form.setMsg(result);
		}
		model.put("result",result);
		return form;
	}

}
