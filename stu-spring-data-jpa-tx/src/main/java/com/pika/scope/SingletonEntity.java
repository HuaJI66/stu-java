package com.pika.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author pikachu
 * @since 2023/5/3 17:40
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingletonEntity {
}
