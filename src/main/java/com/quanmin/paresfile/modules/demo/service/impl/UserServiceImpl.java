package com.quanmin.paresfile.modules.demo.service.impl;

import com.quanmin.paresfile.modules.demo.entity.User;
import com.quanmin.paresfile.modules.demo.mapper.UserMapper;
import com.quanmin.paresfile.modules.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-10-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
