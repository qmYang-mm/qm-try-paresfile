package com.quanmin.paresfile.modules.physicalexam.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 机构导入表
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-09-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InstitutionBaseExport implements IExcelModel, IExcelDataModel, Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String errorMsg;

    @Getter
    @Setter
    private Integer rowNum;


    @ExcelIgnore
    private Integer instId;

    @ExcelIgnore
    private String instCode;

    @Excel(name = "原始机构代码（如果有）")
    private String instOriginalCode;

    @Excel(name = "机构（分店名称）")
    private String instName;

    @ExcelIgnore
    private Integer instType;

    @ExcelIgnore
    private Integer instParent;

    @Excel(name = "省")
    private String province;

    @Excel(name = "市")
    private String city;

    @Excel(name = "县")
    private String county;

    @Excel(name = "地址")
    private String instAddress;

    @Excel(name = "简介")
    private String instIntroduction;

    @Excel(name = "协议名称")
    private String instAgreementName;

    @Excel(name = "合作周期（开始）", format = "yyyy/MM/dd")
    private LocalDate instCoopPeriodStart;

    @Excel(name = "合作周期（结束）")
    private LocalDate instCoopPeriodEnd;

    @Excel(name = "合作描述")
    private String instCoopDescription;

    @Excel(name = "机构电话")
    private String instPhone;

    @Excel(name = "邮箱")
    private String instEmail;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "取得报告时间")
    private String gainReportTime;

    @Excel(name = "取得报告方式")
    private String gainReportWay;

    @Excel(name = "查看报告地址URL")
    private String viewReportUrl;


}
