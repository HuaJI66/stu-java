package com.pika.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pikachu
 * @since 2023/5/10 12:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private String orderSn;
    private String token;
}
