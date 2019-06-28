package com.haocang.mapper;

import com.haocang.pojo.IntegrationStation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegrationStationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntegrationStation record);

    int insertSelective(IntegrationStation record);

    IntegrationStation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntegrationStation record);

    int updateByPrimaryKey(IntegrationStation record);

    /**
     *  根据Id和长度查询数据
     * @param id    查询起始位置ID
     * @param length    查询长度
     * @return
     */
    List<IntegrationStation> findList(@Param("startId")Integer id, @Param("pageSize")Long length);

    /**
     * 计算数据总数
     * @return
     */
    Long countTotal();

    /**
     *  根据Id和长度查询数据
     * @param id    查询起始位置ID
     * @param length    查询长度
     * @return
     */
    List<IntegrationStation> findListByTime(@Param("startId")Integer id, @Param("pageSize")Long length);

    /**
     * 计算数据总数
     * @return
     */
    Long countTotalByTime();
}