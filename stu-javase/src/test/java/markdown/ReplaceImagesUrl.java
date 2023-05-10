package file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;

/**
 * 替换md文件 images url （本地 -> OSS）
 *
 * @author pikachu
 * @since 2023/5/5 22:37
 */
public class ReplaceImagesUrl {
    private static final String outputPath = "D:\\project\\java\\stu-java\\stu-javase\\src\\main\\resources\\out\\";
    private static final String inputPath = "D:\\project\\java\\stu-java\\stu-javase\\src\\main\\resources\\in\\";
    private Random random = new Random();

    private static void extracted(String localUrl, String OSSUrl, File file) throws IOException {
        if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                extracted(localUrl, OSSUrl, subFile);
            }
        } else {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file), Math.toIntExact(file.length()));
            String outDir = outputPath + File.separator + file.getPath().replace(inputPath, "").replace(file.getName(), "") + File.separator;
            new File(outDir).mkdirs();
            File outputFile = new File(outDir + file.getName());

            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String text = new String(bytes);
            String replace = text.replace(
                    localUrl, OSSUrl + localUrl);
            outputStream.write(replace.getBytes(StandardCharsets.UTF_8));
            inputStream.close();
            file.deleteOnExit();
            outputStream.close();
        }
    }

    @org.junit.Test
    public void replaceUrl() throws Exception {
        String localUrl = "/assets/images";
        String OSSUrl = "https://blog-pika.oss-cn-hongkong.aliyuncs.com";
        File file = new File(inputPath);
        File outDir = new File(outputPath);
        if (!file.exists()) {
            return;
        }
        extracted(localUrl, OSSUrl, file);
    }
}
