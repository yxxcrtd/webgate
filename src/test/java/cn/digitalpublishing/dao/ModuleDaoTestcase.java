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
import cn.digitalpublishing.po.SysModule;
import cn.digitalpublishing.util.PathUtil;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "file:src/test/remodules/appContext*.xml" })
//@ContextConfiguration({ "file:src/main/java/cn/digitalpublishing/remodule/appContext*.xml" })
@ContextConfiguration(locations={"classpath:appContext*.xml"})
public class ModuleDaoTestcase extends AbstractJUnit4SpringContextTests {
	
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
		//condition.put("name", "ddd");
		//condition.put("name", null);
		//condition.put("name", "");
		//condition.put("must", 3);
		
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ModuleDao").get("getList");
		List<SysModule> moduleList = daoFacade.getModuleDao().getList(condition, sort, hqlBean);
		System.out.println("count:" + moduleList.size());
		for(int i = 0; i < moduleList.size(); i ++) {
			SysModule module = moduleList.get(i);
			System.out.print("【i】:" + i);
			System.out.print("【模块ID】:" + module.getId());
			System.out.print("【模块名称】:" + module.getName());
			System.out.print("【模块链接】:" + module.getLink());
			System.out.print("【模块更多链接】:" + module.getMore());
			System.out.print("【模块图标】:" + module.getIcon());
			System.out.print("【模块是否必须】:" + module.getMust());
			System.out.print("【模块高度】:" + module.getHeight());
			System.out.print("【模块宽度】:" + module.getWidth());
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
		
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ModuleDao").get("getPagingList");
		List<SysModule> moduleList = daoFacade.getModuleDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		System.out.println("count:" + moduleList.size());
		for(int i = 0; i < moduleList.size(); i ++) {
			SysModule module = moduleList.get(i);
			System.out.print("【i】:" + i);
			System.out.print("【模块ID】:" + module.getId());
			System.out.print("【模块名称】:" + module.getName());
			System.out.print("【模块链接】:" + module.getLink());
			System.out.print("【模块更多链接】:" + module.getMore());
			System.out.print("【模块图标】:" + module.getIcon());
			System.out.print("【模块是否必须】:" + module.getMust());
			System.out.print("【模块高度】:" + module.getHeight());
			System.out.print("【模块宽度】:" + module.getWidth());
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
		
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ModuleDao").get("getCount");
		Integer count = daoFacade.getModuleDao().getCount(condition, hqlBean);
		System.out.println("Count:" + count);
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