package com.example.user.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
    // lay extension file
    // img.png => png
    public static String getExtesion(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }

        return fileName.substring(lastIndexOf + 1);
    }

    // kiem tra extension file co hop le khong
    public static boolean checkFileExtesion(String fileExtension) {
        List<String> checkFileExten = Arrays.asList("png", "jpg", "jpeg");

        return checkFileExten.contains(fileExtension.toLowerCase());
    }

}
