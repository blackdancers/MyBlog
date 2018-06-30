package com.github.fish.blog.annotations;


import com.github.fish.blog.enums.Module;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截Controller
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebControllerLog {

    String description() default "";

    /**
     * 注解所使用模块
     *
     * @return 模块名称
     */
    Module module() default Module.BASE;

}