package com.haocang.mapper;

import com.haocang.pojo.ExcessiveRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shenke on 2018/12/10.
 */
public interface ExcessiveRuleMapper {

    /**
     * 查询设备指标上下限&默认值
     * @param equipmentId 设备id
     * @param indicatorsName 指标名称
     * @return
     */
    ExcessiveRule selectExcessive(@Param("equipmentId") String equipmentId, @Param("indicatorsName") String indicatorsName);

    List<ExcessiveRule> findAll();
}
