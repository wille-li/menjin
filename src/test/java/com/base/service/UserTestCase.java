package com.base.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.menjin.user.service.If.UserServiceIf;

/**
 * 用户管理的测试类
 * @author Jack
 *
 */
@ContextConfiguration({"classpath:spring/spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class) //SpringJUnit支持，由此引入Spring—Test框架支持。  
@Transactional //这个非常关键，如果不加入这个注解配置，事务控制就会完全失效  
//这里的事务关联到配置文件中的事务控制器(transactionManager = "transactionManager")，同时指定自动回滚(defaultRollback = true).  
//这样做操作的数据库才不会污染数据库！  
@Rollback(false)//想看到更新效果就设置为false
public class UserTestCase {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserServiceIf userServiceIf;
	
	@Test
	public void testInsert(){
		for(int i=6;i<50;i++){
		com.menjin.user.model.User usr = new com.menjin.user.model.User();
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		usr.setUsername("Jack00"+i);
		usr.setPassword("123456");
		usr.setEmail("Becky@.qq.com");
		usr.setModifiedDate(date);
		usr.setStatus("1");
		usr.setCreatedDate(date);
		int effect = -1;
		effect = userServiceIf.add(usr);
		log.info("testInsert effect="+effect);
		}
	}
	
	
	@Test 
	public void testSelectByPrimaryKey(){
		com.menjin.user.model.User usr = new com.menjin.user.model.User();
		usr.setId(1);
		usr = userServiceIf.findById(usr);
		log.info("user password1:" + (usr!=null ? usr.getPassword() : "error"));
	}
	

	@Test 
	public void testselectByUserName(){
		String username = "ben";
		com.menjin.user.model.User usr = null;
		usr = userServiceIf.findByUsername(username);
		log.info("user password2:" + (usr!=null ? usr.getPassword() : "error"));
	}
	
	@Test 
	public void testDeleteById(){
		com.menjin.user.model.User usr = new com.menjin.user.model.User();
		usr.setId(2);
		int effect = -1;
		effect = userServiceIf.deleteById(usr);
		log.info("testDeleteById effect="+effect);
	}
	
	@Test 
	public void testModifyById(){
		com.menjin.user.model.User usr = new com.menjin.user.model.User();
		usr.setId(3);
		usr = userServiceIf.findById(usr);
		usr.setStatus("1");
		int effect = -1;
		effect = userServiceIf.modifyById(usr);
		log.info("testModifyById effect="+effect);
	}
}
