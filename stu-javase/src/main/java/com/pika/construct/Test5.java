package com.pika.construct;


/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/14 16:16
 */
public class Test5 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Apple apple = new Apple();
        Fruit fruit = new Fruit();
        Plate<? extends Fruit> plate = new Plate<>(fruit);
//        plate.set();
        Fruit fruit1 = plate.get();
        Plate<Apple> applePlate = new Plate<Apple>(apple);

        Plate<? super Fruit> plate1 = new Plate<>(fruit);
        plate1.get();
        plate1.set(apple);
        plate1.get();


    }
}

class Plate<T> {
    private T item;

    public Plate(T t) {

        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() throws InstantiationException, IllegalAccessException {
        System.out.println("t.getClass().getName() = " + item.getClass().getName());
        return item;
    }
}

class Fruit {

}

class Apple extends Fruit {

}

class Banana extends Fruit {

}
