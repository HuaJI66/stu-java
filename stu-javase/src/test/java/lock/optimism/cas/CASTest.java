package lock.optimism.cas;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Desc:
 *
 * @since: 2022/10/17 23:17
 * @author: pikachu
 */
@Data
@AllArgsConstructor
class Book {
    private int id;
    private String name;
}

public class CASTest {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(6);
        int updated, expected;
        expected = integer.get();
        updated = expected + 3;
        System.out.println(integer.compareAndSet(6, 9) + "," + integer.get());
        //更新操作，原数据 +3操作
        while (!integer.compareAndSet(expected, updated)) {
            expected = integer.get();
            updated = expected + 3;
        }
        System.out.println("integer.get() = " + integer.get());

        AtomicReference<Book> atomicReference = new AtomicReference<>();
        Book book1 = new Book(10, "Book1");
        Book book2 = new Book(20, "Book2");

        atomicReference.set(book1);
        System.out.println(atomicReference.compareAndSet(book1, book2) + "," + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(book1, book2) + "," + atomicReference.get());


    }
}
