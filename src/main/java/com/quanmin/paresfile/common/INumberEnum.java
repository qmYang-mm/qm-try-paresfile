package com.quanmin.paresfile.common;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author liwenbo
 * 通用字典枚举
 */
@JsonDeserialize(using = NumberEnumDeserializer.class)
public interface INumberEnum extends IEnum<Integer> {

    /**
     * 数据库中存储的值
     *
     * @return 数据库中存储的值
     */
    @JsonValue
    @Override
    Integer getValue();


    /**
     * 描述信息，例如 "男”，“女”；  “申请中”，“已拒绝” 这样的描述信息。
     *
     * @return 字典ID
     */
    String getDescription();
}
