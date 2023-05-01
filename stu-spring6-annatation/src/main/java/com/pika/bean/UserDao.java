package com.pika.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @author pikachu
 * @since 2023/3/31 22:33
 */

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserDao {
}
