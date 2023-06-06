package com.ke.mytest.ext;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Zhang Xudong
 * @date 2023/6/5 22:31
 * 该接口在该类实例化后调用afterPropertiesSet
 */
@Component
public class InitializingBeanExt implements InitializingBean, DisposableBean, CommandLineRunner {


	public InitializingBeanExt(){
		//postProcessBeforeInstantiation之后执行
		System.out.println("InitializingBeanExt construct");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//实例化
		System.out.println("sout InitializingBeanExt afterPropertiesSet");
	}

	@PostConstruct
	public void init() {
		//初始化 postProcessBeforeInitialization之后
		System.out.println("sout InitializingBeanExt init");
	}


	@Override
	public void destroy() throws Exception {
		System.out.println("sout InitializingBeanExt destroy");
	}

	@PreDestroy
	public void destroy1() {
		//先于destroy实现类
		System.out.println("sout InitializingBeanExt destroy1");
	}


	@Override
	public void run(String... args) throws Exception {
		//容器刷新完成后,只会执行一次 所有的bean都注册完成
		System.out.println("InitializingBeanExt CommandLineRunner");
	}
}
