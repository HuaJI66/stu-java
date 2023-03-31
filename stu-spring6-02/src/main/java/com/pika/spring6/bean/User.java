package com.pika.spring6.bean;

import lombok.Data;

/**
 * @author pikachu
 * @since 2023/3/30 19:47
 */
@Data
public class User {
    private int id;
    private String name;

    private User() {
    }
}
