package com.haocang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.haocang.pojo.BuoyStation;
import com.haocang.pojo.FlowActual;
import com.haocang.pojo.OpentsdbPutRequest;
import com.haocang.service.BuoyStationService;
import com.haocang.service.FlowActualService;
import com.haocang.utils.FloatToDobuleUtil;
import com.haocang.utils.HttpUtils;
import com.haocang.utils.TransformToOpenTSDBUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FlowActualController {

    private static Logger logger = Logger.getLogger(FlowActualController.class);

    @Autowired
    private FlowActualService flowActualService;

    public void sendData(Long pageSize){
        Integer startId = 0; //默认从0开始查询

        //获取数据库中数据总数
        Long total = flowActualService.countTotal();

        Long count = 1L;    //记录累计查询次数
        Long sum = 1L;      //累计执行

        while (((count-1)*pageSize)< total){
            List<FlowActual> flowActualList = flowActualService.findList(startId,pageSize);

            //得到最后一个数据的Id
            FlowActual flowActualLast = flowActualList.get(flowActualList.size()-1);
            startId = flowActualLast.getId();

            //遍历数据并且进行数据发送
            for(FlowActual flowActual:flowActualList){
                /**公共属性**/
                String deviceId = flowActual.getRtuid();

                Date date = flowActual.getColldatetime();
                Long timestamp = date.getTime();//6

                HashMap<String,String> tags = new HashMap<>();
                tags.put("deviceId",deviceId);  // 1

                /**公共属性**/
                Double  velocity = FloatToDobuleUtil.parseDouble(flowActual.getVelocity()); //2
                Double  flowRate = FloatToDobuleUtil.parseDouble(flowActual.getFlowrate());//3
                Double  voltage = FloatToDobuleUtil.parseDouble(flowActual.getVoltage());//4
                Double  waterLevel = FloatToDobuleUtil.parseDouble(flowActual.getWaterlevel());//5


                Map<String,Double> map = new HashMap<>();
                map.put("velocity",velocity);
                map.put("flowRate",flowRate);
                map.put("waterLevel",waterLevel);
                map.put("voltage",voltage);
                List<OpentsdbPutRequest> resultList = TransformToOpenTSDBUtil.transform(map,timestamp,tags);
                HttpUtils.postForJSON("http://117.131.40.152:8088/api/put?details",JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                logger.info("【设备类型:流量计，总数："+total+"，累计执行："+sum+"，下次开始位置："+startId+"】："+JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                sum++;
            }
            count++;
        }
    }
}
