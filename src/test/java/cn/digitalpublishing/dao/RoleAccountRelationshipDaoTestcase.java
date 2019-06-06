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
import cn.digitalpublishing.po.SysRoleAccountRelationship;
import cn.digitalpublishing.util.PathUtil;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "file:src/test/reroleAccountRelationships/appContext*.xml" })
//@ContextConfiguration({ "file:src/main/java/cn/digitalpublishing/reroleAccountRelationship/appContext*.xml" })
@ContextConfiguration(locations={"classpath:appContext*.xml"})
public class RoleAccountRelationshipDaoTestcase extends AbstractJUnit4SpringContextTests {
	
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
		String sort = null;
		Map<String,Object> condition = new HashMap<String,Object>();
		
		//没有condition
		
		//And条件（accountId = ''）
		condition.put("accountId", "111");
		//condition.put("name", null);
		//condition.put("name", "");
		//condition.put("must", 3);
		
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleAccountRelationshipDao").get("getRoleAccountRelationshipListByAccountId");
		List<SysRoleAccountRelationship> roleAccountRelationshipList = daoFacade.getRoleAccountRelationshipDao().getRoleAccountRelationshipListByAccountId(condition, hqlBean);
		System.out.println("count:" + roleAccountRelationshipList.size());
		for(int i = 0; i < roleAccountRelationshipList.size(); i ++) {
			SysRoleAccountRelationship roleAccountRelationship = roleAccountRelationshipList.get(i);
			System.out.print("【i】:" + i);
			System.out.print("【角色和账户关系ID】:" + roleAccountRelationship.getId());
			System.out.print("【角色名称】:" + roleAccountRelationship.getRole().getName());
			System.out.print("【角色状态】:" + roleAccountRelationship.getRole().getStatus());
			System.out.print("【角色描述】:" + roleAccountRelationship.getRole().getDesc());
			System.out.print("【账户名称】:" + roleAccountRelationship.getAccount().getName());
			System.out.print("【账户密码】:" + roleAccountRelationship.getAccount().getPass());
			System.out.print("【账户状态】:" + roleAccountRelationship.getAccount().getStatus());
			System.out.print("【账户类型】:" + roleAccountRelationship.getAccount().getType());
			System.out.print("【账户级别】:" + roleAccountRelationship.getAccount().getLevel());
			System.out.println();
		}
		System.out.println("---------------------------------------------------------");
	}
	
	// 获取列表
	//@Test
	public void testDelByWhere() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing delByWhere...");
		Map<String,Object> condition = new HashMap<String,Object>();
		
		//没有condition
		
		//And条件（accountId = ''）
		condition.put("accountId", "111");
		//condition.put("name", null);
		//condition.put("name", "");
		//condition.put("must", 3);
		
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.RoleAccountRelationshipDao").get("delByWhere");
		daoFacade.getRoleAccountRelationshipDao().delByWhere(condition, hqlBean);
		System.out.println("---------------------------------------------------------");
	}
	
	// 插入
	//@Test
	public void testInsert() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing insert...");
		System.out.println("---------------------------------------------------------");
	}
	
	// 更新
	//@Test
	public void testUpdate() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing update...");
		System.out.println("---------------------------------------------------------");
	}
	
	// 获取
	//@Test
	public void testGet() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing get...");
		System.out.println("---------------------------------------------------------");
	}
	
	// 删除
	//@Test
	public void testDelete() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing delete...");
		System.out.println("---------------------------------------------------------");
	}
	
}