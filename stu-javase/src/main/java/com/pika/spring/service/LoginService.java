package com.pika.spring.service;

/**
 * @author pi'ka'chu
 */
@FunctionalInterface
public interface LoginService {
    Object getUserInfo(String code);
}
