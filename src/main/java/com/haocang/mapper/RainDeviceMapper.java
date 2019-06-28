package com.haocang.mapper;

import com.haocang.pojo.BankStation;
import com.haocang.pojo.RainDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RainDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RainDevice record);

    int insertSelective(RainDevice record);

    RainDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RainDevice record);

    int updateByPrimaryKey(RainDevice record);

    /**
     *  根据Id和长度查询数据
     * @param id    查询起始位置ID
     * @param length    查询长度
     * @return
     */
    List<RainDevice> findList(@Param("startId")Integer id, @Param("pageSize")Long length);

    /**
     * 计算数据总数
     * @return
     */
    Long countTotal();
}