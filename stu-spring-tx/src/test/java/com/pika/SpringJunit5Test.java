package com.pika;

import com.pika.config.MyConfig;
import com.pika.dao.BalanceDao;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Jupiter
 *
 * @author pikachu
 * @since 2023/4/11 16:55
 */
@ContextConfiguration(classes = MyConfig.class)
@ExtendWith(SpringExtension.class)
public class SpringJunit5Test {
    @Resource
    private BalanceDao balanceDao;

    @Test
    public void test() {
        balanceDao.testReadOnly();
    }
}
