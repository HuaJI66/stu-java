package org.myspringframework.core;


import cn.hutool.core.collection.ConcurrentHashSet;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultDocument;
import org.dom4j.tree.DefaultElement;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * @author pikachu
 * @since 2023/3/30 22:26
 */
@SuppressWarnings("all")
public class ClassPathXmlApplicationContext extends ApplicationContext {
    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ObjectFactory<?>> singletonFactories = new ConcurrentHashMap<>();
    private final ConcurrentHashSet<Object> onCreationBeans = new ConcurrentHashSet<>();
    private Document document;

    /**
     * 解析配置文件，生成bean
     *
     * @param configLocation 配置文件路径
     */
    public ClassPathXmlApplicationContext(String configLocation) {
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream(configLocation);
            Objects.requireNonNull(in, "找不到指定配置文件: " + configLocation);
            //解析xml
            SAXReader reader = new SAXReader();
            document = reader.read(in);
//            nodes = document.selectNodes("//bean");
            List<Node> nodes = ((DefaultElement) ((DefaultDocument) document).getRootElement()).content();
            for (Node node : nodes) {
                if (node instanceof DefaultElement) {
                    DefaultElement element = (DefaultElement) node;
                    String id = element.attributeValue("id");
                    //尝试获取bean
                    Object instance = getBeanOnCreation(id);
                    if (instance == null) {
                        createBean(id, element);
                    }
                }
            }
            System.out.println("singletonObjects = " + singletonObjects);
//            System.out.println("earlySingletonObjects = " + earlySingletonObjects);
//            System.out.println("singletonFactories = " + singletonFactories);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createBean(String id, Element element) throws Exception {
        if (onCreationBeans.contains(id)) {
            throw new RuntimeException(id + " 正在创建，可能存在不可解决的循环依赖");
        }
        onCreationBeans.add(id);
        String className = element.attributeValue("class");
        Class<?> beanClass = Class.forName(className);
        // 实例化
        Object instance = instantiate(beanClass);
        //添加到3级缓存
        singletonObjects.put(id, instance);
        singletonFactories.put(id, () -> instance);
        //填充属性
        populate(instance, element.content());
        //初始化...
        addSingleton(id, instance);
    }


    private static Object instantiate(Class<?> beanClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> noArgsConstructor = beanClass.getDeclaredConstructor();
        noArgsConstructor.setAccessible(true);
        return noArgsConstructor.newInstance();
    }

    private void populate(Object instance, List<Node> propertyNodes) throws Exception {
        for (Node node : propertyNodes) {
            if (node instanceof DefaultElement) {
                DefaultElement element = (DefaultElement) node;
                String attributeName = element.attributeValue("name");
                Objects.requireNonNull(attributeName, "属性名未设置");
                String attributeValue = element.attributeValue("value");
                String attributeRef = element.attributeValue("ref");

                if (attributeValue != null) {
                    doPopulate(instance, attributeName, attributeValue);
                } else if (attributeRef != null) {
                    Object refBean = getBeanOnCreation(attributeRef);
                    if (refBean == null) {
                        Element refElement = getBeanElementById(attributeRef);
                        createBean(attributeRef, refElement);
                        //再次获取refBean
                        refBean = getBeanOnCreation(attributeRef);
                        assertTrue("无法为" + node.getParent().attributeValue("id") + "注入属性:" + attributeRef, refBean != null);
                    }
                    doPopulate(instance, attributeName, refBean);
                } else {
                    throw new RuntimeException(attributeName + ":  未指定属性值");
                }
            }

        }
    }

    private Element getBeanElementById(String attributeRef) {
        List<Node> refNodes = document.selectNodes("//@id").stream().filter(item -> {
            if (item instanceof DefaultAttribute) {
                return ((DefaultAttribute) item).getValue().equals(attributeRef);
            }
            return false;
        }).collect(Collectors.toList());
        assertTrue("找不到ref属性指向的bean: " + attributeRef, refNodes.size() > 0);
        return refNodes.get(refNodes.size()-1).getParent();
    }

    private void doPopulate(Object instance, String attributeName, Object attributeValue) throws Exception {
        Class<?> instanceClass = instance.getClass();
        Class<?> fieldType = instanceClass.getDeclaredField(attributeName).getType();
        Method setMethod = instanceClass.getDeclaredMethod("set" + String.valueOf(attributeName.charAt(0)).toUpperCase() + attributeName.substring(1), fieldType);
        Object value = castValue(attributeValue, fieldType);
        setMethod.invoke(instance, value);
    }

    private Object castValue(Object attributeValue, Class<?> fieldType) throws Exception {
        Object result = attributeValue;
        if (fieldType.equals(int.class)) {
            result = Integer.valueOf(attributeValue.toString());
        } else if (fieldType.equals(long.class)) {
            result = Long.valueOf(attributeValue.toString());
        }
        return result;
    }

    private void addSingleton(String id, Object instance) {
        if (onCreationBeans.remove(id)) {
            singletonFactories.remove(id);
            earlySingletonObjects.remove(id);
            singletonObjects.put(id, instance);
        }
        singletonObjects.put(id, instance);
    }

    @Override
    public Object getBean(String name) {
        return singletonObjects.get(name);
    }

    private Object getBeanOnCreation(String name) {
        Object bean = singletonObjects.get(name);
        if (bean == null) {
            bean = earlySingletonObjects.get(name);
            if (bean == null) {
                ObjectFactory<?> objectFactory = singletonFactories.get(name);
                if (objectFactory != null) {
                    bean = objectFactory.getObject();
                    singletonFactories.remove(name);
                    earlySingletonObjects.put(name, bean);
                }
            }
        }
        return bean;
    }
}
