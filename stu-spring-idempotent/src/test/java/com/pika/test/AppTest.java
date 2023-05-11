package com.pika.test;

import cn.hutool.json.JSONUtil;
import com.pika.controller.IdempotentController;
import com.pika.entity.Order;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author pikachu
 * @since 2023/5/10 21:22
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class AppTest {
    @Resource
    private WebApplicationContext webApplicationContext;

    @Test
    public void test1() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(IdempotentController.class).build();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/idem/test1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSONUtil.toJsonStr(new Order(1, "abc", "32")));
        String response = mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
        log.info("response:\n{}", JSONUtil.toJsonStr(response));
    }
}
