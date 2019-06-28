package com.haocang.mapper;

import com.haocang.pojo.BankStation;
import com.haocang.pojo.SSDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SSDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SSDevice record);

    int insertSelective(SSDevice record);

    SSDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SSDevice record);

    int updateByPrimaryKey(SSDevice record);

    /**
     *  根据Id和长度查询数据
     * @param id    查询起始位置ID
     * @param length    查询长度
     * @return
     */
    List<SSDevice> findList(@Param("startId")Integer id, @Param("pageSize")Long length);

    /**
     * 计算数据总数
     * @return
     */
    Long countTotal();
}