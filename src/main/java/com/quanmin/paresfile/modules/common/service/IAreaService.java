package com.quanmin.paresfile.modules.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanmin.paresfile.modules.common.entity.Area;

/**
 * <p>
 * 区域表 服务类
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-09-28
 */
public interface IAreaService extends IService<Area> {

    /**
     * 通过父级Id和名称获得对应的地区
     *
     * @param name     地区名称
     * @param parentId 父级ID
     * @return 地区
     */
    Area getAreaLikeName(String name, Integer parentId);
}
