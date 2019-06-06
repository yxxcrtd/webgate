package cn.digitalpublishing.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.digitalpublishing.facade.DaoFacade;
import cn.digitalpublishing.po.SysPage;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "file:src/test/repages/appContext*.xml" })
//@ContextConfiguration({ "file:src/main/java/cn/digitalpublishing/repage/appContext*.xml" })
@ContextConfiguration(locations={"classpath:appContext*.xml"})
public class PageDaoTestcase extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private DaoFacade daoFacade;
	
	@BeforeClass
	public static void init() {
		System.out.println("Initializing...");
	}
	
	@AfterClass
	public static void release() {
		System.out.println("Releasing...");
		//ProxoolFacade.shutdown(0);
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
		//List<SysPage> pageList = daoFacade.getSysPageDao().getList(condition, sort);
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("status", 1);
		//condition.put("type", 1);
		//condition.put("level", 1);
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.PageDao").get("getList");
		List<SysPage> pageList = daoFacade.getPageDao().getList(condition, sort, hqlBean);
		System.out.println("count:" + pageList.size());
		for(int i = 0; i < pageList.size(); i ++) {
			SysPage page = pageList.get(i);
			System.out.print("【i】:" + i);
			System.out.print("【页面ID】:" + page.getId());
			System.out.print("【页面名称】:" + page.getName());
			System.out.print("【页面编号】:" + page.getCode());
			System.out.println();
		}
		System.out.println("---------------------------------------------------------");
	}

	// 获取分页列表
	//@Test
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
		String sort = null;
		Integer pageCount = 10;
		Integer page = 0;		//当前页是第一页
		//Integer page = 1;		//当前页是第二页
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.PageDao").get("getPagingList");
		List<SysPage> pagingList = daoFacade.getPageDao().getPagingList(condition, sort, pageCount, page, hqlBean);
		System.out.println("count:" + pagingList.size());
		for(int i = 0; i < pagingList.size(); i ++) {
			SysPage sysPage = pagingList.get(i);
			System.out.print("【i】:" + i);
			System.out.print("【页面ID】:" + sysPage.getId());
			System.out.print("【页面名称】:" + sysPage.getName());
			System.out.print("【页面编号】:" + sysPage.getCode());
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
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.PageDao").get("getCount");
		Integer count = daoFacade.getPageDao().getCount(condition, hqlBean);
		System.out.println("Count:" + count);
		System.out.println("---------------------------------------------------------");
	}
	
	// 插入
	//@Test
	public void testInsert() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing insert...");
		
		SysPage page = new SysPage();
		page.setName("其他页面");
		
		daoFacade.getPageDao().insert(page);
		System.out.println("---------------------------------------------------------");
	}
	
	// 更新
	//@Test
	public void testUpdate() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing update...");
		
		String id = "402881e53e99808f013e998093f70000";
		SysPage page = (SysPage) daoFacade.getPageDao().get(SysPage.class.getName(), id);

		page.setName("非法页面");
		
		daoFacade.getPageDao().update(page, SysPage.class.getName(), id, null);
		System.out.println("---------------------------------------------------------");
	}
	
	// 获取
	//@Test
	public void testGet() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing get...");
		String id = "402881e53e99808f013e998093f70000";
		SysPage page = (SysPage) daoFacade.getPageDao().get(SysPage.class.getName(), id);
		System.out.print("【页面ID】:" + page.getId());
		System.out.print("【页面名称】:" + page.getName());
		System.out.print("【页面编号】:" + page.getCode());
		System.out.println();
		System.out.println("---------------------------------------------------------");
	}
	
	// 删除
	//@Test
	public void testDelete() throws Exception {
		System.out.println("---------------------------------------------------------");
		System.out.println("testing delete...");
		String id = "402881e53e99808f013e998093f70000";
		daoFacade.getPageDao().delete(SysPage.class.getName(), id);
		System.out.println("---------------------------------------------------------");
	}
	
}