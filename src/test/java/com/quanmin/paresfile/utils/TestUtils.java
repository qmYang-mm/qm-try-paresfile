package com.quanmin.paresfile.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.Scanner;

public class TestUtils {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(tip + ": ");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        return "";
    }
}
