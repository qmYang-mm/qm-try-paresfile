package com.quanmin.paresfile.modules.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanmin.paresfile.common.exception.NormalException;
import com.quanmin.paresfile.modules.common.entity.Area;
import com.quanmin.paresfile.modules.common.mapper.AreaMapper;
import com.quanmin.paresfile.modules.common.service.IAreaService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域表 服务实现类
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-09-28
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {


    @Override
    @Cacheable(value = "local", key = "'areaNameAndParentId-' + #name + '-' + #parentId")
    public Area getAreaLikeName(String name, Integer parentId) {
        if (parentId == null || StrUtil.isBlank(name)) {
            throw new NormalException("参数错误！");
        }
        LambdaQueryWrapper<Area> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Area::getParentId, parentId)
                .like(Area::getAreaName, name);
        return this.getOne(wrapper);
    }
}
