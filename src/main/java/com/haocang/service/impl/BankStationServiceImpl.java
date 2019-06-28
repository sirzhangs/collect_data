package com.haocang.service.impl;

import com.haocang.mapper.BankStationMapper;
import com.haocang.pojo.BankStation;
import com.haocang.service.BankStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankStationServiceImpl implements BankStationService{

    @Autowired
    private BankStationMapper bankStationMapper;

    @Override
    public List<BankStation> findList(Integer id, Long length) {
        return bankStationMapper.findList(id,length);
    }

    @Override
    public Long countTotal() {
        return bankStationMapper.countTotal();
    }

    @Override
    public List<BankStation> findListByTime(Integer id, Long length) {
        return bankStationMapper.findListByTime(id,length);
    }

    @Override
    public Long countTotalByTime() {
        return bankStationMapper.countTotalByTime();
    }
}
