package com.youhuo.cloud.common.exception;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>
 *      开启全局异常捕获
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ExceptionSelector.class)
public @interface EnableAutoException {
}
