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
import com.menjin.company.service.CompanyService;
import com.menjin.company.service.DepartmentService;
import com.menjin.company.service.EmployeeService;

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
public class EmployeeTestCase {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private CompanyService companyService;
	
	@Test
	public void testDBConnection(){
		com.menjin.company.model.Employee test = new com.menjin.company.model.Employee();
		test.setId(3);
		test = employeeService.findById(test);
		log.info(test.getEmployeeName());
	}
	
	@Test
	@SystemControllerLog(description="")
	public void testCount(){
		int count = employeeService.findCount(null, null);
		log.info("Employee表数据量为:" + count);
	}
	
	@Test
	public void testInsert(){
		
		com.menjin.company.model.Department department = new com.menjin.company.model.Department();
		department.setId(2);
		department = departmentService.findById(department);
		
		com.menjin.company.model.Employee entity = new com.menjin.company.model.Employee();
		entity.setDepartment(department);
		
		int insertTotalCount = 0;
		for (int i = 0; i < 100; i++){
			int insertCount = employeeService.add(entity);
			entity.setEmployeeName("Lin"+i);
			if (insertCount > 0) {
				insertTotalCount++;
			}
		}
		log.info("插入数据量为:" + insertTotalCount);
	}
	
	@Test 
	public void testUpdate(){
		com.menjin.company.model.Employee test = new com.menjin.company.model.Employee();
		test.setId(3);
		test = employeeService.findById(test);
		test.setMobile("13631393878");
		int updateCount = employeeService.modifyById(test);
		log.info("更新数据量为:" + updateCount);
	}
	
	@Test 
	public void testDelete(){
		com.menjin.company.model.Employee test = new com.menjin.company.model.Employee();
		test.setId(2);
		int i = employeeService.deleteById(test);
		log.info("更新数据量为:" + i);
	}
	
	@Test
	public void testFindByPage(){
		int count = employeeService.findCount(null, null);
		SimplePage page = new SimplePage(1, 20, count);
		Map<String, Object> params = null;
		String orderBy = null;
		List<com.menjin.company.model.Employee> testList = employeeService.findByPage(page, params, orderBy);
		log.info("Page size: " + testList.size());
		log.info("Content: " + testList.toString());
	}
	
	@Test
	public void testGetEmployeesByDepartmentId(){
		List<com.menjin.company.model.Employee> employees = employeeService.getEmployeesByDepartmentId(3);
		log.info("List size: " + employees.size());
		log.info("Content: " + employees.toString());
	}
	
	@Test
	public void testGetEmployeesByCompanyId(){
		List<com.menjin.company.model.Employee> employees = employeeService.getEmployeesByCompanyId(3);
		log.info("List size: " + employees.size());
		log.info("Content: " + employees.toString());
	}
}
