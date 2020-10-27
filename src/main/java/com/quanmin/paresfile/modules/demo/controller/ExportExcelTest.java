package com.quanmin.paresfile.modules.demo.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.core.io.IoUtil;
import com.quanmin.paresfile.modules.demo.entity.PersonExportVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "easypoi")
public class ExportExcelTest {


         
      /**
       * 导出Excel 营业收入支出明细
       * @param response
       * @return
       */
      @GetMapping(value="exportBillDatailInfo")
      @ResponseBody
      public String exportBillDatailInfo(HttpServletRequest request, HttpServletResponse response){
            String companyCode = request.getParameter("companyCode");
            String flows = request.getParameter("flows");
            System.out.println("companyCode:"+companyCode);
            System.out.println("flows:"+flows);
            // 获取workbook对象
            // 单sheet或多sheet 只需要更改此处即可
            Workbook workbook = exportSheets(companyCode,flows) ;
            // 判断数据
            if(workbook == null) {
                return "fail";
            }
            // 设置excel的文件名称
            String excelName = "测试excel" ;
            // 重置响应对象
            response.reset();
            // 当前日期，用于导出文件名称
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = "["+excelName+"-"+sdf.format(new Date())+"]";
            // 指定下载的文件名--设置响应头
            response.setHeader("Content-Disposition", "attachment;filename=" +dateStr+".xls");
            response.setContentType("application/vnd.ms-excel;charset=GBK");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            // 写出数据输出流到页面
            try {
                OutputStream output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                FileOutputStream fileOutputStream = new FileOutputStream("");
                workbook.write(bufferedOutPut);
                bufferedOutPut.flush();
                bufferedOutPut.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "success";
     }
      
    
    /**
     * 多sheet导出
     * @return
     */
    public Workbook exportSheets(String companyCode,String flows){
       
        
        //将条件放入Map中
        Map<String,Object> conditionMap = new HashMap<String,Object>();
        
        conditionMap.put("companyCode", companyCode);
        conditionMap.put("flows", flows);
        conditionMap.put("start", 0);
        conditionMap.put("size", 100);
        
        //获得营业明细(含收支)信息
        List<PersonExportVo> list = new ArrayList<>();

        // 创建参数对象（用来设定excel得sheet得内容等信息）
        ExportParams params1 = new ExportParams() ;
        // 设置sheet得名称
        params1.setSheetName("营业收支明细"); ;

        // 创建sheet1使用得map
        Map<String,Object> dataMap1 = new HashMap<>();
        // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
        dataMap1.put("title",params1) ;
        // 模版导出对应得实体类型
        dataMap1.put("entity",PersonExportVo.class) ;
        // sheet中要填充得数据
        dataMap1.put("data",list) ;
 
        // 将sheet1和sheet2使用得map进行包装
        List<Map<String, Object>> sheetsList = new ArrayList<>() ;
        sheetsList.add(dataMap1);
        // 执行方法
        return ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF) ;
    }
    
}