package com.haocang.mapper;

import com.haocang.pojo.FlowActual;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowActualMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FlowActual record);

    int insertSelective(FlowActual record);

    FlowActual selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FlowActual record);

    int updateByPrimaryKey(FlowActual record);

    /**
     *  根据Id和长度查询数据
     * @param id    查询起始位置ID
     * @param length    查询长度
     * @return
     */
    List<FlowActual> findList(@Param("startId")Integer id, @Param("pageSize")Long length);

    /**
     * 计算数据总数
     * @return
     */
    Long countTotal();
}