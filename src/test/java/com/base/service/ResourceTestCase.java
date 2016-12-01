package com.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.base.annotation.log.SystemControllerLog;
import com.base.entity.SimplePage;
import com.menjin.test.service.TestService;
import com.menjin.user.model.Resource;
import com.menjin.user.service.If.ResourceServiceIf;

/**
 * 访问资源管理（权限）的测试类
 * @author Jack
 *
 */
@ContextConfiguration({"classpath:spring/spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class) //SpringJUnit支持，由此引入Spring—Test框架支持。  
@Transactional //这个非常关键，如果不加入这个注解配置，事务控制就会完全失效  
//这里的事务关联到配置文件中的事务控制器(transactionManager = "transactionManager")，同时指定自动回滚(defaultRollback = true).  
//这样做操作的数据库才不会污染数据库！  
@Rollback(false)//想看到更新效果就设置为false
public class ResourceTestCase {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ResourceServiceIf resourceServiceIf;
	
	@Test
	public void testInsert(){
		com.menjin.user.model.Resource resource1 = new com.menjin.user.model.Resource();
		com.menjin.user.model.Resource resource2 = new com.menjin.user.model.Resource();
		List<com.menjin.user.model.Resource> list = new ArrayList<com.menjin.user.model.Resource>();
		resource1.setResourceUrl("/admin.jsp*");
		resource1.setName("管理员页面");
		resource1.setDescription("管理员页面");
		resource2.setResourceUrl("/**");
		resource2.setName("所有资源");
		resource2.setDescription("所有资源");
		list.add(resource1);
		list.add(resource2);
		int count = 0;
		for(com.menjin.user.model.Resource resource : list){
			int effect = -1;
			effect = resourceServiceIf.add(resource);
			if(effect>0){
				count++;
			}
		}
		log.info("testInsert count="+count);
	}
	
	@Test 
	public void testSelectAllResource(){
		List<com.menjin.user.model.Resource> list = null;
		list = resourceServiceIf.findAllResource();
		for(com.menjin.user.model.Resource resource : list){
			log.info(resource.toString());
		}
	}
	
	@Test 
	public void testSelectByPrimaryKey(){
		com.menjin.user.model.Resource resource1 = new com.menjin.user.model.Resource();
		resource1.setId(1);
		resource1 = resourceServiceIf.findById(resource1);
		log.info("select by id :" + (resource1!=null ? resource1.toString() : "error"));
	}
	
	@Test 
	public void testSelectByResourceUrl(){
		String resourceUrl = "/add/*";
		com.menjin.user.model.Resource resource = null;
		resource = resourceServiceIf.findByResourceUrl(resourceUrl);
		log.info("select by resourceUrl:" + (resource!=null ? resource.toString() : "error"));
	}
	
	@Test 
	public void testDeleteByPrimaryKey(){
		com.menjin.user.model.Resource resource = new com.menjin.user.model.Resource();
		resource.setId(3);
		int effect = -1;
		effect = resourceServiceIf.deleteById(resource);
		log.info("testDeleteByPrimaryKey effect="+effect);
	}
	
	@Test 
	public void testUpdateByPrimaryKey(){
		com.menjin.user.model.Resource resource = new com.menjin.user.model.Resource();
		resource.setId(2);
		resource = resourceServiceIf.findById(resource);
		resource.setResourceUrl("/save/*");
		int effect = -1;
		effect = resourceServiceIf.modifyById(resource);
		log.info("testUpdateByPrimaryKey effect="+effect);
	}
	
	@Test 
	public void testSelectResourcesByUserId(){
		List<com.menjin.user.model.Resource> list = null;
		Integer i = new Integer(1);
		list = resourceServiceIf.findResourcesByUserId(i);
		for(com.menjin.user.model.Resource resource : list){
			log.info(resource.toString());
		}
	}
	
	@Test 
	public void testSelectResourcesByUsername(){
		List<com.menjin.user.model.Resource> list = null;
		String username = "jack";
		list = resourceServiceIf.findResourcesByUsername(username);
		for(com.menjin.user.model.Resource resource : list){
			log.info(resource.toString());
		}
	}
	
	@Test
	public void testInsertRolesResource(){
		com.menjin.user.model.RolesResource rolesResource1 = new com.menjin.user.model.RolesResource();
		com.menjin.user.model.RolesResource rolesResource2 = new com.menjin.user.model.RolesResource();
		com.menjin.user.model.RolesResource rolesResource3 = new com.menjin.user.model.RolesResource();
		List<com.menjin.user.model.RolesResource> list = new ArrayList<com.menjin.user.model.RolesResource>();
		rolesResource1.setRoleId(1);
		rolesResource1.setResourceId(5);
		rolesResource2.setRoleId(1);
		rolesResource2.setResourceId(6);
		rolesResource3.setRoleId(3);
		rolesResource3.setResourceId(6);
		list.add(rolesResource1);
		list.add(rolesResource2);
		list.add(rolesResource3);
		int count = 0;
		for(com.menjin.user.model.RolesResource rolesResource : list){
			int effect = -1;
			effect = resourceServiceIf.addRolesResource(rolesResource);
			if(effect>0){
				count++;
			}
		}
		log.info("testInsertRolesResource count="+count);
	}
	
}
