package file;

import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pikachu
 * @since 2023/4/24 10:14
 */
public class Test {
    private Random random = new Random();

    @org.junit.Test
    public void test1() {
        System.out.println("Math.random(10) = " + new Random().nextInt(20));
    }

    @org.junit.Test
    public void resetCategories() throws Exception {
        File file = new File("D:\\project\\java\\stu-java\\stu-javase\\src\\main\\resources\\in");
        File outDir = new File("D:\\project\\java\\stu-java\\stu-javase\\src\\main\\resources\\out\\");
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(subFile.toPath(), StandardOpenOption.READ), Math.toIntExact(subFile.length()));
                BufferedInputStream inFile = new BufferedInputStream(inputStream, inputStream.available());
                BufferedOutputStream outFile = new BufferedOutputStream(Files.newOutputStream(Path.of(outDir.toPath() + "\\" + subFile.getName())));

                byte[] bytes = new byte[inputStream.available()];
                inFile.read(bytes);
                String text = new String(bytes);
                String replace = text
                        .replace("categories: java", "categories: Java")
                        .replace("image: /assets/images/banners/spy2.jpg",
                                "image: /assets/images/banners/bg" + random.nextInt(26) + ".jpg");
                outFile.write(replace.getBytes(StandardCharsets.UTF_8));
                inFile.close();
                subFile.deleteOnExit();
                outFile.close();
            }
        }
    }

    @org.junit.Test
    public void resetTags() throws Exception {
        File file = new File("D:\\project\\java\\stu-java\\stu-javase\\src\\main\\resources\\in");
        File outDir = new File("D:\\project\\java\\stu-java\\stu-javase\\src\\main\\resources\\out\\");
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                BufferedOutputStream outFile = new BufferedOutputStream(Files.newOutputStream(Path.of(outDir.toPath() + "\\" + subFile.getName())));
                String author = "cs-notes";
                String categories = "cs-notes";
                String tags = "算法，数据结构";
                String newText = updatePostYaml(Files.newInputStream(subFile.toPath(), StandardOpenOption.READ), author, categories, tags);
                outFile.write(newText.getBytes(StandardCharsets.UTF_8));
                subFile.deleteOnExit();
                outFile.close();
            }
        }
    }

    public String updatePostYaml(InputStream inputStream, String author, String categories, String tags) throws Exception {
        String text;
        try (BufferedInputStream in = new BufferedInputStream(inputStream, inputStream.available())) {
            byte[] bytes = new byte[inputStream.available()];
            String splitStr = "---\n";
            in.read(bytes);
            text = new String(bytes);
            String originalYamlStr = Arrays.stream(text.split(splitStr)).filter(s -> s.startsWith("layout")).toList().get(0);
            Yaml yaml = new Yaml();
            Map<String, Object> yamlMap = yaml.load(originalYamlStr);
            yamlMap.put("author", StringUtils.hasText(author) ? author : "HuaJi66");
            yamlMap.remove("banner");
            yamlMap.put("categories", categories.replace("，", ",").split(","));
            yamlMap.put("tags", tags.replace("，", ",").split(","));
            yamlMap.put("cover", "/assets/images/banners/bg" + random.nextInt(40) + ".jpg");
            return text.replace(originalYamlStr, yaml.dumpAsMap(yamlMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("发生错误");
    }

    @org.junit.Test
    public void test3() {
        String s = "---\n" +
                "layout: post\n" +
                "title: MySQL\n" +
                "subtitle: 却将万字平戎策，换得东家种树书。\n" +
                "categories: MySQL\n" +
                "tags: [cs-notes]\n" +
                "banner:\n" +
                "  video: null             # Video banner source\n" +
                "  loop: true              # Video loop\n" +
                "  volume: 0               # Video volume (100% is 1.0)\n" +
                "  start_at: 0             # Video start time\n" +
                "  image: /assets/images/banners/spy2.jpg    # Image banner source\n" +
                "---\n" +
                "{% raw %}\n" +
                "# MySQL\n" +
                "<!-- GFM-TOC -->";
//        String regex = "(^---)([\\s\\S]*)(---$)";
        String regex = "^---([\\w\\W]*)---$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        System.out.println("matcher.groupCount() = " + matcher.groupCount());

        String replaceFirst = s.replaceFirst(regex, "test");
        System.out.println("replaceFirst = " + replaceFirst);
    }
}
