package com.haocang.mapper;

import com.haocang.pojo.BankStation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankStationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankStation record);

    int insertSelective(BankStation record);

    BankStation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankStation record);

    int updateByPrimaryKey(BankStation record);

    /**
     *  根据Id和长度查询数据
     * @param id    查询起始位置ID
     * @param length    查询长度
     * @return
     */
    List<BankStation> findList(@Param("startId")Integer id,@Param("pageSize")Long length);

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
    List<BankStation> findListByTime(@Param("startId")Integer id,@Param("pageSize")Long length);

    /**
     * 计算数据总数
     * @return
     */
    Long countTotalByTime();
}