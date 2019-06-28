package com.haocang.service.impl;

import com.haocang.mapper.WaterDeviceMapper;
import com.haocang.pojo.WaterDevice;
import com.haocang.service.WaterDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterDeviceServiceImpl implements WaterDeviceService {

    @Autowired
    private WaterDeviceMapper waterDeviceMapper;

    @Override
    public List<WaterDevice> findList(Long id, Long length) {
        return waterDeviceMapper.findList(id, length);
    }

    @Override
    public Long countTotal() {
        return waterDeviceMapper.countTotal();
    }
}
