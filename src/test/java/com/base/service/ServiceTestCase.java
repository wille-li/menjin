package com.base.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.menjin.service.TestService;

/**
 * 基础框架的测试类
 * @author wille
 *
 */
@ContextConfiguration({"classpath:spring.xml","classpath:spring-dao.xml"})
@RunWith(SpringJUnit4ClassRunner.class) //SpringJUnit支持，由此引入Spring—Test框架支持。  
@Transactional //这个非常关键，如果不加入这个注解配置，事务控制就会完全失效  
//这里的事务关联到配置文件中的事务控制器(transactionManager = "transactionManager")，同时指定自动回滚(defaultRollback = true).  
//这样做操作的数据库才不会污染数据库！  
@Rollback(false)//想看到更新效果就设置为false
public class ServiceTestCase {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TestService testService;
	
	@Test
	public void testDBConnection(){
		com.menjin.model.Test test = new com.menjin.model.Test();
		test.setTid(1);
		test = testService.findById(test);
		log.info(test.getName());
	}
	
	@Test
	public void testCount(){
		int count = testService.findCount(null, null);
		log.info("Test表数据量为:" + count);
	}
	
	@Test
	public void testInsert(){
		com.menjin.model.Test entity = new com.menjin.model.Test();
		entity.setName("Jack");
		int insertCount = testService.add(entity);
		log.info("插入数据量为:" + insertCount);
	}
	
	@Test 
	public void testUpdate(){
		com.menjin.model.Test entity = new com.menjin.model.Test();
		entity.setTid(4);
		entity = testService.findById(entity);
		log.info(entity.getName());
		entity.setName("Lin");
		int updateCount = testService.modifyById(entity);
		log.info("更新数据量为:" + updateCount);
	}
}
