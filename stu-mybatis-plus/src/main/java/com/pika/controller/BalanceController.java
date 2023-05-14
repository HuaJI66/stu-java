package com.pika.controller;

import com.pika.entity.Balance;
import com.pika.mapper.BalanceMapper;
import com.pika.utils.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pikachu
 * @since 2023/5/6 21:37
 */
@RestController
@RequestMapping("balance")
public class BalanceController {
    @Resource
    private BalanceMapper balanceMapper;

    @GetMapping("getById/{id}")
    public R test2(@PathVariable("id") Integer id) {
        return R.ok().put("data", balanceMapper.selectById(id));
    }

    @GetMapping("getByName/{name}")
    public R test3(@PathVariable String name) {
        List<Balance> balance = balanceMapper.getByName(name);
        return R.ok().setData(balance);
    }
}
