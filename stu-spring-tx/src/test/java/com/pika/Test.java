package com.pika;

import com.pika.config.MyConfig;
import com.pika.dao.BalanceDao;
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
        balanceDao.test(1, 2, 100);
    }

    @org.junit.Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        BalanceDao balanceDao = context.getBean("balanceDaoImpl", BalanceDao.class);
        balanceDao.transfer(1, 2, 100);
    }

    @org.junit.Test
    public void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        BalanceDao balanceDao = context.getBean("balanceDaoImpl", BalanceDao.class);
        balanceDao.testTimeout();
    }

    @org.junit.Test
    public void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        BalanceDao balanceDao = context.getBean("balanceDaoImpl", BalanceDao.class);
        balanceDao.testNoTimeOut();
    }
}
