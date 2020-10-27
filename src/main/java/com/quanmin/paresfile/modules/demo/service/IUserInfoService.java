package com.quanmin.paresfile.modules.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanmin.paresfile.modules.demo.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-10-27
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 获得用户表中对应有的用户信息数据
     *
     * @return 用户信息数据集合
     */
    List<UserInfo> getUserInfoStatusList();
}
