package com.pika.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private final String id = "id";
    private final String createdBy = "createdBy";
    private final String createdTime = "createdTime";
    private final String updatedBy = "updatedBy";
    private final String updatedTime = "updatedTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter(createdBy)) {
            this.setFieldValByName(createdBy, "SYSTEM", metaObject);
        }
        if (metaObject.hasSetter(createdTime)) {
            this.setFieldValByName(createdTime, LocalDateTime.now(), metaObject);
        }
        if (metaObject.hasSetter(updatedBy)) {
            this.setFieldValByName(updatedBy, "SYSTEM", metaObject);
        }
        if (metaObject.hasSetter(updatedTime)) {
            this.setFieldValByName(updatedTime, LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter(updatedBy)) {
            this.setFieldValByName(updatedBy, "SYSTEM", metaObject);
        }
        if (metaObject.hasSetter(updatedTime)) {
            this.setFieldValByName(updatedTime, LocalDateTime.now(), metaObject);
        }
    }
}
