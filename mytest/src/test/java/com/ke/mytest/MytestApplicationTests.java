package com.ke.mytest;

import com.ke.mytest.configuration.CommonConfig;
import com.ke.mytest.model.po.Dog;
import com.ke.mytest.service.TestBeanService;
import java.util.Arrays;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MytestApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
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

	/**
	 * 测试BeanDefinitionRegistry,用来注册beanDefinition
	 */
	@Test
	void testBeanDefinitionRegistry() {
		SimpleBeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		//父类BeanDefinition定义
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
		rootBeanDefinition.setBeanClass(Dog.class);
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("color", "red"));
		propertyValues.addPropertyValue("age", 2);
		rootBeanDefinition.setPropertyValues(propertyValues);


		registry.registerBeanDefinition("dog", rootBeanDefinition);

		//子类BeanDefinition定义
		ChildBeanDefinition childBeanDefinition = new ChildBeanDefinition("dog");
		propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("name", "teddy");
		childBeanDefinition.setPropertyValues(propertyValues);
		registry.registerBeanDefinition("teddyDog", childBeanDefinition);

		//日志打印registry信息
		log.info("dog------->" + registry.getBeanDefinition("teddyDog").getPropertyValues());
	}

	/**
	 *	测试XmlBeanDefinitionReader,
	 *	用来读取xml文件,放置到BeanDefinitionRegistry中
	 */
	@Test
	void testXmlBeanDefinitionReader() {
		//创建一个xml读取器
		SimpleBeanDefinitionRegistry simpleBeanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(simpleBeanDefinitionRegistry);
		reader.loadBeanDefinitions("classpath:bean.xml");
		log.info("dog------->" + simpleBeanDefinitionRegistry.getBeanDefinition("dog").getBeanClassName());
	}


	/**
	 * 测试ClassPathBeanDefinitionScanner,用来扫描路径上的bean对象
	 */
	@Test
	void testBeanScanner(){
		SimpleBeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
		scanner.scan("com.ke.mytest.model.po");
		log.info(Arrays.toString(registry.getBeanDefinitionNames()));
	}

}
