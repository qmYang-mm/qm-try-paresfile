package com.quanmin.paresfile.modules.common.controller;


import com.quanmin.paresfile.common.api.ApiController;
import com.quanmin.paresfile.common.api.Result;
import com.quanmin.paresfile.modules.common.entity.Area;
import com.quanmin.paresfile.modules.common.service.IAreaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 区域表 前端控制器
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/areas")
@Api(tags = "区域管理")
public class AreaController extends ApiController {

    @Autowired
    private IAreaService areaService;

    @GetMapping("{areaId}")
    public Result<Area> getById(@PathVariable Integer areaId){
        return success(areaService.getById(areaId));
    }
}
