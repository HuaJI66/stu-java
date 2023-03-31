package com.pika.thread;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/14 11:10
 */
@Data
public class ThreadPoolTest {

    public static void main(String[] args) {
        float x = 3.14f;
        double y = 3.14;
        System.out.println("x = " + x);
        System.out.println("y = " + y);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20));
        MyFunctionalInterface myFunctionalInterface = () -> {
        };

        Consumer<Integer> consumer = (integer) -> {

        };
        Function<Stu, String> function = Stu::getUsername;
        String username = function.apply(new Stu());
        System.out.println("username = " + username);
        myFunctionalInterface.test3(new Consumer<List>() {
            @Override
            public void accept(List list) {

            }
        });
    }
}

@Data
class Stu {
    private Integer id = 1;
    private String username = "Test5";
}

@FunctionalInterface
interface MyFunctionalInterface {
    int i = 0;

    void test();

    default <T, R> R test2(Function<T, R> function, T t) {
        return function.apply(t);
    }

    default <T> void test3(Consumer<? super List> consumer) {
    }

    default <T, R> void test4(Consumer<? extends T> consumer) {
        Consumer<? extends T> c1;

    }
}
