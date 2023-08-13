package com.ke.mytest;

import com.ke.mytest.service.feign.FeignService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO Description
 *
 * @author Zhang Xudong
 * @since 2023/8/7 17:27
 */
@SpringBootTest(classes = MytestApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ServiceTest {
	@Resource
	FeignService feignService;

	@Test
	public void testFirstFeignService(){
		log.info(feignService.testBasicHello());
	}
}
