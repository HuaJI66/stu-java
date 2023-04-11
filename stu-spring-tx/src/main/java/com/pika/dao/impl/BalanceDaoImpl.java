package com.pika.dao.impl;

import com.pika.dao.BalanceDao;
import com.pika.entity.Balance;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 一共有七种传播行为：
 * <p>
 * REQUIRED：支持当前事务，如果不存在就新建一个(默认)
 * 【没有就新建，有就加入】
 * <p>
 * SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行
 * 【有就加入，没有就不管了】
 * <p>
 * MANDATORY：必须运行在一个事务中，如果当前没有事务正在发生，将抛出一个异常
 * 【有就加入，没有就抛异常】
 * <p>
 * REQUIRES_NEW：开启一个新的事务，如果一个事务已经存在，则将这个存在的事务挂起
 * 【不管有没有，直接开启一个新事务，开启的新事务和之前的事务不存在嵌套关系，之前事务被挂起】
 * <p>
 * NOT_SUPPORTED：以非事务方式运行，如果有事务存在，挂起当前事务
 * 【不支持事务，存在就挂起,以非事务（自动提交）运行隔离级别为 NOT_SUPPORTED 的代码，执行完成后在恢复被挂起的事务】
 * <p>
 * NEVER：以非事务方式运行，如果有事务存在，抛出异常
 * 【不支持事务，存在就抛异常】
 * <p>
 * NESTED：如果当前正有一个事务在进行中，则该方法应当运行在一个嵌套式事务中。
 * 被嵌套的事务可以独立于外层事务进行提交或回滚。如果外层事务不存在，行为就像REQUIRED一样。
 * 【有事务的话，就在这个事务里再嵌套一个完全独立的事务，嵌套的事务可以独立的提交和回滚。没有事务就和REQUIRED一样。】
 *
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

//        int x = 10 / 0;

        //加
        sql = "update t_balance set balance=balance+? where id=?";
        jdbcTemplate.update(sql, money, to);
        System.out.println("用户:" + from + " 向用户:" + to + " 转账:" + money);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void test(Integer from, Integer to, Integer money) {
        BalanceDao balanceDao = (BalanceDao) AopContext.currentProxy();
        try {
            balanceDao.transfer(from, to, money);
        } catch (Exception ignored) {
        }
    }

    /**
     * timeout：事务开始最后一条DML语句执行结束所耗费的时间
     */
    @Transactional(timeout = 1, rollbackFor = Exception.class)
    @Override
    public void testTimeout() {
        BalanceDao balanceDao = (BalanceDao) AopContext.currentProxy();
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        balanceDao.transfer(1, 2, 100);
    }

    @Override
    @Transactional(timeout = 1, rollbackFor = Exception.class)
    public void testNoTimeOut() {
        BalanceDao balanceDao = (BalanceDao) AopContext.currentProxy();
        balanceDao.transfer(1, 2, 100);
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void testNoReadOnly() {
        BalanceDao balanceDao = (BalanceDao) AopContext.currentProxy();
        balanceDao.transfer(1, 2, 100);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void testReadOnly() {
        String sql = "select * from t_balance";
        List<Balance> balances = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Balance.class));
        balances.forEach(System.out::println);
    }
}
