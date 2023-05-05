package com.pika.entity;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pikachu
 * @since 2023/5/5 9:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户名: 字母开头且只能是英文数字下划线
     */
    @Pattern(regexp = "^[a-zA-Z]\\w*$", message = "字母开头且只能是英文数字下划线,长度:5-20")
    @Size(min = 5, max = 20)
    private String username;
    /**
     * 密码: 至少含有大写字母、数字，长度: 10-20
     */
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "密码: 至少含有大写字母、数字，长度: 10-20")
    @Size(min = 10, max = 20)
    private String password;
    /**
     * 年龄：正整数
     */
    @Pattern(regexp = "^\\d+$", message = "年龄输入有误")
    private Integer age;
    /**
     * 性别: 0:男 1：女 2：其它
     */
    @Pattern(regexp = "^[012]$", message = "性别: 0:男 1：女 2：其它")
    private Byte sex;
}
