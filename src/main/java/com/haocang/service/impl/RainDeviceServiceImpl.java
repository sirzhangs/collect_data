package com.haocang.service.impl;

import com.haocang.mapper.RainDeviceMapper;
import com.haocang.pojo.RainDevice;
import com.haocang.service.RainDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RainDeviceServiceImpl implements RainDeviceService {

    @Autowired
    private RainDeviceMapper rainDeviceMapper;

    @Override
    public List<RainDevice> findList(Integer id, Long length) {
        return rainDeviceMapper.findList(id, length);
    }

    @Override
    public Long countTotal() {
        return rainDeviceMapper.countTotal();
    }
}
