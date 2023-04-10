package com.pika;

import com.pika.config.Myconfig;
import com.pika.service.ShopService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author pikachu
 * @since 2023/4/10 10:45
 */
public class Test {
    @org.junit.Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Myconfig.class);
        ShopService shopService = context.getBean(ShopService.class);
        shopService.purchase();
    }

    @org.junit.Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Myconfig.class);
        ShopService shopService = context.getBean(ShopService.class);
        shopService.purchaseE();
    }
}
