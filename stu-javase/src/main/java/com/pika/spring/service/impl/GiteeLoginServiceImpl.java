package com.pika.spring.service.impl;

import com.pika.spring.annocation.Login;
import com.pika.spring.service.LoginService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/26 22:30
 */
@Login
@Aspect
@Service
public class GiteeLoginServiceImpl implements LoginService {
    @Override
    public Object getUserInfo(String code) {
        return code;
    }
}
