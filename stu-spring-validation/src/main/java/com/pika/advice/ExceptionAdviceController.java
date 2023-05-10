package com.pika.advice;

import com.pika.controller.TestController;
import com.pika.controller.UserController;
import com.pika.utils.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>{@link RestControllerAdvice} 处理 Controller 抛出的异常</p>
 *
 * @author pikachu
 * @since 2023/5/5 14:30
 */
@RestControllerAdvice(assignableTypes = {UserController.class, TestController.class})
public class ExceptionAdviceController {
    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R globalExceptionHandle(MethodArgumentNotValidException e) {
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> errorMsg.append(fieldError.getDefaultMessage()).append(";"));
        return R.error(HttpStatus.FORBIDDEN.value(), errorMsg.toString());
    }

    @ExceptionHandler(value = Exception.class)
    public R defaultHandle(Exception exception) {
        return R.error(exception.getMessage());
    }
}
