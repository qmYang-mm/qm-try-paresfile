package com.quanmin.paresfile.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数量计算工具类
 *
 * @author zhangzhaoyang
 * @since 2020-08-31
 */
public class NumberUtils {

    /**
     * 根据shopId字符串计算机构下面分店数量
     *
     * @param shopIds 分店id 格式：,1,2,3,4,5,6,
     * @return 分店个数
     */
    public static Integer instShopNum(String shopIds) {
        if (StringUtils.isBlank(shopIds)) {
            return 0;
        }
        String[] strings = shopIds.split(",");
        return (strings.length - 1);
    }

    /**
     * 判断一个Integer类型是不是自然数，不为null并且大于零的为自然数。
     *
     * @param it 数字
     * @return 是不是自然数
     */
    public static boolean isNatural(Integer it) {
        return it != null && it > 0;
    }


    /**
     * 把字符串转换成list
     *
     * @param strings 格式: ,1,2,3,4,5,6,
     * @return list
     */
    public static List<String> transitionList(String strings) {
        String[] split = strings.substring(1).split(",");
        return Arrays.asList(split);
    }

    public static List<Integer> transitionListInt(String strings) {
        List<Integer> intList = new ArrayList<>();
        if (StrUtil.isEmpty(strings)) {
            return intList;
        }

        String[] split = strings.substring(1, strings.length() - 1).split(",");
        for (String it : split) {
            intList.add(Integer.valueOf(it));
        }

        return intList;
    }

    /**
     * 数字样式的字符串转化为数字
     *
     * @param num 可转化的字符串 "123"/""
     * @return 数字 123/0
     */
    public static int parseInt(String num) {
        if (StrUtil.isNotBlank(num)) {
            return Integer.parseInt(num);
        }
        return 0;
    }

}
