package com.quanmin.paresfile.modules.demo.mapper;

import com.quanmin.paresfile.modules.demo.entity.UserInfo;
import com.quanmin.paresfile.modules.demo.entity.UserInfoCopy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-10-27
 */
public interface UserInfoCopyMapper extends BaseMapper<UserInfoCopy> {

    List<UserInfo> getUserInfoCopyStatusList(@Param("userCopyIds") List<Integer> userCopyIds);

    List<Integer> getHaveUserInfoCopy();
}
