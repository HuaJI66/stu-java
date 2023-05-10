package com.pika.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pika.entity.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author pi'ka'chu
 * @description 针对表【t_balance】的数据库操作Mapper
 * @createDate 2023-05-03 16:43:07
 */
@Mapper
public interface BalanceMapper extends BaseMapper<Balance> {
    void deduct(@Param("id") Integer id, @Param("money") BigDecimal money);

    void add(@Param("id") Integer id, @Param("money") BigDecimal money);
}




