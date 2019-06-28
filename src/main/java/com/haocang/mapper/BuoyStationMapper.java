package com.haocang.mapper;

import com.haocang.pojo.BuoyStation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuoyStationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuoyStation record);

    int insertSelective(BuoyStation record);

    BuoyStation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuoyStation record);

    int updateByPrimaryKey(BuoyStation record);

    /**
     *  根据Id和长度查询数据
     * @param id    查询起始位置ID
     * @param length    查询长度
     * @return
     */
    List<BuoyStation> findList(@Param("startId")Integer id, @Param("pageSize")Long length);

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
    List<BuoyStation> findListByTime(@Param("startId")Integer id, @Param("pageSize")Long length);

    /**
     * 计算数据总数
     * @return
     */
    Long countTotalByTime();
}