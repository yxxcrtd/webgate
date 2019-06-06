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

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.model.Param;
import cn.digitalpublishing.po.SysModule;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.system.ModuleForm;
import cn.digitalpublishing.util.SystemConstants;

import com.google.common.base.Strings;

@Controller
@RequestMapping("/pages/module")
public class ModuleController extends BaseController {

	@RequestMapping(value="/form/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, ModuleForm form) throws Exception {
		String forwardString="module/list";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			form.setMustMap(Param.getParam("module.must",true,request.getSession().getAttribute("lang").toString()));
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}
	
	//方式一
	//@RequestMapping(value="/form/manager")
	//@ResponseBody
	//public ModuleForm manager(HttpServletRequest request, HttpServletResponse response, @RequestBody ModuleForm form) throws Exception {
	//方式二
	@RequestMapping(value="/form/manager", headers="Accept=application/json")
	@ResponseBody
	public ModuleForm manager(HttpServletRequest request, HttpServletResponse response, ModuleForm form) throws Exception {
			
		List<SysModule> moduleList = new ArrayList<SysModule>();
		try{
			//form.setUrl(request.getRequestURL().toString());
			Map<String, Object> condition = form.getCondition();
			// 查询条件：Config的状态为未删除
			form.setiTotalRecords(this.systemService.getModuleCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if(form.getiTotalRecords()>0){
				moduleList = this.systemService.getModulePagingList(condition, " order by a.id ", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(moduleList);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, ModuleForm form)throws Exception {
		String forwardString = "module/edit";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			form.setMustMap(Param.getParam("module.must",true,request.getSession().getAttribute("lang").toString()));
			form.setI18nMap(Param.getParam("module.i18n",true,request.getSession().getAttribute("lang").toString()));
			Map<String, Object> condition = new HashMap<String, Object>();
			// 查询所有系统状态为“在用”状态的系统列表
			condition.put("status", SystemConstants.COMPONENT_STATUS_USING);
			form.setComponentList(this.systemService.getComponentList(condition, " order a.code "));
			if(!Strings.isNullOrEmpty(request.getParameter("eid"))){
				form.setObj(this.systemService.getModuleById(request.getParameter("eid")));
				form.setId(request.getParameter("eid"));
			}
			form.setIsSuccess(SUCCESS);
			//throw new CcsException("xxxxx");
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value="/form/editSubmit")
	@ResponseBody
	public ModuleForm editSubmit(HttpServletRequest request, HttpServletResponse response, ModuleForm form)throws Exception {
		try{
			if(!Strings.isNullOrEmpty(form.getId())){
				//修改
				String[] properties = null;
				if(form.getObj().getComponent()==null||form.getObj().getComponent().getId()==null||"".equals(form.getObj().getComponent().getId())){
					properties = new String[]{"component"};
				}
				this.systemService.updateModule(form.getObj(), form.getId(), properties);
				form.setMsg(Lang.getLanguage("Module.Info.Update.Success",request.getSession().getAttribute("lang").toString()));
			}else{
				//新增
				SysModule module = new SysModule();
				module.setName(form.getObj().getName());
				module.setLink(form.getObj().getLink());
				module.setMore(form.getObj().getMore());
				//module.setIcon(form.getObj().getIcon());
				module.setHeight(form.getObj().getHeight());
				module.setWidth(form.getObj().getWidth());
				module.setMust(form.getObj().getMust());
				module.setI18n(form.getObj().getI18n());
				if(form.getObj().getComponent()!=null&&form.getObj().getComponent().getId()!=null&&!"".equals(form.getObj().getComponent().getId())){
					module.setComponent(form.getObj().getComponent());
				}
				this.systemService.insertModule(module);
				form.setMsg(Lang.getLanguage("Module.Info.Add.Success",request.getSession().getAttribute("lang").toString()));
			}
			form.setIsSuccess(SUCCESS);
			//throw new NullPointerException("空指针异常");
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
		//return this.index(request, response, form);
	}
	
	/*@RequestMapping(value="/form/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, ModuleForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>(); 
		try{
			this.systemService.deleteModuleById(request.getParameter("id"));
			form.setMsg(Lang.getLanguage("Module.Info.Delete.Success",request.getSession().getAttribute("lang").toString()));
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return this.index(request, response, form);
	}*/
	
	@RequestMapping(value="/form/delete")
	@ResponseBody
	public ModuleForm delete(HttpServletRequest request, HttpServletResponse response, ModuleForm form) throws Exception {
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("moduleId", request.getParameter("id"));
			Integer count = this.systemService.getAccountModuleRelationshipCountByRelationId(condition);
			if (count > 0) {
				form.setMsg(Lang.getLanguage("Module.Info.Delete.Exist.Account",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(FAILURE);
			} else {
				this.systemService.deleteModuleById(request.getParameter("id"));
				form.setMsg(Lang.getLanguage("Module.Info.Delete.Success",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess(SUCCESS);
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/validateNameUnique")
	@ResponseBody
	public ModuleForm validateNameUnique(HttpServletRequest request, HttpServletResponse response, ModuleForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>(); 
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("name", form.getName());
			if(!Strings.isNullOrEmpty(form.getId())){
				condition.put("uniqueId", form.getId());
			}
			// 查询条件：Config的状态为未删除
			form.setiTotalRecords(this.systemService.getModuleCount(condition));
			if(form.getiTotalRecords()>0) {
				form.setIsSuccess(FAILURE);
				form.setMsg(Lang.getLanguage("Module.Info.ValidateNameUnique.Failure",request.getSession().getAttribute("lang").toString()));
			} else {
				form.setIsSuccess(SUCCESS);
				form.setMsg(Lang.getLanguage("Module.Info.ValidateNameUnique.Success",request.getSession().getAttribute("lang").toString()));
			}
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		model.put("form", form);
		return form;
	}
	@RequestMapping(value="/form/indexView")
	public ModelAndView indexView(HttpServletRequest request, HttpServletResponse response, ModuleForm form) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		String forwardString = "module/indexView";
		try {
			model.put("form", form);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return new ModelAndView(forwardString,model);
	}
	@RequestMapping(value="/form/getModule")
	@ResponseBody
	public ModuleForm getModule(HttpServletRequest request, HttpServletResponse response, ModuleForm form) throws Exception {
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			List<SysModule> sysModule = this.systemService.getModuleList(condition, "");
			form.setSysModules(sysModule);
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}
	
	@RequestMapping(value="/form/test", headers="Accept=application/json")
	@ResponseBody
	public ModuleForm test(HttpServletRequest request, HttpServletResponse response, ModuleForm form) throws Exception {
			
		try{
			Map<String,Object> condition = new HashMap<String,Object>();
			List<SysModule> sysModule = this.systemService.getModuleList(condition, "");
			String[] head = new String[]{"模块名称","模块链接","更多链接"};
			Map<String,List<String>> map = new HashMap<String,List<String>>();
			for(SysModule sys : sysModule)
			{
				List<String> list = new ArrayList<String>();
				list.add(sys.getName());
				list.add(sys.getLink());
				list.add(sys.getMore());
				map.put(sys.getName(), list);
			}
			form.setTableHead(head);
			form.setMap(map);
			form.setIsSuccess(SUCCESS);
		}catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
		}
		return form;
	}

    @RequestMapping(value="/form/modiModule")
    public ModelAndView modiModule(HttpServletRequest request, HttpServletResponse response, ModuleForm form) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        String forwardString = "module/modiModule";
        try {
            String[] str = request.getParameterValues("hideId");
            /*
            Map<String,Object> condition = new HashMap<String,Object>();
            List<SysModule> sysModule = this.systemService.getModuleList(condition, "");
            if(str != null && str.length > 0)
            {
                for(SysModule sys : sysModule)
                {
                    for(String s :str)
                    {
                        if(s.equals(sys.getId()))
                        {
                            sys.setHideShow("trueShow");
                        }
                    }
                }
            }
            form.setSysModules(sysModule);
            */
            StringBuffer sb = new StringBuffer();
            if (str != null && !"".equals(str)) {
                for (int i = 0; i < str.length; i++) {
                    sb.append(str[i]);
                    if (i < str.length - 1) {
                        sb.append(",");
                    }
                }
            }
            form.setHideIds(sb.toString());
            form.setIsSuccess(SUCCESS);
            model.put("form", form);
        } catch (Exception e) {
            form.setIsSuccess(FAILURE);
            form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
        }
        return new ModelAndView(forwardString,model);
    }

    @RequestMapping(value="/form/modiModuleManager", headers="Accept=application/json")
    @ResponseBody
    public ModuleForm modiModuleManager(HttpServletRequest request, HttpServletResponse response, ModuleForm form) throws Exception {
        List<SysModule> moduleList = new ArrayList<SysModule>();
        try{
            //form.setUrl(request.getRequestURL().toString());
            Map<String, Object> condition = form.getCondition();
            // 查询条件：Config的状态为未删除
            form.setiTotalRecords(this.systemService.getModuleCount(condition));
            form.setiTotalDisplayRecords(form.getiTotalRecords());
            if(form.getiTotalRecords()>0){
                moduleList = this.systemService.getModulePagingList(condition, " order by a.id ", form.getiDisplayLength(), form.getiDisplayStart());
            }
            List<SysModule> newModuleList = new ArrayList<SysModule>();
            Map<String,Object> moduleCondition = new HashMap<String,Object>();

            String hideIds = form.getHideIds();
            if(hideIds != null && !"".equals(hideIds)) {
                String[] hideIdsArray = hideIds.split(",");
                for(SysModule module : moduleList) {
                    for(int i = 0; i < hideIdsArray.length; i++) {
                        if(hideIdsArray[i].equals(module.getId())) {
                            module.setHideShow("trueShow");
                        }
                    }
                    newModuleList.add(module);
                }
                form.setAaData(newModuleList);
            } else {
                for(SysModule module : moduleList) {
                    module.setHideShow("");
                    newModuleList.add(module);
                }
                form.setAaData(newModuleList);
            }
            form.setIsSuccess(SUCCESS);
        }catch(Exception e){
            form.setIsSuccess(FAILURE);
            form.setMsg((e instanceof CcsException)?((CcsException)e).getMessage():e.getMessage());
        }
        return form;
    }
}