package com.youhuo.cloud.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 异常实体封装
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorCode {
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String msg;
}
