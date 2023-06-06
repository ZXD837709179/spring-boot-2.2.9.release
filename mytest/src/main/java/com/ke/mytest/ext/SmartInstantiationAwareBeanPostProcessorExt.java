package com.ke.mytest.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

/**
 * @author Zhang Xudong
 * @date 2023/6/5 23:37
 * SmartInstantiationAwareBeanPostProcessor其余的方法同InstantiationAwareBeanPostProcessor 不在这里展示
 */
@Component
public class SmartInstantiationAwareBeanPostProcessorExt implements SmartInstantiationAwareBeanPostProcessor {
	/**
	 * 该触发点发生在postProcessBeforeInstantiation之前，这个方法用于预测Bean的类型，
	 * 返回第一个预测成功的Class类型，如果不能预测返回null；
	 * 当你调用BeanFactory.getType(name)时当通过bean的名字无法得到bean类型信息时就调用该回调方法来决定类型信息。
	 *
	 * @param beanClass
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	/**
	 * 检测Bean的构造器，可以检测出多个候选构造器，再有相应的策略决定使用哪一个，
	 * 如AutowiredAnnotationBeanPostProcessor实现将自动扫描通过@Autowired/@Value注解的构造器从而可以完成构造器注入
	 *
	 * @param beanClass
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName)
			throws BeansException {

		return null;
	}

	/**
	 * 获得提前暴露的bean引用，主要用于解决循环引用的问题。
	 *
	 * @param bean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
