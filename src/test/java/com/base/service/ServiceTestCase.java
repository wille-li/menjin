package com.base.service;

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

/**
 * 基础框架的测试类
 * @author wille
 *
 */
@ContextConfiguration({"classpath:spring/spring.xml"})
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
		com.menjin.test.model.Test test = new com.menjin.test.model.Test();
		test.setTid(4);
		test = testService.findById(test);
		log.info(test.getName());
	}
	
	@Test
	@SystemControllerLog(description="")
	public void testCount(){
		int count = testService.findCount(null, null);
		log.info("Test表数据量为:" + count);
	}
	
	@Test
	public void testInsert(){
		com.menjin.test.model.Test entity = new com.menjin.test.model.Test();
		entity.setName("Jack");
		int insertTotalCount = 0;
		for (int i = 0; i < 1000; i++){
			entity.setName("Wille" + i);
			int insertCount = testService.add(entity);
			if (insertCount > 0) {
				insertTotalCount++;
			}
		}
		log.info("插入数据量为:" + insertTotalCount);
	}
	
	@Test 
	public void testUpdate(){
		com.menjin.test.model.Test test = new com.menjin.test.model.Test();
		test.setTid(4);
		test = testService.findById(test);
		log.info(test.getName());
		test.setName("Lin");
		int updateCount = testService.modifyById(test);
		log.info("更新数据量为:" + updateCount);
	}
	
	@Test
	public void testFindByPage(){
		int count = testService.findCount(null, null);
		SimplePage page = new SimplePage(1, 20, count);
		Map<String, Object> params = null;
		String orderBy = null;
		List<com.menjin.test.model.Test> testList = testService.findByPage(page, params, orderBy);
		log.info("Page size: " + testList.size());
		log.info("Content: " + testList.toString());
	}
}
