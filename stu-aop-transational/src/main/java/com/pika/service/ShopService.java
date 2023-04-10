package com.pika.service;

import com.pika.annatation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pikachu
 * @since 2023/4/10 10:41
 */
@Service
public class ShopService {
    @Autowired
    PayService payService;

    @Transactional(Exception.class)
    public void purchase() {
        payService.pay();
        System.out.println("购买成功...");
    }

    @Transactional(Exception.class)
    public void purchaseE() {
        payService.payE();
        System.out.println("购买成功...");
    }

}
