package org.myspringframework.core;

/**
 * @author pikachu
 * @since 2023/3/30 22:19
 */
@SuppressWarnings("all")
public abstract class ApplicationContext {
    public abstract Object getBean(String name);

    public <T> T getBean(String name, Class<T> clazz) {
        return (T) getBean(name);
    }
}
