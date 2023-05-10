package com.pika;

import com.pika.entity.Balance;
import com.pika.mapper.BalanceMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author pikachu
 * @since 2023/5/3 16:45
 */
@ContextConfiguration(classes = JPATransactionApp.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestApp {
    private final Random random = new Random();
    @Resource
    private BalanceMapper balanceMapper;

    @Test
    public void test1() {
        balanceMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void test2() {
        Balance balance = new Balance("test" + random.nextInt(), BigDecimal.valueOf(666));
        balanceMapper.insert(balance);
    }

    @Test
    public void test3() {
        Balance balance = new Balance(2, BigDecimal.valueOf(777));
        balanceMapper.updateById(balance);
    }
}
