package com.haocang.service;

import com.haocang.pojo.RainDevice;

import java.util.List;

public interface RainDeviceService {
    /**
     *  根据ID和查询长度查询数据
     * @param id   查询起始数据ID
     * @param length    查询长度
     * @return
     */
    List<RainDevice> findList(Integer id, Long length);

    /**
     * 计算数据总数量
     * @return
     */
    Long countTotal();
}
