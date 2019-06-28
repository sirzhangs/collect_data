package com.haocang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.haocang.pojo.BankStation;
import com.haocang.pojo.BuoyStation;
import com.haocang.pojo.OpentsdbPutRequest;
import com.haocang.service.BankStationService;
import com.haocang.service.BuoyStationService;
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
public class BuoyStationController {

    private static Logger logger = Logger.getLogger(BuoyStationController.class);

    @Autowired
    private BuoyStationService buoyStationService;

    public void sendData(Long pageSize){
        Integer startId = 0; //默认从0开始查询

        //获取数据库中数据总数
        Long total = buoyStationService.countTotalByTime();

        Long count = 1L;    //记录累计查询次数
        Long sum = 1L;      //累计执行

        while (((count-1)*pageSize)< total){
            List<BuoyStation> buoyStationList = buoyStationService.findListByTime(startId,pageSize);

            //得到最后一个数据的Id
            BuoyStation buoyStationLast = buoyStationList.get(buoyStationList.size()-1);
            startId = buoyStationLast.getId();

            //遍历数据并且进行数据发送
            for(BuoyStation buoyStation:buoyStationList){
                /**公共属性**/
                String deviceId = buoyStation.getDeviceid();
//                String cn = buoyStation.getCn();

                Date date = buoyStation.getRecdate();
                Long timestamp = date.getTime();

                HashMap<String,String> tags = new HashMap<>();
                tags.put("deviceId",deviceId);
//                tags.put("cn",cn);
                /**公共属性**/
                Double  wT = FloatToDobuleUtil.parseDouble(buoyStation.getWt());
                Double  pH = FloatToDobuleUtil.parseDouble(buoyStation.getPh());
                Double  dO = FloatToDobuleUtil.parseDouble(buoyStation.getDo2());
                Double  tUB = FloatToDobuleUtil.parseDouble(buoyStation.getTub());
                Double  cHL = FloatToDobuleUtil.parseDouble(buoyStation.getChl());
                Double  cOND = FloatToDobuleUtil.parseDouble(buoyStation.getCond());
                Double  nH3N = FloatToDobuleUtil.parseDouble(buoyStation.getNh3n());
                Double  bGA = FloatToDobuleUtil.parseDouble(buoyStation.getBga());
                Double  voltage = FloatToDobuleUtil.parseDouble(buoyStation.getVoltage());

                Map<String,Double> map = new HashMap<>();
                map.put("wT",wT);
                map.put("pH",pH);
                map.put("dO",dO);
                map.put("tUB",tUB);
                map.put("cHL",cHL);
                map.put("cOND",cOND);
                map.put("nH3N",nH3N);
                map.put("bGA",bGA);
                map.put("voltage",voltage);
                List<OpentsdbPutRequest> resultList = TransformToOpenTSDBUtil.transform(map,timestamp,tags);
                HttpUtils.postForJSON("http://117.131.40.152:8088/api/put?details",JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                logger.info("【设备类型:浮标站，总数："+total+"累计执行："+sum+",本次开始ID："+(startId-1)*pageSize+"】："+JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                sum++;
            }
            count++;
        }
    }
}
