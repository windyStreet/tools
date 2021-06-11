package com.windy;

import java.io.File;
import java.util.Locale;

/**
 * @author windyStreet
 * @codetime 2021-05-27 17:50
 */


public class Main {
    public static void showFile(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isFile()) {
                    String name = file1.getName();

                    String oldFilePath = file1.getParent() + "\\" + name;
                    System.out.print(oldFilePath);

                    int lastIndexOf = name.lastIndexOf(".");
                    if (lastIndexOf<0){
                        System.out.println("文件无后缀名");
                        continue;
                    }
                    //获取文件的后缀名 .jpg
                    String suffix = name.substring(lastIndexOf);

                    String newName = name.replace(suffix, suffix.toLowerCase(Locale.ROOT));

                    String newFilePath = file1.getParent() + "\\" + newName;
                    System.out.println( "====>" + newFilePath);

                    file1.renameTo(new File(newFilePath));

                }
                if (file1.isDirectory()) {
                    showFile(file1);
                }
            }
        }
    }

    public static void main(String[] args) {
        String dirPath = "C:\\Users\\Administrator\\Desktop\\cqbxxh20210508\\upload";
        showFile(new File(dirPath));
    }
}
