package com.pika.spring.aspect;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/26 22:38
 */
@Component
public class GiteeAspectHandle implements InvocationHandler {
    private Object proxied;

    public GiteeAspectHandle() {
    }

    public GiteeAspectHandle(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        isGiteeUser(method);
        return method.invoke(proxy, args);
    }

    private boolean isGiteeUser(Method method) {
        return proxied.getClass().getName().equals("com.pika.service.impl.GiteeLoginServiceImpl")
                && method.getName().equals("getUserInfo");
    }
}
