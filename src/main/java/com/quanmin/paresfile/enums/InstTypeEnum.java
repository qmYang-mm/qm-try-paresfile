package com.quanmin.paresfile.enums;

import com.quanmin.paresfile.common.INumberEnum;
import lombok.Getter;

@Getter
public enum InstTypeEnum implements INumberEnum {
    UNSELECTED(0, "未选择"),
    PHYSICAL_EXAM_CENTER(1, "专业体检中心"),
    PUBLIC_HOSPITAL(2, "公立医院"),
    OTHER_HOSPITAL(3, "其他"),
    EXAM_BRANCH(4, "分店");

    InstTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    private final Integer value;
    private final String description;
}
