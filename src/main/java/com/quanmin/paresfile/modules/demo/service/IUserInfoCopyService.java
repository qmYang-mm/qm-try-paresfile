package com.quanmin.paresfile.modules.demo.service;

import com.quanmin.paresfile.modules.demo.entity.UserInfo;
import com.quanmin.paresfile.modules.demo.entity.UserInfoCopy;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-10-27
 */
public interface IUserInfoCopyService extends IService<UserInfoCopy> {

    /**
     * 获得用户表中对应有的用户信息数据
     *
     * @return 用户信息数据集合
     */
    List<UserInfo> getUserInfoCopyStatusList();

    List<Integer> getHaveUserInfoCopy();

}
