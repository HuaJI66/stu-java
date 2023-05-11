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

    @Test
    public void test2() {
        String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        String uri = "hsf.fer";
        System.out.println("uri.matches(regex) = " + uri.matches(regex));
    }
}
