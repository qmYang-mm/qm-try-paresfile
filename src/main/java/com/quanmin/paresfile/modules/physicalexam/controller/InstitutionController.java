package com.quanmin.paresfile.modules.physicalexam.controller;


import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.quanmin.paresfile.common.api.ApiController;
import com.quanmin.paresfile.common.api.Result;
import com.quanmin.paresfile.modules.physicalexam.entity.InstitutionBaseExport;
import com.quanmin.paresfile.modules.physicalexam.service.IInstitutionService;
import com.quanmin.paresfile.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *      机构表 前端控制器
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/institutions")
@Api(tags = "机构管理")
public class InstitutionController extends ApiController {

    @Autowired
    private IInstitutionService institutionService;

    @PostMapping("batch")
    @ApiOperation("批量导入机构")
    public Result batchExport(@RequestParam("file") MultipartFile file) throws Exception {
        List<InstitutionBaseExport> institutionBaseExports = ExcelUtils.importExcel(file,0,1, InstitutionBaseExport.class);
        return success(institutionBaseExports);
    }

}
