package com.youhuo.cloud.common.exception;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>
 *      IOC选择器
 *      https://blog.csdn.net/weixin_38937840/article/details/104617407
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-03 11:18
 */
public class ExceptionSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                "com.youhuo.cloud.common.exception.GlobalExceptionHandler"
        };
    }
}
