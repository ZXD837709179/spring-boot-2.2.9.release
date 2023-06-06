package com.ke.mytest.ext;

import com.ke.mytest.model.DTO.Father;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @author Zhang Xudong
 * @date 2023/6/5 19:01
 * 每个bean的定义都会执行一次
 */
@Component
public class BeanDefinitionRegistryPostProcessorExt implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Father.class);
		registry.registerBeanDefinition("fatherBean",rootBeanDefinition);
		System.out.println("sout BeanDefinitionRegistryPostProcessorExt postProcessBeanDefinitionRegistry");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
		while (beanNamesIterator.hasNext()){
			String beanName = beanNamesIterator.next();
			if (beanName.equals("initializingBeanExt")){
				System.out.println("sout BeanDefinitionRegistryPostProcessorExt postProcessBeanFactory");
			}
		}
	}
}
