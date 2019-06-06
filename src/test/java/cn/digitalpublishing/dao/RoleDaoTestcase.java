package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.com.daxtech.framework.util.hql.XmlToHqlBeanBuilder;
import cn.digitalpublishing.facade.DaoFacade;
import cn.digitalpublishing.po.SysRole;
import cn.digitalpublishing.util.PathUtil;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "file:src/test/reroles/appContext*.xml" })
//@ContextConfiguration({ "file:src/main/java/cn/digitalpublishing/rerole/appContext*.xml" })
@ContextConfiguration(locations={"classpath:appContext*.xml"})
public class RoleDaoTestcase extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private DaoFacade daoFacade;
	
	@BeforeClass
	public static void init() {
		System.out.println("Initializing...");
		try {
			initXml();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void release() {
		System.out.println("Releasing...");
		//ProxoolFacade.shutdown(0);
	}
	
	public static void initXml() throws Exception {
		String prefix = "C:\\apache-tomcat-7.0.39\\webapps\\webgate\\";
		String dirpath = "/WEB-INF/classes/hql";
		String absolutePath = prefix + dirpath;
		String suffix = ".xml";
		try {
			List<String> fileList = new ArrayList<String>();
			PathUtil.getFiles(fileList,absolutePath,suffix);
			XmlToHqlBeanBuilder builder = XmlToHqlBeanBuilder.getInstance();
			builder.build(fileList);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex);
		}
	}
	
	// 获取列表
	@Test
	public void testGetList() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing getList...");
		//fileCode		FILE_CODE		出版物编号
		//title			CONTENT_TITLE	出版物标题
		//author		CONTENT_AUTHOR	作者
		//status		CONTENT_STATUS	出版物状态 1-未审查，2-未通过，3-已通过
		//type			CONTENT_TYPE	出版物类型 1-电子书，2-期刊
		//checkType		CHECK_TYPE		内容审查类型 1-本地，2-远程
		String sort = null;
		//List<SysRole> roleList = daoFacade.getSysRoleDao().getList(condition, sort);
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", 1);
		//condition.put("accountId", "111");
		//condition.put("accountId", null);
		//condition.put("type", 1);
		//condition.put("level", 1);
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleDao").get("getList");
		List<SysRole> roleList = daoFacade.getRoleDao().getList(condition, sort, hqlBean);
		System.out.println("count:" + roleList.size());
		for(int i = 0; i < roleList.size(); i ++) {
			SysRole role = roleList.get(i);
			System.out.print("【i】:" + i);
			System.out.print("【角色ID】:" + role.getId());
			System.out.print("【角色名称】:" + role.getName());
			System.out.print("【角色状态】:" + role.getStatus());
			System.out.println();
		}
		System.out.println("---------------------------------------------------------");
	}

	// 获取分页列表
	@Test
	public void testGetPagingList() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing getPagingList...");
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("nameLike", "be");
		//condition.put("nameNotEqual", "liuben");
		//condition.put("name", "liuben");
		condition.put("status", 1);
		condition.put("type", 1);
		condition.put("level", 1);
		condition.put("accountId", "111");
		//condition.put("accountId", null);
		String sort = null;
		Integer pageCount = 10;
		Integer page = 0;		//当前页是第一页
		//Integer page = 1;		//当前页是第二页
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleDao").get("getPagingList");
		List<SysRole> pagingList = daoFacade.getRoleDao().getPagingList(condition, sort, pageCount, page, hqlBean);
		System.out.println("count:" + pagingList.size());
		for(int i = 0; i < pagingList.size(); i ++) {
			SysRole role = pagingList.get(i);
			System.out.print("【i】:" + i);
			System.out.print("【角色ID】:" + role.getId());
			System.out.print("【角色名称】:" + role.getName());
			System.out.print("【角色状态】:" + role.getStatus());
			System.out.println();
		}
		System.out.println("---------------------------------------------------------");
	}
	
	// 获取总数
	//@Test
	public void testGetCount() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing getCount...");
		Map<String,Object> condition = new HashMap<String,Object>();
		//condition.put("nameLike", "be");
		//condition.put("nameNotEqual", "liuben");
		//condition.put("name", "liuben");
		condition.put("status", 1);
		condition.put("type", 1);
		condition.put("level", 1);
		//Integer count = daoFacade.getRoleDao().getCount(condition);
		//System.out.println("Count:" + count);
		System.out.println("---------------------------------------------------------");
	}
	
	// 插入
	//@Test
	public void testInsert() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing insert...");
		
		SysRole role = new SysRole();
		role.setName("其他角色");
		
		daoFacade.getRoleDao().insert(role);
		System.out.println("---------------------------------------------------------");
	}
	
	// 更新
	//@Test
	public void testUpdate() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing update...");
		
		String id = "402881e53e99808f013e998093f70000";
		SysRole role = (SysRole) daoFacade.getRoleDao().get(SysRole.class.getName(), id);

		role.setName("非法角色");
		
		daoFacade.getRoleDao().update(role, SysRole.class.getName(), id, null);
		System.out.println("---------------------------------------------------------");
	}
	
	// 获取
	//@Test
	public void testGet() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing get...");
		String id = "402881e53e99808f013e998093f70000";
		SysRole role = (SysRole) daoFacade.getRoleDao().get(SysRole.class.getName(), id);
		System.out.print("【角色ID】:" + role.getId());
		System.out.print("【角色名称】:" + role.getName());
		System.out.print("【角色状态】:" + role.getStatus());
		System.out.println();
		System.out.println("---------------------------------------------------------");
	}
	
	// 删除
	//@Test
	public void testDelete() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing delete...");
		String id = "402881e53e99808f013e998093f70000";
		daoFacade.getRoleDao().delete(SysRole.class.getName(), id);
		System.out.println("---------------------------------------------------------");
	}
	
}