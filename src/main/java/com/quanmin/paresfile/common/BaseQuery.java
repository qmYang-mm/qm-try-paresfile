package com.quanmin.paresfile.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础查询类
 */

@Data
@ApiModel("基础查询类")
public class BaseQuery {

    @ApiModelProperty("关键字")
    String keyword = "";

    @ApiModelProperty("页号")
    Integer pageNo = 1;

    @ApiModelProperty("分页尺寸")
    Integer pageSize = 20;
}
