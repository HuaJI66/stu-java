package com.pika.service.impl;

import com.pika.service.PayService;
import org.springframework.stereotype.Service;

/**
 * @author pikachu
 * @since 2023/4/2 11:24
 */
@Service
public class PayServiceImpl1 implements PayService {
    @Override
    public void test() {
        System.out.println("PayServiceImpl1.test()");
    }
}
