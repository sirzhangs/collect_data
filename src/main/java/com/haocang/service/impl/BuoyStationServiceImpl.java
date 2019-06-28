package com.haocang.service.impl;

import com.haocang.mapper.BuoyStationMapper;
import com.haocang.pojo.BuoyStation;
import com.haocang.service.BuoyStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuoyStationServiceImpl implements BuoyStationService {

    @Autowired
    private BuoyStationMapper buoyStationMapper;

    @Override
    public List<BuoyStation> findList(Integer id, Long length) {
        return buoyStationMapper.findList(id,length);
    }

    @Override
    public Long countTotal() {
        return buoyStationMapper.countTotal();
    }

    @Override
    public List<BuoyStation> findListByTime(Integer id, Long length) {
        return buoyStationMapper.findListByTime(id,length);
    }

    @Override
    public Long countTotalByTime() {
        return buoyStationMapper.countTotalByTime();
    }
}
