package com.pika.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @TableName t_balance
 */
@Data
public class Balance implements Serializable {

    /**
     *
     */
    @NotNull(message = "[]不能为空")
    private Integer id;
    /**
     *
     */
    @NotBlank(message = "[]不能为空")
    @Size(max = 20, message = "编码长度不能超过20")
    @Length(max = 20, message = "编码长度不能超过20")
    private String name;
    /**
     *
     */
    private BigDecimal balance;

}
