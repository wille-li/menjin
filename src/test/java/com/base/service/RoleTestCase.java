package com.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.menjin.user.service.If.RoleServiceIf;

/**
 * 用户角色管理的测试类
 * @author Jack
 *
 */
@ContextConfiguration({"classpath:spring/spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class) //SpringJUnit支持，由此引入Spring—Test框架支持。  
@Transactional //这个非常关键，如果不加入这个注解配置，事务控制就会完全失效  
//这里的事务关联到配置文件中的事务控制器(transactionManager = "transactionManager")，同时指定自动回滚(defaultRollback = true).  
//这样做操作的数据库才不会污染数据库！  
@Rollback(false)//想看到更新效果就设置为false
public class RoleTestCase {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private RoleServiceIf roleServiceIf;
	
	@Test
	public void testInsert(){
		com.menjin.user.model.Role role1 = new com.menjin.user.model.Role();
		com.menjin.user.model.Role role2 = new com.menjin.user.model.Role();
		List<com.menjin.user.model.Role> list = new ArrayList<com.menjin.user.model.Role>();
		role1.setName("ROLE_USER");
		role1.setDescription("普通用户角色");
		role2.setName("ROLE_Mamager");
		role2.setDescription("错误用户角色");
		list.add(role1);
		list.add(role2);
		int count = 0;
		for(com.menjin.user.model.Role role : list){
			int effect = -1;
			effect = roleServiceIf.add(role);
			if(effect>0){
				count++;
			}
		}
		log.info("testInsert count="+count);
	}
	
	@Test 
	public void testSelectByPrimaryKey(){
		com.menjin.user.model.Role role = new com.menjin.user.model.Role();
		role.setId(1);
		role = roleServiceIf.findById(role);
		log.info("get role by id:" + (role!=null ? role.toString() : "error"));
	}
	
	@Test 
	public void testSelectByRoleName(){
		String roleName = "ROLE_ADMIN";
		com.menjin.user.model.Role role = null;
		role = roleServiceIf.findByRoleName(roleName);
		log.info("get role by roleName:" + (role!=null ? role.toString() : "error"));
	}
	
	@Test 
	public void testDeleteByPrimaryKey(){
		com.menjin.user.model.Role role = new com.menjin.user.model.Role();
		role.setId(2);
		int effect = -1;
		effect = roleServiceIf.deleteById(role);
		log.info("testDeleteByPrimaryKey effect="+effect);
	}
	
	@Test 
	public void testUpdateByPrimaryKey(){
		com.menjin.user.model.Role role = new com.menjin.user.model.Role();
		role.setId(4);
		role = roleServiceIf.findById(role);
		role.setName("ROLE_Manager");
		role.setDescription("设计角色");
		int effect = -1;
		effect = roleServiceIf.modifyById(role);
		log.info("testUpdateByPrimaryKey effect="+effect);
	}
	
	@Test
	public void testInsertUserRoles(){
		com.menjin.user.model.UserRoles role1 = new com.menjin.user.model.UserRoles();
		/*com.menjin.user.model.UserRoles role2 = new com.menjin.user.model.UserRoles();*/
		List<com.menjin.user.model.UserRoles> list = new ArrayList<com.menjin.user.model.UserRoles>();
		role1.setUserId(3);
		role1.setRoleId(1);
		/*role2.setUserId(1);
		role2.setRoleId(3);*/
		list.add(role1);
		/*list.add(role2);*/
		int count = 0;
		for(com.menjin.user.model.UserRoles role : list){
			int effect = -1;
			effect = roleServiceIf.addUserRoles(role);
			if(effect>0){
				count++;
			}
		}
		log.info("testInsertUserRoles count="+count);
	}

	@Test 
	public void testSearchRoleByUserId(){
		Integer i = new Integer(1);
		Set<com.menjin.user.model.Role> set= null;
		set = roleServiceIf.findRoleByUserId(i);
		for(com.menjin.user.model.Role role : set){
			log.info("testSearchRoleByUserId role="+(role!=null ? role.toString() : "error"));
		}
	}
	
	@Test 
	public void testSearchRoleByUsername(){
		String a = "jack";
		Set<com.menjin.user.model.Role> set= null;
		set = roleServiceIf.findRoleByUsername(a);
		for(com.menjin.user.model.Role role : set){
			log.info("testSearchRoleByUsername role="+(role!=null ? role.toString() : "error"));
		}
	}
	
	@Test 
	public void testSearchRoleByResourceId(){
		Integer i = new Integer(4);
		Set<com.menjin.user.model.Role> set= null;
		set = roleServiceIf.findRoleByResourceId(i);
		for(com.menjin.user.model.Role role : set){
			log.info("testSearchRoleByResourceId role="+(role!=null ? role.toString() : "error"));
		}
	}
	
	@Test 
	public void testDeleteRoleIdByUserId(){
		Integer i = new Integer(3);
		int effect = -1;
		effect = roleServiceIf.deleteByUserId(i);
		log.info("testDeleteRoleIdByUserId effect="+effect);
	}
}
