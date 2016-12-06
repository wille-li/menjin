package com.menjin.opener.controller;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.menjin.opener.model.Opener;
import com.menjin.opener.service.OpenerService;

@ContextConfiguration({"classpath:spring/spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class) //SpringJUnit支持，由此引入Spring—Test框架支持。  
@Transactional //这个非常关键，如果不加入这个注解配置，事务控制就会完全失效  
//这里的事务关联到配置文件中的事务控制器(transactionManager = "transactionManager")，同时指定自动回滚(defaultRollback = true).  
//这样做操作的数据库才不会污染数据库！  
@Rollback(false)//想看到更新效果就设置为false
public class OpenerTest {

	@Autowired
	private OpenerService openerService;
	
	private Opener opener;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Before
	public void initData(){
		String openerIP="14.154.156.146";
		int openerPort = 5000;
		opener = new Opener();
		opener.setIp(openerIP);
		opener.setPort(openerPort);
		opener.setName("HHC-NET2D");
		opener.setNum("2");
	}
	
	@Test
	public void testOpenerGetStatus() throws IOException{
		String openerIP="14.154.156.146";
		int openerPort = 5000;
		Opener opener = new Opener();
		opener.setIp(openerIP);
		opener.setPort(openerPort);
		opener.setName("HHC-NET2D");
		opener.setNum("2");
		String status = openerService.getStatus(opener);
		
		logger.info(status);
	}
	
	@Test
	public void testOpenerOpen() throws IOException{
		String openerIP="14.154.156.146";
		int openerPort = 5000;
		Opener opener = new Opener();
		opener.setIp(openerIP);
		opener.setPort(openerPort);
		opener.setName("HHC-NET2D");
		opener.setNum("2");
		String status = openerService.getStatus(opener);
		
		logger.info(status);
	}
	
	@Test
	public void testOpenerClose() throws IOException{
		String openerIP="14.154.156.146";
		int openerPort = 5000;
		Opener opener = new Opener();
		opener.setIp(openerIP);
		opener.setPort(openerPort);
		opener.setName("HHC-NET2D");
		opener.setNum("2");
		String status = openerService.getStatus(opener);
		
		logger.info(status);
	}
}
