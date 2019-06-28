package com.haocang.service.impl;

import com.haocang.mapper.IntegrationStationMapper;
import com.haocang.pojo.IntegrationStation;
import com.haocang.service.IntegrationStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegrationStationServiceImpl implements IntegrationStationService {

    @Autowired
    private IntegrationStationMapper integrationStationMapper;

    @Override
    public List<IntegrationStation> findList(Integer id, Long length) {
        return integrationStationMapper.findList(id, length);
    }

    @Override
    public Long countTotal() {
        return integrationStationMapper.countTotal();
    }

    @Override
    public List<IntegrationStation> findListByTime(Integer id, Long length) {
        return integrationStationMapper.findListByTime(id, length);
    }

    @Override
    public Long countTotalByTime() {
        return integrationStationMapper.countTotalByTime();
    }
}
