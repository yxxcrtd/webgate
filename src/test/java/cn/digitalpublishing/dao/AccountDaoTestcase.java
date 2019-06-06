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
import cn.digitalpublishing.po.SysAccount;
import cn.digitalpublishing.util.PathUtil;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "file:src/test/reaccounts/appContext*.xml" })
//@ContextConfiguration({ "file:src/main/java/cn/digitalpublishing/reaccount/appContext*.xml" })
@ContextConfiguration(locations={"classpath:appContext*.xml"})
public class AccountDaoTestcase extends AbstractJUnit4SpringContextTests {
	
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
		String prefix = "C:\\apache-tomcat-6.0.36\\webapps\\webgate\\";
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
		
		//And条件（name = '' and must = ''）
		condition.put("name", "ddd");
		//condition.put("name", null);
		//condition.put("name", "");
		//condition.put("must", 3);
		
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AccountDao").get("getList");
		List<SysAccount> accountList = daoFacade.getAccountDao().getList(condition, sort, hqlBean);
		System.out.println("count:" + accountList.size());
		for(int i = 0; i < accountList.size(); i ++) {
			SysAccount account = accountList.get(i);
			System.out.print("【i】:" + i);
			System.out.print("【账户ID】:" + account.getId());
			System.out.print("【账户名称】:" + account.getName());
			System.out.print("【账户名称】:" + account.getName());
			System.out.print("【账户密码】:" + account.getPass());
			System.out.print("【账户状态】:" + account.getStatus());
			System.out.print("【账户类型】:" + account.getType());
			System.out.print("【账户级别】:" + account.getLevel());
			System.out.println();
		}
		System.out.println("---------------------------------------------------------");
	}

	// 获取分页列表
	@Test
	public void testGetPagingList() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing getPagingList...");
		String sort = null;
		Map<String,Object> condition = new HashMap<String,Object>();
		int pageCount = 10;
		int countStart = 0;
		
		//没有condition
		
		//And条件（name = '' and must = ''）
		//condition.put("name", "ddd");
		//condition.put("name", null);
		//condition.put("name", "");
		//condition.put("must", 3);
		
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AccountDao").get("getPagingList");
		List<SysAccount> accountList = daoFacade.getAccountDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		System.out.println("count:" + accountList.size());
		for(int i = 0; i < accountList.size(); i ++) {
			SysAccount account = accountList.get(i);
			System.out.print("【i】:" + i);
			System.out.print("【账户ID】:" + account.getId());
			System.out.print("【账户名称】:" + account.getName());
			System.out.print("【账户密码】:" + account.getPass());
			System.out.print("【账户状态】:" + account.getStatus());
			System.out.print("【账户类型】:" + account.getType());
			System.out.print("【账户级别】:" + account.getLevel());
			System.out.println();
		}
		System.out.println("---------------------------------------------------------");
	}
	
	// 获取总数
	@Test
	public void testGetCount() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing getCount...");
		Map<String,Object> condition = new HashMap<String,Object>();
		
		//没有condition
		
		//And条件（name = '' and must = ''）
		//condition.put("name", "ddd");
		//condition.put("name", null);
		//condition.put("name", "");
		//condition.put("must", 3);
		
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AccountDao").get("getCount");
		Integer count = daoFacade.getAccountDao().getCount(condition, hqlBean);
		System.out.println("Count:" + count);
		System.out.println("---------------------------------------------------------");
	}
	
	// 根据name和pass获取
	@Test
	public void testGetByUidAndPwd() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing getByUidAndPwd...");
		String sort = null;
		Map<String,Object> condition = new HashMap<String,Object>();
		
		//没有condition
		
		//And条件（username = '' and password = ''）
		condition.put("username", "liuben");
		//condition.put("password", "123456");
		condition.put("password", "liuben");
		
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AccountDao").get("getByUidAndPwd");
		SysAccount account = daoFacade.getAccountDao().getByUidAndPwd(condition, hqlBean);
		if (account != null) {
			System.out.print("【账户ID】:" + account.getId());
			System.out.print("【账户名称】:" + account.getName());
			System.out.print("【账户名称】:" + account.getName());
			System.out.print("【账户密码】:" + account.getPass());
			System.out.print("【账户状态】:" + account.getStatus());
			System.out.print("【账户类型】:" + account.getType());
			System.out.print("【账户级别】:" + account.getLevel());
			System.out.println();
		} else {
			System.out.println("登录失败，用户名或者密码不正确！");
		}
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