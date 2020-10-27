package com.quanmin.paresfile.modules.demo.controller;

import com.quanmin.paresfile.common.api.ApiController;
import com.quanmin.paresfile.common.api.Result;
import com.quanmin.paresfile.modules.demo.entity.PersonExportVo;
import com.quanmin.paresfile.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Put;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *      学习 EasyPoi 的控制层
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-10-23 17:41
 */
@RestController
@RequestMapping("easyPoi")
@Slf4j
public class EasyPoiController extends ApiController {


    /**
     * 导出
     *
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) throws IOException {
        long start = System.currentTimeMillis();
        List<PersonExportVo> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PersonExportVo personVo = new PersonExportVo();
            personVo.setName("张三" + i);
            personVo.setUsername("张三" + i);
            personVo.setPhoneNumber("18888888888");
            personVo.setImageUrl("");
            personList.add(personVo);
        }
        log.debug("导出excel所花时间：" + (System.currentTimeMillis() - start));
        ExcelUtils.exportExcel(personList, "员工信息表", "员工信息", PersonExportVo.class, "员工信息", response);
    }
}
