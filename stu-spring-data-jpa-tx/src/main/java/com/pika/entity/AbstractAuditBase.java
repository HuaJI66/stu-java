package com.pika.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author pi'ka'chu
 */
@Data
public abstract class AbstractAuditBase {
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT, insertStrategy = FieldStrategy.DEFAULT)
    private String createdBy;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;
}

