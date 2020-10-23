package com.quanmin.paresfile.util;


import com.quanmin.paresfile.common.exception.NormalException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static final String fmt = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String fmt1 = "yyyy-MM-dd";
    public static final String fmt2 = "yyyyMMddHHmmss";
    public static final String fmt3 = "yyyy-MM";
    public static final String fmt4 = "yyyy-MM-dd HH:mm:ss";
    public static final String fmt5 = "yyyyMMdd";
    public static final String fmt6 = "MM月dd日";

    /**
     * string转LocalDateTime
     *
     * @param str
     * @return
     */
    public static LocalDateTime getLocalDateTime(String str, String timeFm) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFm);
        return LocalDateTime.parse(str, dateTimeFormatter);
    }

    /**
     * string转LocalDate
     *
     * @param str
     * @return
     */
    public static LocalDate getLocalDate(String str, String timeFm) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFm);
        return LocalDate.parse(str, dateTimeFormatter);
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static String getStrTime(LocalDateTime date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(fmt);
        return df.format(date);
    }

    public static String getStrDateTime(LocalDateTime date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(fmt2);
        return df.format(date);
    }

    public static String getStrDate(LocalDate date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(fmt1);
        return df.format(date);
    }

    public static String getNowDateStr() {
        return getStrDate(LocalDate.now());
    }

    public static LocalDate getLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fmt1);
        return LocalDate.parse(date, formatter);
    }

    public static Date strToDate(String time) {
        String date = time.replace("Z", " UTC");//是空格+UTC
        Date dateTime = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//格式化的表达式
        try {
            dateTime = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * LocalDateTime转化时间戳
     *
     * @param dateTime
     * @return
     */
    public static long localDateTimeToTimestamp(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static long localDateToTimestamp(LocalDate date) {
        return date.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    }

    /**
     * 计算某天 到当前天的天数差
     * 当前时间也计算 今天-今天 = 1（天）
     *
     * @param dateTime
     * @return
     */
    public static Long betweenDateAndNow(LocalDateTime dateTime) {
        return LocalDate.now().toEpochDay() - dateTime.toLocalDate().toEpochDay() + 1;
    }

    public static Long betweenDateAndNow(LocalDate dateTime) {
        return LocalDate.now().toEpochDay() - dateTime.toEpochDay() + 1;
    }

    /**
     * 得到时间格式字符串是星期几
     *
     * @param dayStr yyyy-MM-dd格式
     * @return 返回1, 2, 3, 4, 5, 6, 7
     */
    public static Integer getWeekDayFromStrDate(String dayStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fmt1);
        return LocalDate.parse(dayStr, formatter).getDayOfWeek().getValue();
    }

    /**
     * 输入字符串时间 和偏移时间
     * 输出不小于该时间的时间字符串
     *
     * @param str
     * @param ofSet
     * @return
     */
    public static String makeStrDateLess(String str, Integer ofSet) {
        LocalDate dateTime = getLocalDate(str);
        long in = localDateToTimestamp(dateTime);
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.plusDays(ofSet);
        long out = localDateToTimestamp(localDate);
        if (in <= out) {
            return getStrDate(localDate);
        }
        return str;
    }

    public static String makeStrDateMore(String str, Integer ofSet) {
        LocalDate dateTime = getLocalDate(str);
        long in = localDateToTimestamp(dateTime);
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.plusDays(ofSet);
        long out = localDateToTimestamp(localDate);
        if (in > out) {
            return getStrDate(localDate);
        }
        return str;
    }

    public static String getDateStr() {
        return getDateStr(fmt4);
    }

    public static String getDateStr(String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return df.format(LocalDateTime.now());
    }

    public static String getStrDateMonthDay(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fmt6);
        String str = formatter.format(date);
        if (date.getMonthValue() < 10) {
            return str.substring(1);
        }
        return str;
    }

    /**
     * 从字符串中获得日期
     *
     * @param strDate
     * @param dateFm
     * @return
     */
    public static LocalDate getStrDate(String strDate, String dateFm) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DateUtils.fmt1);
        if (DateUtils.fmt1.equals(dateFm)) {
            //yyyy-MM-dd
            return LocalDate.parse(strDate, df);
        }

        if (DateUtils.fmt3.equals(dateFm)) {
            //yyyy-MM 没有日期无法转化
            return LocalDate.parse(strDate + "-01", df);
        }
        return null;
    }

    /**
     * 输入1-7得到对应的中文星期转义
     *
     * @param week 1~7, 1 - 星期一， 2 - 星期二， ...
     * @return 星期的中文
     */
    public static String getWeekZH(Integer week) {
        if (null == week || week <= 0 || week >= 8) {
            return "";
        }

        String[] weekList = {"一", "二", "三", "四", "五", "六", "日"};
        return "星期" + weekList[week - 1];
    }


    /**
     * 输入一周的数字星期几 得到是否工作日 范围 1-7
     *
     * @param day 1-7
     * @return 是工作日：true 不是工作日：false 输入范围外的返回null
     */
    public static Boolean checkIsWorkingDay(int day) {
        if (day >= 1 && day <= 7) {
            return day <= 5;
        }
        throw new NormalException("星期索引超界！");
    }

    public static boolean overNow(String string) {
        LocalDateTime strDate = getLocalDateTime(string, fmt4);
        LocalDateTime now = LocalDateTime.now();
        return localDateTimeToTimestamp(strDate) >= localDateTimeToTimestamp(now);
    }
}
