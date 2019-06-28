package com.haocang.mapper;

import com.haocang.pojo.WaterDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaterDeviceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WaterDevice record);

    int insertSelective(WaterDevice record);

    WaterDevice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WaterDevice record);

    int updateByPrimaryKey(WaterDevice record);

    /**
     *  根据Id和长度查询数据
     * @param id    查询起始位置ID
     * @param length    查询长度
     * @return
     */
    List<WaterDevice> findList(@Param("startId")Long id, @Param("pageSize")Long length);

    /**
     * 计算数据总数
     * @return
     */
    Long countTotal();
}