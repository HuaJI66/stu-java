package markdown;

import org.junit.platform.commons.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * @author pikachu
 * @since 2023/4/24 10:14
 */
public class PostYaml {
    private static final String cover = "https://blog-pika.oss-cn-hongkong.aliyuncs.com/assets/images/banners/bg";
    private static final Random random = new Random();
    private static final String TITLE = "title";
    private static final String SUBTITLE = "subtitle";
    private static final String CATEGORIES = "categories";
    private static final String AUTHOR = "author";
    private static final String COVER = "cover";
    private static final String TAGS = "tags";
    private static final String outputPath = "D:\\project\\java\\stu-java\\stu-javase\\src\\main\\resources\\out\\";
    private static final String inputPath = "D:\\project\\java\\stu-java\\stu-javase\\src\\main\\resources\\in\\";
    private static ConcurrentLinkedQueue<String> poemsQueue = PoemUtils.init();

    public static void updatePostYaml(File file, Map<String, Object> map) throws Exception {
        if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                updatePostYaml(subFile, map);
            }
        } else {
            String text;
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                text = new String(inputStream.readAllBytes());
                // 以第一次出现的 --- 作为切分标准
                String splitStr = Arrays.stream(text.split("\n")).filter(StringUtils::isNotBlank).toList().get(0) + "\n";
                String originalYamlStr = Arrays.stream(text.split(splitStr)).filter(s -> s.contains("layout")).toList().get(0);
                Yaml yaml = new Yaml();
                Map<String, Object> yamlMap = yaml.load(originalYamlStr);
                yamlMap.putAll(map);

                yamlMap.put("subtitle", poemsQueue.remove());
                yamlMap.remove("banner");
                yamlMap.put("cover", cover + random.nextInt(60) + ".jpg");

                String outDir = outputPath + File.separator + file.getPath().replace(inputPath, "").replace(file.getName(), "") + File.separator;
                new File(outDir).mkdirs();
                String pathname = outDir + file.getName();
                File outputFile = new File(pathname);
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

                String replace = text.replace(originalYamlStr, yaml.dumpAsMap(yamlMap));
                outputStream.write(replace.getBytes(StandardCharsets.UTF_8));
                inputStream.close();
//                file.deleteOnExit();
                outputStream.close();
                System.out.println("success: " + pathname);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @org.junit.Test
    public void updateYaml() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
//        map.put(AUTHOR, "cs-notes");
//        map.put(CATEGORIES, "cs-notes");
//        map.put(TAGS, "算法，数据结构");
        File file = new File(inputPath);
        updatePostYaml(file, map);
    }
}
