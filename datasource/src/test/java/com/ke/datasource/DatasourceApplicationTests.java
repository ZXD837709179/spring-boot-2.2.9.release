package com.ke.datasource;


import com.ke.datasource.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
class DatasourceApplicationTests {
	@Resource
	DataSource dataSource;

	@Test
	public void contextLoads() throws SQLException {
		//默认使用的HikariProxyConnection 没有额外的引用的话
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
	}

	@Resource
	UserService userService;
	@Test
	public void testUserService(){
		System.out.println(userService.findAllUser());
	}

}
