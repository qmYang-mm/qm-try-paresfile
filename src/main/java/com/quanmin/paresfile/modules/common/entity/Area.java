package com.quanmin.paresfile.modules.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cmn_area")
@ApiModel(value="Area对象", description="区域表")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "area_id",type = IdType.AUTO)
    private Integer areaId;

    @ApiModelProperty(value = "城市（区域）名称")
    private String areaName;

    @ApiModelProperty(value = "父节点名称")
    private Integer parentId;

    @ApiModelProperty(value = "级别 0 - 国家  1 - 省  2 - 市 3 - 县 4 - 乡 5 - 村")
    private Integer level;

    @ApiModelProperty(value = "是否是热门城市, 0-否， 1-是")
    private Integer isHot;

    @ApiModelProperty(value = "是否禁用")
    private Integer isDisabled;

    @ApiModelProperty(value = "排序, 值越大越靠前")
    private Integer orderNum;

    @ApiModelProperty(value = "名称的第一个字拼音，用于拼音排序")
    private String pinyin;

    @ApiModelProperty(value = "名称拼音的完整")
    private String pinyinFull;

    @ApiModelProperty(value = "别名")
    private String aliasName;


}
