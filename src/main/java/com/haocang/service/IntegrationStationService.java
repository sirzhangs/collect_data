package com.haocang.service;

import com.haocang.pojo.IntegrationStation;

import java.util.List;

public interface IntegrationStationService {
    /**
     *  根据ID和查询长度查询数据
     * @param id   查询起始数据ID
     * @param length    查询长度
     * @return
     */
    List<IntegrationStation> findList(Integer id, Long length);

    /**
     * 计算数据总数量
     * @return
     */
    Long countTotal();

    /**
     *  根据ID和查询长度查询数据
     * @param id   查询起始数据ID
     * @param length    查询长度
     * @return
     */
    List<IntegrationStation> findListByTime(Integer id, Long length);

    /**
     * 计算数据总数量
     * @return
     */
    Long countTotalByTime();
}
