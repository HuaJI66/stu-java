package com.pika.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author pikachu
 * @since 2023/5/12 22:41
 */
@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public void error(Exception e, HttpServletResponse response) {
        log.error("发生错误:{}，", e.getMessage());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
