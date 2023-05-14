package com.pika.exception;

/**
 * @author pikachu
 * @since 2023/5/8 23:37
 */
public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}
