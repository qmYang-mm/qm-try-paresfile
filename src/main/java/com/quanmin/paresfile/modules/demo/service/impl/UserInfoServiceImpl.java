package com.quanmin.paresfile.modules.demo.service.impl;

import com.quanmin.paresfile.modules.demo.entity.UserInfo;
import com.quanmin.paresfile.modules.demo.mapper.UserInfoMapper;
import com.quanmin.paresfile.modules.demo.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-10-27
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Override
    public List<UserInfo> getUserInfoStatusList() {
        return baseMapper.getUserInfoStatusList();
    }
}
