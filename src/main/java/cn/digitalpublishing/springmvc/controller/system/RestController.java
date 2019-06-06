
package cn.digitalpublishing.springmvc.controller.system;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.model.ResultObject;
import cn.com.daxtech.framework.util.Log;
import cn.com.daxtech.framework.util.ObjectUtil;
import cn.digitalpublishing.bean.LoginInfo;
import cn.digitalpublishing.bean.SessionIdMap;
import cn.digitalpublishing.po.SysAccount;
import cn.digitalpublishing.po.SysRole;
import cn.digitalpublishing.po.SysRoleFunctionRelationship;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.util.Map2Object;
import cn.digitalpublishing.vo.Resource;

@Controller
@RequestMapping("/pages/rest")
public class RestController extends BaseController {

	public final static String REST_OPERATOR_ADD = "1";

	public final static String REST_OPERATOR_MOD = "2";

	/**
	 * 获取账户信息
	 * 
	 * @param id
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAccount/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultObject<SysAccount> getAccount(@PathVariable String id) throws Exception {

		ResultObject<SysAccount> result = null;
		try {
			if ("0".equals(id)) {
				result = new ResultObject<SysAccount>(2, "Rest.Get.Account.Success");// "获取用户信息成功！");
			} else {
				SysAccount account = this.systemService.getAccountByEmpId(id);
				if (account != null) {
					ObjectUtil<SysAccount> util = new ObjectUtil<SysAccount>();
					account = util.setNull(account, new String[] { Set.class.getName(), List.class.getName() });
					result = new ResultObject<SysAccount>(1, account, "Rest.Get.Account.Success");// "获取用户信息成功！");
				} else {
					result = new ResultObject<SysAccount>(1, "Rest.Get.Account.Success");// "获取用户信息成功！");
				}
			}
		} catch (Exception e) {
			result = new ResultObject<SysAccount>(2, "Rest.Get.Account.Failure");// "获取用户信息失败！");
		}
		return result;
	}

	/**
	 * 操作账户信息
	 * 
	 * @param request
	 * @param account
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public ResultObject<SysAccount> insertAccount(@RequestBody Map<String, Object> params) {

		ResultObject<SysAccount> result = null;
		try {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			SysAccount obj = (SysAccount) Map2Object.convert((LinkedHashMap) params.get("account"), SysAccount.class.getName());
			String operType = params.get("operType").toString();// request.getParameter("operType").toString();
																// //1-insert
																// 2-update
			if (REST_OPERATOR_MOD.equals(operType)) {
                SysAccount account = this.systemService.getAccountById(obj.getId());
                if (!obj.getName().equals(account.getName())) {
                    // 修改之后的用户名与之前的不相同
                    Map<String, Object> condition = new HashMap<String, Object>();
                    condition.put("name", obj.getName());
                    int count = this.systemService.getAccountCount(condition);
                    if (count > 0) {
                        result = new ResultObject<SysAccount>(2, "账户名称已存在！");
                        return result;
                    }
                }
				String pwd = obj.getPass();
				if(pwd!=null&&!"".equals(pwd)){
					if (obj.getEncryption() == 2) {
						Md5PasswordEncoder md5 = new Md5PasswordEncoder();
						pwd = md5.encodePassword(pwd, obj.getName());
					}
				}else{
					pwd = null;
				}
				obj.setPass(pwd);
				this.systemService.updateAccount(obj, obj.getId(), null);
			} else if (REST_OPERATOR_ADD.equals(operType)) {
                Map<String, Object> condition = new HashMap<String, Object>();
                condition.put("name", obj.getName());
                int count = this.systemService.getAccountCount(condition);
                if (count > 0) {
                    result = new ResultObject<SysAccount>(2, "账户名称已存在！");
                    return result;
                }
				String pwd = obj.getPass();
				if (obj.getEncryption() == 2) {
					Md5PasswordEncoder md5 = new Md5PasswordEncoder();
					pwd = md5.encodePassword("123456", obj.getName());
				}
				obj.setPass(pwd);
				this.systemService.insertAccount(obj);
			} else {
				// 删除
			}
			ObjectUtil<SysAccount> util = new ObjectUtil<SysAccount>();
			obj = util.setNull(obj, new String[] { Set.class.getName(), List.class.getName() });
			result = new ResultObject<SysAccount>(1, obj, "Rest.Operation.Account.Success");
		} catch (Exception e) {
			result = new ResultObject<SysAccount>(2, "Rest.Operation.Account.Failure");
		}
		return result;
	}

	/**
	 * 获取权限信息
	 * 
	 * @param id
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPermissions/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResultObject<Resource> getPermissions(@PathVariable String id, @RequestBody Map<String, Object> params) throws Exception {

		ResultObject<Resource> result = null;
		try {
			String sid = id;
			if (sid != null && !"".equals(sid)) {
				LoginInfo loginInfo = SessionIdMap.get(sid);
				@SuppressWarnings({ "unchecked", "rawtypes" })
				Resource obj = (Resource) Map2Object.convert((LinkedHashMap) params.get("resource"), Resource.class.getName());
				if (loginInfo != null) {// 如果登录ID存在，说明已经登录了
					// 根据 ID 资源链接 和 资源参数 ,确定资源是否有访问权限
					// 如果有再加载功能权限

					result = new ResultObject<Resource>(1, obj, "Rest.Operation.Verify.Login.Success");
				} else {
					result = new ResultObject<Resource>(2, "Rest.Operation.Verify.Login.Fail");
				}
			} else {
				result = new ResultObject<Resource>(2, "Rest.Operation.Verify.Login.Fail");
			}
		} catch (Exception e) {
			result = new ResultObject<Resource>(2, ((e instanceof CcsException) ? ((CcsException) e).getPrompt() : e.getMessage()));
		}
		return result;
	}

	/**
	 * 获取登录信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAccountInfo/{id}", method = RequestMethod.GET)
	public void getLoginInfo(@PathVariable String id,Model model)throws Exception {
		Log.printInfo("Get Account info ---> Start");
		ResultObject<Map<String, Object>> result = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String sid = id;
			if (sid != null && !"".equals(sid)) {
				LoginInfo loginInfo = SessionIdMap.get(sid);
				if (loginInfo != null) {
					SysAccount account = loginInfo.getAccount();
					SysRole role = loginInfo.getRole();
					map.put("accountId", account.getId().toString());
					map.put("accountName", account.getName().toString());
					map.put("employeeId", account.getEmployeeId());
					map.put("roleId", role.getId().toString());
					map.put("roleStatus", String.valueOf(role.getStatus()));
					map.put("lang", loginInfo.getLang());
					map.put("success", "true");
					result = new ResultObject<Map<String, Object>>(1, map, "webgate.httpclient.login.success");
				} else {
					result = new ResultObject<Map<String, Object>>(2, "webgate.httpclient.no.login");
				}
			} else {
				result = new ResultObject<Map<String, Object>>(2, "webgate.httpclient.ticket.error");
			}
		} catch (Exception e) {
			result = new ResultObject<Map<String, Object>>(2, (e instanceof CcsException) ? ((CcsException) e).getPrompt() : e.getMessage());
		}
		Log.printInfo("Get login info ---> End");
		model.addAttribute("target",result);
	}

	/**
	 * 获取访问权限信息
	 * @param componentCode
	 * @param url
	 * @param roleId
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPermissions/{componentCode}/{url}/{roleId}", method = RequestMethod.GET)
	public void getPermissions(@PathVariable String componentCode, @PathVariable String url, @PathVariable String roleId,Model model) throws Exception {

		Log.printInfo("Get Permissions info ---> Start");
		ResultObject<String> result = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		try {
			condition.put("componentCode", componentCode);
			condition.put("url", url.replace("_","/"));
			condition.put("roleId", roleId);
			int count = this.systemService.getRoleResourceRelationshipCountByRelationId(condition);
			result = new ResultObject<String>(1,count==1?"y":"n", "webgate.getPermissions.success");
		} catch (Exception e) {
			result = new ResultObject<String>(2, (e instanceof CcsException) ? ((CcsException) e).getPrompt() : e.getMessage());
		}
		Log.printInfo("Get Permissions info ---> End");
		model.addAttribute("target",result);
	}
	
	/**
	 * 获取功能权限信息
	 * @param componentCode
	 * @param url
	 * @param roleId
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getFunctions/{componentCode}/{url}/{roleId}", method = RequestMethod.GET)
	public void getFunctions(@PathVariable String componentCode, @PathVariable String url, @PathVariable String roleId,Model model) throws Exception {

		Log.printInfo("Get Functions info ---> Start");
		ResultObject<Map<String,String>> result = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String,String> map = new HashMap<String,String>();
		try {
			condition.put("componentCode", componentCode);
			condition.put("url", url.replace("_","/"));
			condition.put("roleId", roleId);
			List<SysRoleFunctionRelationship> list = this.systemService.getRoleFunctionRelationshipListByRelationId(condition,"");
			for(SysRoleFunctionRelationship obj : list)
			{
				map.put("/"+obj.getFunction().getPage().getResource().getComponent().getCode()+obj.getFunction().getPage().getPath()+(obj.getFunction().getPage().getSpecial()!=null&&!"".equals(obj.getFunction().getPage().getSpecial())?("#"+obj.getFunction().getPage().getSpecial()):"") +"#"+ obj.getFunction().getCode(),"true");
			}
			result = new ResultObject<Map<String,String>>(1,map, "webgate.getFunctions.success");
		} catch (Exception e) {
			result = new ResultObject<Map<String,String>>(2, (e instanceof CcsException) ? ((CcsException) e).getPrompt() : e.getMessage());
		}
		Log.printInfo("Get Functions info ---> End");
		model.addAttribute("target",result);
	}
	
	/**
	 * 获取所有角色信息
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRoles", method = RequestMethod.GET)
	public void getRoles(Model model)throws Exception {
		Log.printInfo("Get Roles info ---> Start");
		ResultObject<Map<String,String>> result = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String,String> map = new HashMap<String,String>();
		try {
			List<SysRole> list = this.systemService.getRoleList(condition, "");
			for(SysRole obj : list){
				map.put(obj.getId(),obj.getName());
			}
			result = new ResultObject<Map<String,String>>(1,map, "webgate.getRoles.success");
		} catch (Exception e) {
			result = new ResultObject<Map<String,String>>(2, (e instanceof CcsException) ? ((CcsException) e).getPrompt() : e.getMessage());
		}
		Log.printInfo("Get Roles info ---> End");
		model.addAttribute("target",result);
	}

}
