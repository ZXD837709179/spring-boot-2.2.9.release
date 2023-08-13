package com.ke.mytest.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * feign测试
 *
 * @author Zhang Xudong
 * @since 2023/8/7 17:23
 */
@FeignClient(name ="my-feignService" ,url = "http://test1-taurus.nhservice.ke.com")
public interface FeignService {

	@GetMapping(value = "/")
	String testBasicHello();
}
