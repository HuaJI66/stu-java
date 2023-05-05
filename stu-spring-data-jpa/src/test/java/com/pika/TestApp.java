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

/**
 * @author pikachu
 * @since 2023/5/3 16:45
 */
@ContextConfiguration(classes = JPATestApp.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestApp {
    @Resource
    private BalanceMapper balanceMapper;

    @Test
    public void test1() {
        balanceMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void test2() {
        Balance balance = new Balance(null, "test", BigDecimal.valueOf(666));
        balanceMapper.insert(balance);
    }
}
