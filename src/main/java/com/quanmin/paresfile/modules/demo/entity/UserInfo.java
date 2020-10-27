package com.quanmin.paresfile.modules.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("b_user_info")
@ApiModel(value="UserInfo对象", description="")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户信息的ID和用户表ID相等关联")
    private Integer infoId;

    @ApiModelProperty(value = "用户信息详情")
    private String infoDetail;

    @ApiModelProperty(value = "用户信息状态 1初始化，2进行中，3成功，4失败")
    private Integer infoStatus;


}
