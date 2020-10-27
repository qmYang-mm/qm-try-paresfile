package com.quanmin.paresfile.modules.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quanmin.paresfile.modules.demo.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-10-27
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {


    /**
     * 获得用户表中对应有的用户信息数据
     *
     * @return 用户信息数据集合
     */
    List<UserInfo> getUserInfoStatusList();
}
