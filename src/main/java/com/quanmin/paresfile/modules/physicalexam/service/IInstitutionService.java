package com.quanmin.paresfile.modules.physicalexam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanmin.paresfile.modules.physicalexam.entity.Institution;

/**
 * <p>
 * 机构表 服务类
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-09-28
 */
public interface IInstitutionService extends IService<Institution> {

    /**
     * 给机构添加 创建时间 创建人，修改时间 修改人
     *
     * @param inst 机构对象
     */
    void instSupportBase(Institution inst);

    /**
     * 传入省市区转化为对应的地区Id
     *
     * @param inst         机构对象
     * @param provinceName 省名称
     * @param cityName     城市名称
     * @param countyName   区域名称
     */
    boolean instSupportArea(Institution inst, String provinceName, String cityName, String countyName);

}
