package com.pika.controller;

import com.pika.exception.BusinessException;
import com.pika.service.BalanceService;
import com.pika.utils.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author pikachu
 * @since 2023/5/8 10:39
 */
@RestController
@RequestMapping("tx")
public class TransactionController {
    @Resource
    private BalanceService balanceServiceImpl;

    @GetMapping("transfer")
    public R test1(@RequestParam("from") Integer from,
                   @RequestParam("to") Integer to,
                   @RequestParam("money") BigDecimal money
    ) throws BusinessException {
        balanceServiceImpl.transfer(from, to, money);
        return R.ok();
    }
}
