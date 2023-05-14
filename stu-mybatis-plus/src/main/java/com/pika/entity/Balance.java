package com.pika.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * &#064;TableName  t_balance
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_balance", autoResultMap = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class Balance extends AbstractAuditBase implements Serializable {
    /**
     * 使用 JPA 在数据库中非持久化一个字段？
     * <p> transient\final\static 修饰的字段不会被序列化和反序列化</p>
     */
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "balance")
    private BigDecimal balance;


    public Balance(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public Balance(Integer id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }
}
