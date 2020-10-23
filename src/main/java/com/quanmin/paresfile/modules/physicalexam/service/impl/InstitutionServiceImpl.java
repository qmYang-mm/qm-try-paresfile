package com.quanmin.paresfile.modules.physicalexam.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanmin.paresfile.modules.common.entity.Area;
import com.quanmin.paresfile.modules.common.service.IAreaService;
import com.quanmin.paresfile.modules.physicalexam.entity.Institution;
import com.quanmin.paresfile.modules.physicalexam.mapper.InstitutionMapper;
import com.quanmin.paresfile.modules.physicalexam.service.IInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 机构表 服务实现类
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-09-28
 */
@Service
public class InstitutionServiceImpl extends ServiceImpl<InstitutionMapper, Institution> implements IInstitutionService {

    @Autowired
    private IAreaService areaService;

    @Override
    public void instSupportBase(Institution inst) {
        LocalDateTime now = LocalDateTime.now();

        inst.setCreatedTime(now);
        inst.setCreatedBy("系统导入");
        inst.setUpdatedTime(now);
        inst.setUpdatedBy("系统导入");
    }

    @Override
    public boolean instSupportArea(Institution inst, String provinceName, String cityName, String countyName) {
        Area province = areaService.getAreaLikeName(provinceName, 0);
        if(ObjectUtil.isNotNull(province)){
            Area city = areaService.getAreaLikeName(cityName, province.getAreaId());
            if(ObjectUtil.isNotNull(city)){
                Area county = areaService.getAreaLikeName(countyName, city.getAreaId());
                if(ObjectUtil.isNotNull(county)){
                    inst.setProvince(province.getAreaId());
                    inst.setCity(city.getAreaId());
                    inst.setCounty(county.getAreaId());
                    return true;
                }
            }
        }
        return false;
    }
}
