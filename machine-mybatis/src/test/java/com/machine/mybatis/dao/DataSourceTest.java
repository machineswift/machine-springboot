package com.machine.mybatis.dao;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.machine.mybatis.MybatisMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Description: DataSourceTest
 *
 * @author machine
 * @date 2017年12月18日 上午12:14:55
*/
@SpringBootTest(classes = MybatisMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DataSourceTest {

	@Resource
	private DataSource dataSource;
	
	/**
	 * @Description: testIConnection
	 *
	 * @author machine
	 * @date 2017年12月18日 上午12:15:12
	*/
	@Test
	public void testIConnection() throws SQLException {
		System.out.println(this.dataSource.getConnection());
	}
}