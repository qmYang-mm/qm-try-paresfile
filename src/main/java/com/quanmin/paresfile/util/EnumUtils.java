package com.quanmin.paresfile.util;


import com.quanmin.paresfile.common.INumberEnum;

public class EnumUtils {

    public static <T extends INumberEnum> T getByCode(Integer code, Class<T> enumClass) {
        //通过反射取出Enum所有常量的属性值
        for (T item : enumClass.getEnumConstants()) {
            //利用code进行循环比较，获取对应的枚举
            if (code.equals(item.getValue())) {
                return item;
            }
        }
        return null;
    }
}