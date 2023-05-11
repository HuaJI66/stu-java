package com.pika.test;

import cn.hutool.crypto.digest.MD5;
import org.junit.Test;

/**
 * @author pikachu
 * @since 2023/5/10 20:16
 */
public class CommonTest {
    @Test
    public void test1() {
        MD5 md5 = MD5.create();
        String hex16 = md5.digestHex16("fhiuehgvfiu");
        System.out.println("hex16 = " + hex16);
    }
}
