package com.pika.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pika.entity.Balance;
import com.pika.exception.BusinessException;
import com.pika.mapper.BalanceMapper;
import com.pika.service.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author pikachu
 * @since 2023/5/8 10:43
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance> implements BalanceService {

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deduct(Integer id, BigDecimal money) {
        baseMapper.deduct(id, money);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class, propagation = Propagation.NESTED)
    public void add(Integer id, BigDecimal money) throws BusinessException {
        baseMapper.add(id, money);
        throw new BusinessException("error");
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public void transfer(Integer from, Integer to, BigDecimal money) throws BusinessException {
        BalanceServiceImpl balanceService = (BalanceServiceImpl) AopContext.currentProxy();
        balanceService.deduct(from, money);
        balanceService.add(to, money);

    }
}
