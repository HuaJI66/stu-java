package com.pika.service.impl;

import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pikachu
 * @since 2023/4/10 21:58
 */
@Repository
public class BalanceDaoImpl implements BalanceDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transfer(Integer from, Integer to, Integer money) {
        //减
        String sql = "update t_balance set balance=balance-? where id=?";
        jdbcTemplate.update(sql, money, from);

        int x = 10 / 0;

        //加
        sql = "update t_balance set balance=balance+? where id=?";
        jdbcTemplate.update(sql, money, to);
        System.out.println("用户:" + from + " 向用户:" + to + " 转账:" + money);
    }
}
