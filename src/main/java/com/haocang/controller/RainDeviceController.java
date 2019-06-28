package com.haocang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.haocang.pojo.BuoyStation;
import com.haocang.pojo.OpentsdbPutRequest;
import com.haocang.pojo.RainDevice;
import com.haocang.service.BuoyStationService;
import com.haocang.service.RainDeviceService;
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
public class RainDeviceController {

    private static Logger logger = Logger.getLogger(RainDeviceController.class);

    @Autowired
    private RainDeviceService rainDeviceService;

    public void sendData(Long pageSize){
        Integer startId = 0; //默认从0开始查询

        //获取数据库中数据总数
        Long total = rainDeviceService.countTotal();

        Long count = 1L;    //记录累计查询次数
        Long sum = 1L;      //累计执行

        while (((count-1)*pageSize)< total){
            List<RainDevice> rainDeviceList = rainDeviceService.findList(startId,pageSize);

            //得到最后一个数据的Id
            RainDevice rainDeviceLast = rainDeviceList.get(rainDeviceList.size()-1);
            startId = rainDeviceLast.getId();

            //遍历数据并且进行数据发送
            for(RainDevice rainDevice:rainDeviceList){
                /**公共属性**/
                String deviceId = rainDevice.getRtuid();
//                String cn = buoyStation.getCn();

                Date date = rainDevice.getRecdate();
                Long timestamp = date.getTime();

                HashMap<String,String> tags = new HashMap<>();
                tags.put("deviceId",deviceId);
//                tags.put("cn",cn);
                /**公共属性**/
                Double  min10Pre = FloatToDobuleUtil.parseDouble(rainDevice.getMin10pre());
                Double  sumPre = FloatToDobuleUtil.parseDouble(rainDevice.getSumpre());
                Double  voltage = FloatToDobuleUtil.parseDouble(rainDevice.getVoltage());

                Map<String,Double> map = new HashMap<>();
                map.put("sumPre",sumPre);
                map.put("min10Pre",min10Pre);
                map.put("voltage",voltage);

                List<OpentsdbPutRequest> resultList = TransformToOpenTSDBUtil.transform(map,timestamp,tags);
                HttpUtils.postForJSON("http://117.131.40.152:8088/api/put?details",JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                logger.info("【设备类型:雨量计，总数："+total+",累计执行："+sum+"下次开始位置："+startId+"】："+JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                sum++;
            }
            count++;
        }
    }
}
