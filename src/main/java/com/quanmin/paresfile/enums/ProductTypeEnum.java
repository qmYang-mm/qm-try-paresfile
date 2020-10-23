package com.quanmin.paresfile.enums;

import com.quanmin.paresfile.common.INumberEnum;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ProductTypeEnum implements INumberEnum {
    UNSELECTED(0, "未选择"),
    MEDICAL_SERVICE(1, "就医服务"),
    HEALTH_SERVICE(2, "健康服务"),
    PHYSICAL_EXAM(3, "体检"),
    IMG_TEXT_CONSULT(4, "图文咨询"),
    VOICE_CONSULT(5, "语音咨询"),
    INSURANCE(6, "保险"),
    VIP_BENEFIT(7, "VIP权益"),
    PERSONAGE(8, "个人体检"),
    ORGANIZATION(9, "团体体检"),
    TELE_INQUIRY(10, "电话问诊"),
    OTHER(99, "其他");

    ProductTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    private final Integer value;
    private final String description;

    /**
     * @return 返回体检类型的枚举集合 TJ
     */
    public static List<ProductTypeEnum> getTJProductEnums(){
        List<ProductTypeEnum> TJ = new ArrayList<>();
        TJ.add(PERSONAGE);
        TJ.add(ORGANIZATION);
        TJ.add(PHYSICAL_EXAM);
        return TJ;
    }

    /**
     * @return 返回咨询类型的枚举集合 ZX
     */
    public static List<ProductTypeEnum> getZXProductEnums(){
        List<ProductTypeEnum> ZX = new ArrayList<>();
        ZX.add(IMG_TEXT_CONSULT);
        ZX.add(VOICE_CONSULT);
        return ZX;
    }

    /**
     * @return 返回健康类型的枚举集合 JK 现在就是除了体检和咨询其他的枚举
     */
    public static List<ProductTypeEnum> getJKProductEnums(){
        List<ProductTypeEnum> JK = new ArrayList<>();
        JK.add(UNSELECTED);
        JK.add(MEDICAL_SERVICE);
        JK.add(HEALTH_SERVICE);
        JK.add(INSURANCE);
        JK.add(VIP_BENEFIT);
        JK.add(TELE_INQUIRY);
        JK.add(OTHER);
        return JK;
    }

}
