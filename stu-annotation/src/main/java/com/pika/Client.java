package com.pika;

import com.pika.annotation.Component;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author pikachu
 * @since 2023/3/31 20:07
 */
public class Client {

    private String packageName;
    private final HashMap<String, Object> singletonObjects = new HashMap<>();

    @Test
    public void test1() throws Exception {
        packageName = getClass().getPackageName();
        String packagePath = packageName.replace(".", "/");
        File file = new File(ClassLoader.getSystemResource(packagePath).getPath());

        scan(file);
        System.out.println("singletonObjects = " + singletonObjects);
    }

    private void scan(File file) throws Exception {
        if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                scan(subFile);
            }
        } else {
            String replace = file.getPath().replace("\\", ".");
            String className = replace.substring(replace.indexOf(packageName)).replace(".class", "");
            Class<?> beanClass = Class.forName(className);
            Component component = beanClass.getDeclaredAnnotation(Component.class);
            if (component != null) {
                String key = "".equals(component.value()) ? file.getName().replace(".class", "") : component.value();
                singletonObjects.put(key, beanClass.newInstance());
            }
        }
    }
}
