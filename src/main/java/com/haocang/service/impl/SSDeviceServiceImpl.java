package com.haocang.service.impl;

import com.haocang.mapper.SSDeviceMapper;
import com.haocang.pojo.SSDevice;
import com.haocang.service.SSDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SSDeviceServiceImpl implements SSDeviceService {

    @Autowired
    private SSDeviceMapper ssDeviceMapper;

    @Override
    public List<SSDevice> findList(Integer id, Long length) {
        return ssDeviceMapper.findList(id, length);
    }

    @Override
    public Long countTotal() {
        return ssDeviceMapper.countTotal();
    }
}
