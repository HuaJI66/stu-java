package com.pika.service;

import org.springframework.stereotype.Service;

/**
 * @author pikachu
 * @since 2023/4/10 10:39
 */
@Service
public class PayService {
    public void pay() {
        System.out.println("支付成功...");
    }

    public void payE() {
        throw new RuntimeException("支付失败...");
    }
}
