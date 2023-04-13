package com.ke.mytest.service.anno;

import java.lang.annotation.*;

/**
 * @author Zhang Xudong
 * @date 2023/4/13 22:13
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Handle {

}
