package com.pika.entity;

import lombok.Data;

/**
 * @author pikachu
 * @since 2023/5/12 21:41
 */
@Data
public class LeaseReq {
    String randomness;
    String username;
    String guid;
    Boolean offline = Boolean.FALSE;
    String clientTime;
}
