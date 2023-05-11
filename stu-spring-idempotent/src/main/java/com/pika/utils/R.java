package com.pika.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.TypeReference;
import jakarta.servlet.http.HttpServletResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
    public static final String DATA_NAME = "data";
    public static final String MESSAGE_NAME = "msg";
    public static final String MESSAGE_DEFAULT = "success";
    public static final String CODE_NAME = "code";
    public static final int CODE_ERROR = 500;
    public static final int CODE_SUCCESS = 200;
    public static final String TOKEN_NAME = "token";
    public static final String APPLICATION_JSON_VALUE = "application/json";
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final long serialVersionUID = 1L;

    public R() {
        put(CODE_NAME, CODE_SUCCESS);
        put(MESSAGE_NAME, MESSAGE_DEFAULT);
    }

    public static R error() {
        return error(CODE_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String message) {
        return error(CODE_ERROR, message);
    }

    public static R error(int code, String message) {
        R r = new R();
        r.put(CODE_NAME, code);
        r.put(MESSAGE_NAME, message);
        return r;
    }

    public static R ok(String message) {
        R r = new R();
        r.put(MESSAGE_NAME, message);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static void sendResponse(HttpServletResponse response, String msg) {
        try {
            response.setStatus(CODE_SUCCESS);
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().print(JSON.toJSONString(R.error(msg)));
        } catch (Exception ignored) {
        }

    }

    public static void sendResponse(HttpServletResponse response, int code, String msg) {
        try {
            response.setStatus(CODE_SUCCESS);
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(DEFAULT_CHARSET);
            response.getWriter().print(JSON.toJSONString(R.error(code, msg)));
        } catch (Exception ignored) {
        }
    }

    public static void sendToken(HttpServletResponse response, String token) {
        try {
            response.setStatus(CODE_SUCCESS);
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(DEFAULT_CHARSET);
            response.getWriter().print(JSON.toJSONString(R.ok().setToken(token)));
        } catch (Exception ignored) {
        }
    }

    public static void sendToken(HttpServletResponse response, String tokenName, String token) {
        try {
            response.setStatus(CODE_SUCCESS);
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(DEFAULT_CHARSET);
            response.getWriter().print(JSON.toJSONString(R.ok().put(tokenName, token)));
        } catch (Exception ignored) {
        }
    }

    public String getToken() {
        return get(TOKEN_NAME).toString();
    }

    public R setToken(String token) {
        put(TOKEN_NAME, token);
        return this;
    }

    public Object getData() {
        return this.get(DATA_NAME);
    }

    public R setData(Object data) {
        this.put(DATA_NAME, data);
        return this;
    }

    public <T> T getData(TypeReference<T> typeReference) {
        return getData(DATA_NAME, typeReference);
    }

    public <T> T getData(String key, TypeReference<T> typeReference) {
        String jsonStr = JSON.toJSONString(this.get(key), JSONWriter.Feature.IgnoreErrorGetter);
        return JSON.parseObject(jsonStr, typeReference);
    }

    public <T> T getData(Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(get(DATA_NAME), JSONWriter.Feature.IgnoreErrorGetter), clazz);
    }

    public String getMsg() {
        return (String) this.get(MESSAGE_NAME);
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {
        return Integer.parseInt(get(CODE_NAME) + "");
    }
}
