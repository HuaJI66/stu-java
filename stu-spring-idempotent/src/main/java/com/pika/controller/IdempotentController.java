package com.pika.controller;

import com.pika.annotation.Idempotent;
import com.pika.annotation.Token;
import com.pika.entity.Order;
import com.pika.utils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author pikachu
 * @since 2023/5/10 10:37
 */
@RestController
@RequestMapping("idem")
public class IdempotentController {
    @PostMapping("test1")
    @Idempotent(tokenPosition = Idempotent.TokenPosition.REQUEST_BODY)
    public R test1(@RequestBody Order order) {
        return R.ok().setData(order);
    }

    @GetMapping("token")
    @Token
    public R test2(@RequestParam(Token.REQUEST_URI) String reqUri) {
        return R.ok().setData(reqUri);
    }
}
