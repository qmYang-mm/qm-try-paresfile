package com.quanmin.paresfile.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String numberChar = "0123456789";

    public RandomUtil() {
    }

    public static String generateString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int len = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length();

        for (int i = 0; i < length; ++i) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt(len)));
        }

        return sb.toString();
    }

    public static String generateMixString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; ++i) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }

        return sb.toString();
    }

    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    public static String generateZeroString(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            sb.append('0');
        }

        return sb.toString();
    }

    public static String toFixedLengthString(int num, int fixedLength) {
        StringBuilder sb = new StringBuilder();
        String strNum = String.valueOf(num);
        if (fixedLength - strNum.length() >= 0) {
            sb.append(generateZeroString(fixedLength - strNum.length()));
            sb.append(strNum);
            return sb.toString();
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixedLength + "的字符串发生异常!");
        }
    }

    private static String toFixedLengthStringByUUID(int length) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").substring(0, length);
    }

}
