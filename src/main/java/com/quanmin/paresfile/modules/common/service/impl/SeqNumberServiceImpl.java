package com.quanmin.paresfile.modules.common.service.impl;

import cn.hutool.core.util.StrUtil;

import com.quanmin.paresfile.enums.ProductTypeEnum;
import com.quanmin.paresfile.modules.common.mapper.SeqNumberMapper;
import com.quanmin.paresfile.modules.common.service.ISeqNumberService;
import com.quanmin.paresfile.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SeqNumberServiceImpl implements ISeqNumberService {

    @Value("${spring.profiles.active:dev}")
    private String env;// 当前激活的配置文件


    @Autowired
    private SeqNumberMapper mapper;

    /**
     * @param prefix  前缀
     * @param field   字段名
     * @param table   表的名称
     * @param isDaily 是否是按天来算流水号，如果为否，则为累计流水号
     * @return 最新的编码
     */
    private String getSeqNumber(String prefix, String field, String table, boolean isDaily, int fixWidth) {
        String dateStr = DateUtils.getDateStr(DateUtils.fmt5);
        String condition = prefix + "%";

        // 如果是单日流水号，则加上日期的字符串
        if (isDaily) {
            condition = prefix + dateStr + "%";
        }

        // 获得最大编码
        String code = mapper.selectMaxCode(field, table, condition);

        // 根据code获取到流水号
        int maxSeqNum = 0;
        int preLength = prefix.length() + dateStr.length();
        if (!StrUtil.isEmpty(code) && code.length() > preLength) {
            maxSeqNum = Integer.parseInt(code.substring(preLength));
        }

        // 生成序列号码
        maxSeqNum++;
        String seqNum = String.valueOf(maxSeqNum);
        if (fixWidth > 0) {
            seqNum = StrUtil.fill(seqNum, '0', fixWidth, true);
        }

        return prefix + dateStr + seqNum;
    }

    @Override
    public String getAppointmentSeqNumber() {
        return getSeqNumber("", "appt_code", "appt_appointment", true, 4);
    }

    @Override
    public String getOrderSeqNumber(ProductTypeEnum productType) {
        // 通过产品枚举类型决定编码头部，体检产品 TJ ，咨询产品 ZX ，健康产品 JK
        String prefix;

        //体检枚举集合
        List<ProductTypeEnum> tjProductEnums = ProductTypeEnum.getTJProductEnums();
        //咨询枚举集合
        List<ProductTypeEnum> zxProductEnums = ProductTypeEnum.getZXProductEnums();
        //健康枚举集合
        List<ProductTypeEnum> jkProductEnums = ProductTypeEnum.getJKProductEnums();

        if (tjProductEnums.contains(productType)) {
            prefix = PREFIX_TJ;
        } else if (zxProductEnums.contains(productType)) {
            prefix = PREFIX_ZX;
        } else if (jkProductEnums.contains(productType)) {
            prefix = PREFIX_JK;
        } else {
            return null;
        }

        // 测试环境的订单加"T"表示测试
        if (env.equals("dev")) {
            prefix = "T" + prefix;
        }

        return getSeqNumber(prefix, "order_code", "ord_order", true, 4);
    }

    @Override
    public String getRefundSeqNumber() {
        return getSeqNumber(PREFIX_TK, "refund_code", "ord_order_refund", true, 4);
    }

    @Override
    public String getInstitutionSeqNumber() {
        return getSeqNumber("", "inst_code", "phe_institution", false, 6);
    }

    @Override
    public String getCompanySeqNumber() {
        return null;
    }

    @Override
    public String getProductSeqNumber() {
        return getSeqNumber(PREFIX_TC, "prct_code", "prct_product", false, 4);
    }

    @Override
    public String getCheckItemSeqNumber() {
        return getSeqNumber(PREFIX_JCX, "item_code", "phe_checkitem", false, 4);
    }

    @Override
    public String getAttachProductSeqNumber() {
        return getSeqNumber(PREFIX_JXB, "prct_code", "prct_product", false, 4);
    }
}
