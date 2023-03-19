package com.youhuo.cloud.common.log;

import java.lang.annotation.*;

/**
 * <p>
 * logger 日志注解
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-19
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logger {
    String name() default "";

    /**
     * 是否开始日志， 默认全局开启
     */
    Boolean enable = Boolean.TRUE;


}
