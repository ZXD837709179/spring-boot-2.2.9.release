package com.ke.mytest;

import com.ke.mytest.configuration.CommonConfig;
import com.ke.mytest.service.TestBeanService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = MytestApplication.class)
@RunWith(SpringRunner.class)
class MytestApplicationTests {
	@Resource
	TestBeanService testBeanService;

	@Test
	void contextLoads() {
		testBeanService.TestBeanService();
	}

	@Test
	void testBean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CommonConfig.class);
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanName : beanDefinitionNames) {
			System.out.println(beanName);
		}
		context.getBean("serviceA");
	}

}
