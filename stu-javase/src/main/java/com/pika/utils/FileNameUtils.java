package com.pika.utils;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

/**
 * Desc:
 *
 * @author pikachu
 * @since 2023/2/17 22:43
 */
public class FileNameUtils {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("目标路径target: ");
        String target = scanner.nextLine();
        File file = new File(target);

        System.out.println("需要批量去除的文件名模式");
        String pattern = scanner.nextLine().trim();
        rename(file, pattern);
    }

    private static void rename(File file, String pattern) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            if (file.getName().contains(pattern)) {
                file = doRename(file, pattern);
            }
            if (file.getName().matches(".*(img|imgs|images|图片).*")) {
                return;
            }
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                rename(subFile, pattern);
            }
        }
        if (file.getName().matches(".*(ppt|md|pdf|word|pptx|xmind)$") && file.getName().contains(pattern)) {
            doRename(file, pattern);
        }
    }

    private static File doRename(File file, String pattern) {
        String originName = file.getName();
        String path = file.getAbsolutePath().replace(originName, "") + originName.replace(pattern, "");
        File dest = new File(path);
        boolean result = file.renameTo(dest);
        if (result) {
            System.out.println("文件: " + file.getName() + " -> " + dest.getName());
        } else {
            System.out.println("文件: " + file.getName() + " X " + dest.getName());
        }

        return dest;
    }
}
