package com.base.service;

import java.util.Calendar;
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
import com.menjin.company.model.Department;
import com.menjin.company.service.CompanyService;
import com.menjin.company.service.DepartmentService;

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
public class DepartmentTestCase {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private CompanyService companyService;
	
	@Test
	public void testDBConnection(){
		com.menjin.company.model.Department test = new com.menjin.company.model.Department();
		test.setId(4);
		test = departmentService.findById(test);
		log.info(test.getDepartmentName());
	}
	
	@Test
	@SystemControllerLog(description="")
	public void testCount(){
		int count = departmentService.findCount(null, null);
		log.info("Department表数据量为:" + count);
	}
	
	@Test
	public void testInsert(){
		com.menjin.company.model.Company company = new com.menjin.company.model.Company();
		company.setId(3);
		company = companyService.findById(company);
		com.menjin.company.model.Department entity = new com.menjin.company.model.Department();
		entity.setDepartmentName("技术部");
		entity.setDepartmentHeader("Lin");
		entity.setDepartmentPhone("123456");
		entity.setCompany(company);
		entity.setCreateBy("Lin");
		entity.setCreateTime(Calendar.getInstance().getTime());
		entity.setModifiedDate(Calendar.getInstance().getTime());
		int insertCount = departmentService.add(entity);
		/*int insertTotalCount = 0;
		for (int i = 0; i < 100; i++){
			int insertCount = departmentService.add(entity);
			if (insertCount > 0) {
				insertTotalCount++;
			}
		}*/
		log.info("插入数据量为:" + insertCount);
	}
	
	@Test 
	public void testUpdate(){
		com.menjin.company.model.Department test = new com.menjin.company.model.Department();
		test.setId(2);
		test = departmentService.findById(test);
		test.setDepartmentPhone("13631393878");
		int updateCount = departmentService.modifyById(test);
		log.info("更新数据量为:" + updateCount);
	}
	
	@Test 
	public void testDelete(){
		com.menjin.company.model.Department test = new com.menjin.company.model.Department();
		test.setId(5);
		int i = departmentService.deleteById(test);
		log.info("更新数据量为:" + i);
	}
	
	@Test
	public void testFindByPage(){
		int count = departmentService.findCount(null, null);
		SimplePage page = new SimplePage(1, 20, count);
		Map<String, Object> params = null;
		String orderBy = null;
		List<com.menjin.company.model.Department> testList = departmentService.findByPage(page, params, orderBy);
		log.info("Page size: " + testList.size());
		log.info("Content: " + testList.toString());
	}
	
	@Test
	public void testGetDepartmentsByCompanyId(){
		List<Department> departments = departmentService.getDepartmentsByCompanyId(3);
		log.info("Size:" + departments.size());
		log.info("Content:" + departments.toString());
		
	}
}
