package com.quanmin.paresfile.modules.physicalexam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 机构表
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("phe_institution")
@ApiModel(value="Institution对象", description="机构表")
public class Institution implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构ID")
    @TableId(value = "inst_id", type = IdType.AUTO)
    private Integer instId;

    @ApiModelProperty(value = "机构编码")
    private String instCode;

    @ApiModelProperty(value = "机构的原始代码，该代码用于对接，接对接方的机构代码。目前为JSON格式，keys:instId,instName,code")
    private String instOriginalCode;

    @ApiModelProperty(value = "机构名称")
    private String instName;

    @ApiModelProperty(value = "机构类型")
    private Integer instType;

    @ApiModelProperty(value = "分店的父机构ID")
    private Integer instParent;

    @ApiModelProperty(value = "机构地址")
    private String instAddress;

    @ApiModelProperty(value = "机构Logo")
    private String instLogo;

    @ApiModelProperty(value = "简介")
    private String instIntroduction;

    @ApiModelProperty(value = "协议名称")
    private String instAgreementName;

    @ApiModelProperty(value = "预约接口类型：枚举 0 - 不使用接口，1 - 瑞慈， 2- 天方达")
    private Integer apptInterfaceType;

    @ApiModelProperty(value = "省/直辖市的编码")
    private Integer province;

    @ApiModelProperty(value = "市（区）编码")
    private Integer city;

    @ApiModelProperty(value = "县（镇）的编码")
    private Integer county;

    @ApiModelProperty(value = "机构合作周期（开始）")
    private LocalDate instCoopPeriodStart;

    @ApiModelProperty(value = "机构合作周期（结束）")
    private LocalDate instCoopPeriodEnd;

    @ApiModelProperty(value = "机构合作描述")
    private String instCoopDescription;

    @ApiModelProperty(value = "机构电话")
    private String instPhone;

    @ApiModelProperty(value = "机构邮件地址")
    private String instEmail;

    @ApiModelProperty(value = "机构状态")
    private Integer instStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否冻结 0未冻结 1冻结")
    private Integer isDisabled;

    @ApiModelProperty(value = "到检时间（文字描述）")
    private String checkedTime;

    @ApiModelProperty(value = "获得报告时间（文字描述）")
    private String gainReportTime;

    @ApiModelProperty(value = "获得报告方式")
    private String gainReportWay;

    @ApiModelProperty(value = "查看报告网址")
    private String viewReportUrl;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "更新人")
    private String updatedBy;


}
