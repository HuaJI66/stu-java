package markdown;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pikachu
 * @since 2023/5/7 15:06
 */
public class PoemUtils {
    private static final String outputPath = "D:\\project\\java\\stu-java\\stu-javase\\src\\main\\resources\\poem\\";
    private static final String inputPath = "D:\\project\\java\\stu-java\\stu-javase\\src\\main\\resources\\poem\\";
    private static final String urlString = "https://v1.jinrishici.com/rensheng.txt";
    private static final String outputFileNamePrefix = "poems";
    private static final String outputFileNameSuffix = ".json";
    private static final Random RANDOM = new Random();


    public static ConcurrentLinkedQueue<String> init() {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        try (FileInputStream in = new FileInputStream(inputPath + outputFileNamePrefix + outputFileNameSuffix)) {
            queue = JSONUtil.toBean(new String(in.readAllBytes()), new TypeReference<>() {
            }, true);
        } catch (Exception ignored) {
        }
        return queue;
    }

    @Test
    public void test1() throws Exception {
        ConcurrentLinkedQueue<String> queue = init();
        try (FileOutputStream out = new FileOutputStream(outputPath + outputFileNamePrefix + RANDOM.nextInt(10) + outputFileNameSuffix)) {
            AtomicInteger count = new AtomicInteger(0);

            int n = 100;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    String poem = HttpUtil.get(urlString);
                    if (count.get() >= n) {
                        timer.cancel();
                    }
                    if (!poem.contains("403") || !poem.contains("Error")) {
                        count.addAndGet(1);
                        System.out.println("poem = " + poem);
                        queue.add(poem);
                    }
                }
            }, 0, 300);

            while (count.get() != n) {
                Thread.sleep(1000 * 5);
            }
            System.out.println("finished: " + count.get());
            String jsonStr = JSONUtil.toJsonStr(queue);
            out.write(jsonStr.getBytes(StandardCharsets.UTF_8));
        }

    }


    @Test
    public void test3() throws Exception {
        Timer timer = new Timer();
        AtomicInteger count = new AtomicInteger(0);
        int n = 5;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (count.addAndGet(1) > n) {
                    timer.cancel();
                }
                System.out.println(new Date());
            }
        }, 0, 1000);
        while (count.get() != n) {
        }
        System.out.println("finished: " + count.get());
    }

    @Test
    public void test4() {
        ConcurrentLinkedQueue<String> queue = init();
        System.out.println("queue.remove() = " + queue.remove());
    }
}
