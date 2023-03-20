package com.youhuo.cloud.common.exception;



import com.youhuo.cloud.common.exception.enums.GlobalErrorCodeConstants;
import com.youhuo.cloud.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 全局异常
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-03
 */
@Slf4j
@Component
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    /**
     * <p>
     * 全局Base异常处理
     * </p>
     *
     * @param e
     * @version 1.0.0
     * @author Dylan
     * @since 2020-03-03
     */
    @ExceptionHandler({BaseException.class})
    public Result customExceptionHandler(BaseException e) {
        log.error("BaseException -->", e);
        return Result.error(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * 其他类型的异常处理
     * </p>
     *
     * @param e
     * @version 1.0.0
     * @author Dylan
     * @since 2020-03-03
     */
    @ExceptionHandler({Exception.class})
    public Result customExceptionHandler(Exception e) {
        log.error("Exception -->", e);
        return Result.error(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ServerException.class, ServerException.class, BusinessException.class})
    public Result serExceptionHandler(Exception e) {
        log.error("serExceptionHandler -->", e);
        return Result.error(GlobalErrorCodeConstants.SER_ERROR.getCode(), e.getMessage());
    }


    @ExceptionHandler({DataAccessException.class, SQLException.class})
    public Result sqlExceptionHandler(Exception e) {
        log.error("sqlExceptionHandler -->", e);
        return Result.error(GlobalErrorCodeConstants.BAD_REQUEST);
    }

    /**
     * <p>
     * IOException
     * </p>
     *
     * @param e
     * @version 1.0.0
     * @author Dylan
     * @since 2020-03-03
     */
    @ExceptionHandler({IOException.class})
    public Result ioExceptionHandler(IOException e) {
        log.error("ioExceptionHandler -->", e);
        return Result.error(GlobalErrorCodeConstants.IO_ERROR);
    }


    /**
     * <p>
     * 参数异常处理
     * </p>
     *
     * @param exception
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020-03-03
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result methodNotValidHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.error("methodNotValidHandler -->", exception);
        return Result.error(904, fieldErrors.get(0).getDefaultMessage());
    }

}
