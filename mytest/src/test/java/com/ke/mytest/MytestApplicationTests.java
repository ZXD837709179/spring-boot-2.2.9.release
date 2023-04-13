package com.ke.mytest;

import com.ke.mytest.service.TestBeanService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
class MytestApplicationTests {
	@Resource
	TestBeanService testBeanService;

	@Test
	void contextLoads() {
		testBeanService.TestBeanService();
	}

}
