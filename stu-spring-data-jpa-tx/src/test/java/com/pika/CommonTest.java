package com.pika;

import com.pika.entity.Balance;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author pikachu
 * @since 2023/5/6 10:39
 */
public class CommonTest {
    @Test
    public void test1() {
        Balance balance1 = new Balance(1, BigDecimal.ONE);
        Balance balance2 = new Balance(1, BigDecimal.TEN);
        Balance balance3 = new Balance(1, BigDecimal.ONE);

        System.out.printf("balance1: %s\t balance2: %s\t equals:%s\n", balance1, balance2, balance1.equals(balance2));
        System.out.printf("balance1: %s\t balance3: %s\t equals:%s\n", balance1, balance3, balance1.equals(balance3));

        balance1.setCreatedBy("1");
        balance3.setCreatedBy("3");
        System.out.printf("balance1: %s\t balance3: %s\t equals:%s\n", balance1, balance3, balance1.equals(balance3));
    }

    @Test
    public void test2() {

    }
}
