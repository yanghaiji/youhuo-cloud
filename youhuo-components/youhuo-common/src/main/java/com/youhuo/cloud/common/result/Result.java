package com.youhuo.cloud.common.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youhuo.cloud.common.exception.ErrorCode;
import com.youhuo.cloud.common.exception.ServiceException;
import com.youhuo.cloud.common.exception.enums.GlobalErrorCodeConstants;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 统一返回值
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-19
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 错误码
     *
     * @see ErrorCode#getCode()
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示，用户可阅读
     *
     * @see ErrorCode#getMsg() ()
     */
    private String msg;

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     * <p>
     * 因为 A 方法返回的 Result 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T>    返回的泛型
     * @return 新的 Result 对象
     */
    public static <T> Result<T> error(Result<?> result) {
        return error(result.getCode(), result.getMsg());
    }

    public static <T> Result<T> error(Integer code, String message) {
        Assert.isTrue(!GlobalErrorCodeConstants.SUCCESS.getCode().equals(code), "code 必须是错误的！");
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = message;
        return result;
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.data = data;
        result.msg = "Operation succeeded!";
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.msg = "Operation succeeded!";
        return result;
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(code, GlobalErrorCodeConstants.SUCCESS.getCode());
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isSuccess() {
        return isSuccess(code);
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isError() {
        return !isSuccess();
    }


    /**
     * 判断是否有异常。如果有，则抛出 {@link ServiceException} 异常
     */
    public void checkError() throws ServiceException {
        if (isSuccess()) {
            return;
        }
        // 业务异常
        throw new ServiceException(code, msg);
    }

    /**
     * 判断是否有异常。如果有，则抛出 {@link ServiceException} 异常
     * 如果没有，则返回 {@link #data} 数据
     */
    @JsonIgnore // 避免 jackson 序列化
    public T getCheckedData() {
        checkError();
        return data;
    }

    public static <T> Result<T> error(ServiceException serviceException) {
        return error(serviceException.getCode(), serviceException.getMessage());
    }
}
