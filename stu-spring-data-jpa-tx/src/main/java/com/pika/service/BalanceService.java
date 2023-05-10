package com.pika.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pika.entity.Balance;
import com.pika.exception.BusinessException;

import java.math.BigDecimal;

/**
 * @author pikachu
 * @since 2023/5/8 10:42
 */
public interface BalanceService extends IService<Balance> {
    void deduct(Integer id, BigDecimal money);

    void add(Integer id, BigDecimal money) throws BusinessException;

    void transfer(Integer from, Integer to, BigDecimal money) throws BusinessException;
}
