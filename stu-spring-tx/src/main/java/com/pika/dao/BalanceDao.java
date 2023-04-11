package com.pika.dao;

/**
 * @author pikachu
 * @since 2023/4/10 21:55
 */
public interface BalanceDao {

    void transfer(Integer from, Integer to, Integer money);

    void test(Integer from, Integer to, Integer money);

    void testTimeout();

    void testNoTimeOut();

    void testNoReadOnly();

    void testReadOnly();
}
