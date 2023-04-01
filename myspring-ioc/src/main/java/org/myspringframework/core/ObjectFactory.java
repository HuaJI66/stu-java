package org.myspringframework.core;

/**
 * @author pikachu
 * @since 2023/3/31 8:41
 */
@FunctionalInterface
public interface ObjectFactory<T> {
    T getObject();

}
