package com.ke.mytest.service.anno;

import com.ke.mytest.configuration.HandleRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Zhang Xudong
 * @date 2023/4/13 22:14
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({HandleRegistrar.class})
public @interface EnableHandle {
	String[] value() default {};

	String[] basePackages() default {};

	Class<?>[] basePackageClasses() default {};
}
