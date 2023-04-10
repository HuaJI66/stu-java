package com.pika;

import com.pika.config.MyConfig;
import com.pika.service.impl.BalanceDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author pikachu
 * @since 2023/4/10 22:07
 */
public class Test {
    @org.junit.Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        BalanceDao balanceDao = context.getBean("balanceDaoImpl", BalanceDao.class);
        balanceDao.transfer(1, 2, 100);
    }
}
