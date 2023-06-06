package com.ke.mytest.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Zhang Xudong
 * @date 2023/6/5 22:20
 */
@Component
public class BeanPostProcessorExt implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// 在Bean初始化之前对Bean进行处理
		if (beanName.equals("initializingBeanExt")){
			System.out.println("sout BeanPostProcessorExt postProcessBeforeInitialization");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// 在Bean初始化之后对Bean进行处理
		if (beanName.equals("initializingBeanExt")){
			System.out.println("sout BeanPostProcessorExt postProcessAfterInitialization");
		}
		return bean;
	}

}
