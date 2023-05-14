package com.pika;

import cn.hutool.core.util.IdUtil;
import org.junit.Test;

/**
 * @author pikachu
 * @since 2023/5/12 21:16
 */
public class CommonTest {
    @Test
    public void test1() {
        String simpledUUID = IdUtil.randomUUID();
        System.out.println("simpledUUID = " + simpledUUID);
    }
}
