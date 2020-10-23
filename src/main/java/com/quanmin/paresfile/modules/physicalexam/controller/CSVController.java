package com.quanmin.paresfile.modules.physicalexam.controller;

import com.quanmin.paresfile.common.api.ApiController;
import com.quanmin.paresfile.common.api.Result;
import com.quanmin.paresfile.util.CSVUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("csv")
@Api("csv接口")
public class CSVController extends ApiController {

    @GetMapping("/download")
    @ApiOperation("下载CSV")
    public Result downloadAllUserRoleCSV(HttpServletResponse response) throws IOException {
        // 1. 设置CSV表头
        String[] head = {"aaa","bbb"};


        // 2. 设置CSV的值
        List<Object[]> values = new ArrayList<>();
        String[] test = {"aa","bb"};
        values.add(test);

        // 3. 设置文件名
        String fileName = "file";
        
        File file = CSVUtils.makeTempCSV(fileName, head, values);
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("multipart/form-data");
//        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName +".csv");
//        CSVUtils.downloadFile(response, file);
        return success();
    }

    @PostMapping(value = "/upload")
    @ApiOperation("上传CSV")
    public Result upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
        	//上传内容不能为空
            if (multipartFile.isEmpty()) {
                return failed("CSV file is empty!");
            }
            File file = CSVUtils.uploadFile(multipartFile);
            List<List<String>> userRoleLists = CSVUtils.readCSV(file.getPath(), 2);
            assert userRoleLists != null;
            log.info(userRoleLists.toString());
            file.delete();
            return success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failed();
    }
}
