package com.haocang.service.impl;

import com.haocang.mapper.FlowActualMapper;
import com.haocang.pojo.FlowActual;
import com.haocang.service.FlowActualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlowActualServiceImpl implements FlowActualService {

    @Autowired
    private FlowActualMapper flowActualMapper;

    @Override
    public List<FlowActual> findList(Integer id, Long length) {
        return flowActualMapper.findList(id,length);
    }

    @Override
    public Long countTotal() {
        return flowActualMapper.countTotal();
    }
}
