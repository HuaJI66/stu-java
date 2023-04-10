package com.pika.service.impl;

/**
 * @author pikachu
 * @since 2023/4/10 21:55
 */
public interface BalanceDao {

    void transfer(Integer from, Integer to, Integer money);
}
