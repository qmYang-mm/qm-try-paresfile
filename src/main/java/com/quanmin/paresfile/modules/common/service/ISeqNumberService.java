package com.quanmin.paresfile.modules.common.service;


import com.quanmin.paresfile.enums.ProductTypeEnum;

/**
 * 顺序编号的生成接口
 */
public interface ISeqNumberService {

    String PREFIX_JXB = "JXB";  // 加项包
    String PREFIX_JCX = "JCX";  // 检查项
    String PREFIX_TC = "TC"; // 套餐
    String PREFIX_COMPANY = "B"; // 企业
    String PREFIX_TK = "TK"; // 退款

    String PREFIX_TJ = "TJ"; //订单服务类型首字母 体检
    String PREFIX_ZX = "ZX"; //订单服务类型首字母 咨询
    String PREFIX_JK = "JK"; //订单服务类型首字母 健康


    /**
     * 获得下一个预约订单编号
     * 规则：创建时间年月日 + 当天预约体检流水号
     *
     * @return 预约服务编号
     */
    String getAppointmentSeqNumber();

    /**
     * 获得订单的编号
     *
     * @param productType 产品类型
     * @return 订单编号
     */
    String getOrderSeqNumber(ProductTypeEnum productType);

    /**
     * 获得退款申请的编号
     *
     * @return 退款申请编号
     */
    String getRefundSeqNumber();

    /**
     * 获得机构编码
     *
     * @return 机构编码
     */
    String getInstitutionSeqNumber();

    /**
     * 获得企业编码
     *
     * @return 企业编码
     */
    String getCompanySeqNumber();

    /**
     * 获得套餐编码
     *
     * @return 套餐编码
     */
    String getProductSeqNumber();

    /**
     * 获得检查项编码
     *
     * @return 检查项编码
     */
    String getCheckItemSeqNumber();

    /**
     * 获得加项包编码
     *
     * @return 加项包编码
     */
    String getAttachProductSeqNumber();
}
