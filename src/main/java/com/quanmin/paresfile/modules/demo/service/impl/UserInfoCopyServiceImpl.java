package com.quanmin.paresfile.modules.demo.service.impl;

import com.quanmin.paresfile.modules.demo.entity.UserInfo;
import com.quanmin.paresfile.modules.demo.entity.UserInfoCopy;
import com.quanmin.paresfile.modules.demo.mapper.UserInfoCopyMapper;
import com.quanmin.paresfile.modules.demo.service.IUserInfoCopyService;
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
public class UserInfoCopyServiceImpl extends ServiceImpl<UserInfoCopyMapper, UserInfoCopy> implements IUserInfoCopyService {

    @Override
    public List<UserInfo> getUserInfoCopyStatusList() {
        // 得到有的 再去查询
        List<Integer> userCopyIds= getHaveUserInfoCopy();

        return baseMapper.getUserInfoCopyStatusList(userCopyIds);
    }

    @Override
    public List<Integer> getHaveUserInfoCopy() {
        return baseMapper.getHaveUserInfoCopy();
    }
}
