package com.machine.mybatis.service;

import java.util.List;

import javax.annotation.Resource;

import com.machine.mybatis.MybatisMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.machine.mybatis.servive.IDeptService;
import com.machine.mybatis.vo.Dept;

/**
 * @Description: DeptServiceTest
 *
 * @author machine
 * @date 2017年12月18日 上午12:15:24
*/
@SpringBootTest(classes = MybatisMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DeptServiceTest {
	
	@Resource
	private IDeptService deptService;
	
	/**
	 * @Description: testList
	 *
	 * @author machine
	 * @date 2017年12月18日 上午12:15:30
	*/
	@Test
	public void testList() {
		List<Dept> list = this.deptService.list();
		System.out.println(list);
	}
	
	/**
	 * @Description: testAdd
	 *
	 * @author machine
	 * @date 2017年12月18日 上午12:15:34
	*/
	@Test
	public void testAdd() {
		Dept dept = new Dept();
		dept.setName("测试部");
		System.out.println(this.deptService.add(dept));
	}
}
