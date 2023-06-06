package com.ke.mytest.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Zhang Xudong
 * @date 2023/6/5 23:21
 */
@Component
public class InstantiationAwareBeanPostProcessorExt implements InstantiationAwareBeanPostProcessor {
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		//实例化之前 优先级最高
		if (beanName.equals("initializingBeanExt")) {
			System.out.println("sout InstantiationAwareBeanPostProcessorExt postProcessBeforeInstantiation");
		}
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		//实例化之后 优先级次高
		if (beanName.equals("initializingBeanExt")) {
			System.out.println("sout InstantiationAwareBeanPostProcessorExt postProcessAfterInstantiation");
		}
		return true;
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
			throws BeansException {
		//属性设置 优先级第三
		if (beanName.equals("initializingBeanExt")) {
			System.out.println("sout InstantiationAwareBeanPostProcessorExt postProcessProperties");
		}
		return null;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		//初始化之前 优先级第四
		if (beanName.equals("initializingBeanExt")) {
			System.out.println("sout InstantiationAwareBeanPostProcessorExt postProcessBeforeInitialization");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		//初始化之后 优先级第五
		if (beanName.equals("initializingBeanExt")) {
			System.out.println("sout InstantiationAwareBeanPostProcessorExt postProcessAfterInitialization");
		}
		return bean;
	}
}
