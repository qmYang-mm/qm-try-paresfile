package com.quanmin.paresfile.modules.common.mapper;

import org.apache.ibatis.annotations.Param;

public interface SeqNumberMapper {

    /**
     * 查询库中的最大编码
     *
     * @param field     字段名称
     * @param table     表的名称
     * @param condition 检索条件
     * @return 最大编码
     */
    String selectMaxCode(@Param("field") String field, @Param("table") String table, @Param("condition") String condition);
}
