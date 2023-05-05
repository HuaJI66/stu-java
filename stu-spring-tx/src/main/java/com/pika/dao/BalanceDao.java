package com.pika.dao;

/**
 * @author pikachu
 * @since 2023/4/10 21:55
 */
public interface BalanceDao {
    void deduct(Integer userId, Integer money);

    void add(Integer userId, Integer money);

    void transfer(Integer from, Integer to, Integer money);

    void testTimeout();

    void testNoTimeOut();

    void testNoReadOnly();

    void testReadOnly();
}
