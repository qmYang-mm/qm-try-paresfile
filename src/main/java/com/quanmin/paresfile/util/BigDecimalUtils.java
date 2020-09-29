package com.quanmin.paresfile.util;

import java.math.BigDecimal;

/**
 * @author Yangquanmin
 * @since 2020-09-07
 */
public class BigDecimalUtils {

    /**
     * 加法
     *
     * @param v1
     * @param v2
     * @return BigDecimal类型的结果
     */
    public static BigDecimal addBD(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    /**
     * 加法
     *
     * @param v1
     * @param v2
     * @return double类型的结果
     */
    public static double add(double v1, double v2) {
        return addBD(v1, v2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 减法
     *
     * @param v1
     * @param v2
     * @return BigDecimal类型的结果
     */
    public static BigDecimal subBD(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    /**
     * 减法
     *
     * @param v1
     * @param v2
     * @return double类型的结果
     */
    public static double sub(double v1, double v2) {
        return subBD(v1, v2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 乘法
     *
     * @param v1
     * @param v2
     * @return BigDecimal类型的结果
     */
    public static BigDecimal mulBD(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    /**
     * 乘法
     *
     * @param v1
     * @param v2
     * @return double类型的结果
     */
    public static double mul(double v1, double v2) {
        return mulBD(v1, v2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法
     *
     * @param v1
     * @param v2
     * @return BigDecimal类型的结果
     */
    public static BigDecimal divBD(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        //四舍五入,保留2位小数
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法
     *
     * @param v1
     * @param v2
     * @return double类型的结果
     */
    public static double div(double v1, double v2) {
        //四舍五入,保留2位小数
        return divBD(v1, v2).doubleValue();
    }

}