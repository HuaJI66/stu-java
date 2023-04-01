package com.pika.utils;

import cn.hutool.http.HttpUtil;
import jakarta.validation.constraints.NotNull;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/18 22:45
 */
public class MDUtils {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String mdPath, targetPath;
        String originPath, replacePath;
        String targetImg;

        System.out.println("原md文件路径: ");
        mdPath = scan.nextLine();
        System.out.println("输出md文件路径: ");
        targetPath = scan.nextLine();
        System.out.println("将关联图片复制到: ");
        targetImg = scan.nextLine();
        System.out.println("需要待替换的img路径: ");
        originPath = scan.nextLine();
        System.out.println("需要替换的img路径: ");
        replacePath = scan.nextLine();

//        mdPath = "D:\\tmp\\test";
//        originPath = "images/";
//        replacePath = "/assets/images/java/base/";
//        targetPath = "d:\\tmp\\test";
//        targetImg = "d:\\tmp\\test\\base";
        reBuildMd(new File(mdPath), targetPath, targetImg, originPath, replacePath);
    }

    private static void reBuildMd(@NotNull File mdFile, @NotNull String targetPath, @NotNull String targetImg, @NotNull String originPath, @NotNull String replacePath) throws IOException {
        if (!mdFile.exists()) {
            return;
        }
        if (mdFile.isDirectory()) {
            //复制images目录
            if (mdFile.getName().matches(".*(img|imgs|images|image|图片|pics|pic)$")) {
//                FileUtil.copy(mdFile.getAbsolutePath(), targetImg, true);
            }
            for (File subFile : Objects.requireNonNull(mdFile.listFiles())) {
                reBuildMd(subFile, targetPath, targetImg, originPath, replacePath);
            }
        }
        if (mdFile.getName().matches(".*md$")) {
            rebuilt(mdFile, targetPath, originPath, replacePath);
        }
    }

    private static void rebuilt(File mdFile, String targetPath, String originPath, String replace) throws IOException {
        long length = mdFile.length();
        BufferedInputStream oldFile = new BufferedInputStream(Files.newInputStream(mdFile.toPath()), Math.toIntExact(length));

        String targetFileName = "\\" + LocalDate.now() + "-" + mdFile.getName();
        BufferedOutputStream newFile = new BufferedOutputStream(Files.newOutputStream(Paths.get(targetPath + targetFileName)));
        byte[] bytes = new byte[(int) length];
        oldFile.read(bytes);
        String text = new String(bytes);
        text = text.replace(originPath, replace)
                .replace("讲师：尚硅谷-宋红康（江湖人称：康师傅）", "")
                .replace("官网：[http://www.atguigu.com](http://www.atguigu.com/)", "")
                .replace("讲师：尚硅谷-宋红康", "")
                .replace("网址：www.atguigu.com", "")
                .replace("author：尚硅谷-宋红康", "");
        //插入头部描述
        String title = "" + mdFile.getName().trim().replace(".md", "");
        String subtitle = "Error";
        while (subtitle.contains("Error") || subtitle.contains("403")) {
            subtitle = HttpUtil.get("https://v1.jinrishici.com/rensheng.txt");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String categories = "cs-notes";
        String tags = "cs-notes";
        String header = "---\n" +
                "layout: post\n" +
                "title: " + title + "\n" +
                "subtitle: " + subtitle + "\n" +
                "categories: " + categories + "\n" +
                "tags: [" + tags + "]\n" +
                "banner:\n" +
                "  video: null             # Video banner source\n" +
                "  loop: true              # Video loop\n" +
                "  volume: 0               # Video volume (100% is 1.0)\n" +
                "  start_at: 0             # Video start time\n" +
                "  image: /assets/images/banners/spy2.jpg    # Image banner source\n" +
                "---";
        newFile.write(header.getBytes(StandardCharsets.UTF_8));
        //添加转义
        newFile.write("\n{% raw %}\n".getBytes(StandardCharsets.UTF_8));
        newFile.write(text.getBytes(StandardCharsets.UTF_8));
        newFile.write("\n{% endraw %}".getBytes(StandardCharsets.UTF_8));
        oldFile.close();
        newFile.close();
    }

}
