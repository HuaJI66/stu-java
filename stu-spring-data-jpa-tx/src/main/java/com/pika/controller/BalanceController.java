package com.pika.controller;

import com.pika.mapper.BalanceMapper;
import com.pika.utils.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pikachu
 * @since 2023/5/6 21:37
 */
@RestController
@RequestMapping("balance")
public class BalanceController {
    @Resource
    private BalanceMapper balanceMapper;

    @GetMapping("get/{id}")
    public R test2(@PathVariable("id") Integer id) {
        return R.ok().put("data", balanceMapper.selectById(id));
    }
}
