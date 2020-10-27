package com.quanmin.paresfile.modules.demo.controller;


import com.quanmin.paresfile.common.api.ApiController;
import com.quanmin.paresfile.common.api.Result;
import com.quanmin.paresfile.modules.demo.entity.User;
import com.quanmin.paresfile.modules.demo.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-10-27
 */
@RestController
@RequestMapping("/demo/user")
public class UserController extends ApiController {
    
    @Autowired
    private IUserService userService;
    
    @GetMapping("{userId}")
    @ApiOperation("通过ID获得用户信息")
    public Result<User> getByUserID(@PathVariable Integer userId){
        return success(userService.getById(userId));
    }
    
}
