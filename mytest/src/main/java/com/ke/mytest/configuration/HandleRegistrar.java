package com.ke.mytest.configuration;

import com.ke.mytest.service.anno.EnableHandle;
import com.ke.mytest.service.anno.Handle;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Zhang Xudong
 * @date 2023/4/13 22:15
 */
public class HandleRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {
	private Environment environment;
	private ResourceLoader resourceLoader;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		//定义了一个扫描Handle类的扫描器
		MetaBeanDefinitionScanner scanner = new MetaBeanDefinitionScanner(registry, false, this.environment, this.resourceLoader);
		//扫描的路径
		Set<String> packagesToScan = getPackagesToScan(importingClassMetadata);
		scanner.scan(packagesToScan.toArray(new String[]{}));
	}

	private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
		AnnotationAttributes attributes =
				AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(EnableHandle.class.getCanonicalName()));
		assert attributes != null;
		String[] basePackages = attributes.getStringArray("basePackages");
		Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");

		Set<String> packagesToScan = new LinkedHashSet<>(Arrays.asList(basePackages));
		for (Class clz : basePackageClasses) {
			packagesToScan.add(ClassUtils.getPackageName(clz));
		}

		if (packagesToScan.isEmpty()) {
			packagesToScan.add(ClassUtils.getPackageName(metadata.getClassName()));
		}

		return packagesToScan;
	}

	public static class MetaBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

		public MetaBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, ResourceLoader resourceLoader) {
			super(registry, useDefaultFilters, environment, resourceLoader);
			addIncludeFilter(new AnnotationTypeFilter(Handle.class));
		}

	}


}
