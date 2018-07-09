package com.github.fish.common.annotations;



import com.github.fish.common.enums.Module;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截service
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BizServiceLog {

    /**
     * 方法描述
     *
     * @return 方法描述
     */
    String description() default "";

    /**
     * 注解所使用模块
     *
     * @return 模块名称
     */
    Module module() default Module.BASE;


} 