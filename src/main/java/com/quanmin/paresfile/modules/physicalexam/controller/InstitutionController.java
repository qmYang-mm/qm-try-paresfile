package com.quanmin.paresfile.modules.physicalexam.controller;


import cn.hutool.core.util.StrUtil;
import com.quanmin.paresfile.common.api.ApiController;
import com.quanmin.paresfile.common.api.Result;
import com.quanmin.paresfile.enums.InstTypeEnum;
import com.quanmin.paresfile.modules.common.service.ISeqNumberService;
import com.quanmin.paresfile.modules.physicalexam.entity.Institution;
import com.quanmin.paresfile.modules.physicalexam.entity.InstitutionBaseExport;
import com.quanmin.paresfile.modules.physicalexam.service.IInstitutionService;
import com.quanmin.paresfile.util.CSVUtils;
import com.quanmin.paresfile.util.DateUtils;
import com.quanmin.paresfile.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 机构表 前端控制器
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/institutions")
@Api(tags = "机构管理")
@Slf4j
public class InstitutionController extends ApiController {

    @Autowired
    private IInstitutionService institutionService;

    @Autowired
    private ISeqNumberService seqNumberService;

    @PostMapping("batch")
    @ApiOperation("批量导入机构")
    public Result batchExport(@RequestParam("file") MultipartFile file) throws Exception {
        // TODO:// 此接口只导入分店 转化机构类型，构建父级机构，转化地区，如果哪一行没有存储，进行标记

        // 1. 得到excel中的数据
        List<InstitutionBaseExport> institutionBaseExports = ExcelUtils.importExcel(file, 0, 1, InstitutionBaseExport.class);

        // 注意：数据库必填字段 inst_code inst_name inst_type inst_status is_disabled created_time created_by updated_time updated_by
        // 机构必填：类型 名称
        // 分店必填：父级机构 类型 名称 省市区 地区
        // TODO://instCode应该根据情况修改
        String institutionSeqNumber = seqNumberService.getInstitutionSeqNumber();
        String instCodeprefix = institutionSeqNumber.substring(0, 8);
        Integer instCodeSuffix = Integer.parseInt(institutionSeqNumber.substring(8));

        // 2. 构建自己机构数据
        List<Institution> institutionList = new ArrayList<>();
        for (InstitutionBaseExport baseExport : institutionBaseExports) {
            instCodeSuffix++;
            Institution institution = new Institution();

            // 插入省市区
            boolean success = institutionService.instSupportArea(institution, baseExport.getProvince(), baseExport.getCity(), baseExport.getCounty());

            if (!success) {
                // 说明插入数据有问题，标记行数
                baseExport.setErrorFlag("处理错误！请检查数据！");
                continue;
            }

            institution.setInstParent(baseExport.getInstParentId());
            institution.setInstType(InstTypeEnum.EXAM_BRANCH);

            institution.setInstAddress(baseExport.getInstAddress());
            institution.setInstCode(instCodeprefix + StrUtil.fill(instCodeSuffix.toString(), '0', 6, true));
            institution.setInstName(baseExport.getInstName());
            institution.setInstStatus(0);
            institution.setIsDisabled(0);
            // 插入基本的数据
            institutionService.instSupportBase(institution);

            // 非必要的 可添加
            institution.setInstOriginalCode(baseExport.getInstOriginalCode());
            institution.setInstIntroduction(baseExport.getInstIntroduction());
            institution.setInstAgreementName(baseExport.getInstAgreementName());
            institution.setInstCoopPeriodStart(baseExport.getInstCoopPeriodStart());
            institution.setInstCoopPeriodEnd(baseExport.getInstCoopPeriodEnd());
            institution.setInstCoopDescription(baseExport.getInstCoopDescription());
            institution.setInstPhone(baseExport.getInstPhone());
            institution.setInstEmail(baseExport.getInstEmail());
            institution.setRemark(baseExport.getRemark());
            institution.setGainReportTime(baseExport.getGainReportTime());
            institution.setGainReportWay(baseExport.getGainReportWay());
            institution.setViewReportUrl(baseExport.getViewReportUrl());

            institutionList.add(institution);
        }
        log.info("共计成功条数：{}/条", institutionList.size());

        // 3. 成功条数写成csv文件
        // 3.1 设置csv文件头
        String[] head = {"inst_code", "inst_original_code", "inst_name",
                "inst_type", "inst_parent", "inst_address",
                "province", "city", "county", "inst_status",
                "is_disabled", "created_time", "created_by",
                "updated_time", "updated_by"};
        // 3.2 设置csv文件体
        List<Object[]> body = new ArrayList<>();
        for (Institution institution : institutionList) {
            // 把导入对象转化为数组
            Object[] one = {institution.getInstCode(),
                    institution.getInstOriginalCode(),
                    institution.getInstName(),
                    institution.getInstType().getValue(),
                    institution.getInstParent(),
                    institution.getInstAddress(),
                    institution.getProvince(),
                    institution.getCity(),
                    institution.getCounty(),
                    institution.getInstStatus(),
                    institution.getIsDisabled(),
                    DateUtils.getDateStr(),
                    institution.getCreatedBy(),
                    DateUtils.getDateStr(),
                    institution.getUpdatedBy()};
            body.add(one);
        }

        // 3.3 设置文件名 并输出
        String fileName = "可导入分店";
        CSVUtils.makeTempCSV(fileName, head, body);

        // 4. 失败条数做标记
        String path = "标记没导入分店";
        ExcelUtils.exportExcelToFile(institutionBaseExports,path,InstitutionBaseExport.class);
        return success();
    }

}
