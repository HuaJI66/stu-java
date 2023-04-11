package com.pika;

import com.pika.config.MyConfig;
import com.pika.dao.BalanceDao;
import jakarta.annotation.Resource;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author pikachu
 * @since 2023/4/10 22:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class Test {
    @Resource
    private BalanceDao balanceDao;

    @org.junit.Test
    public void test1() {

        balanceDao.test(1, 2, 100);
    }

    @org.junit.Test
    public void test2() {

        balanceDao.transfer(1, 2, 100);
    }

    @org.junit.Test
    public void test3() {

        balanceDao.testTimeout();
    }

    @org.junit.Test
    public void test4() {

        balanceDao.testNoTimeOut();
    }

    @org.junit.Test
    public void test5() {

        balanceDao.testReadOnly();
    }

    @org.junit.Test
    public void test6() {

        balanceDao.testNoReadOnly();
    }
}
