package com.haocang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.haocang.pojo.BankStation;
import com.haocang.pojo.OpentsdbPutRequest;
import com.haocang.service.BankStationService;
import com.haocang.utils.FloatToDobuleUtil;
import com.haocang.utils.HttpUtils;
import com.haocang.utils.TransformToOpenTSDBUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BankStationController {

    private static Logger logger = Logger.getLogger(BankStationController.class);

    @Autowired
    private BankStationService bankStationService;

    public void sendData(Long pageSize){
        Integer startId = 0; //默认从0开始查询

        //获取数据库中数据总数
        Long total = bankStationService.countTotalByTime();

        Long count = 1L;    //记录累计查询次数
        Long sum = 1L;      //累计执行

        while (((count-1)*pageSize)< total){
            List<BankStation> bankStationList = bankStationService.findListByTime(startId,pageSize);

            //得到最后一个数据的Id
            BankStation bankStationLast = bankStationList.get(bankStationList.size()-1);
            startId = bankStationLast.getId();

            //遍历数据并且进行数据发送
            for(BankStation bankStation:bankStationList){
                /**公共属性**/
                String deviceId = bankStation.getDeviceid();
                String cn = bankStation.getCn();

                Date date = bankStation.getRecdate();
                Long timestamp = date.getTime();

                HashMap<String,String> tags = new HashMap<>();
                tags.put("deviceId",deviceId);
                tags.put("cn",cn);
                /**公共属性**/
                Double  wT = FloatToDobuleUtil.parseDouble(bankStation.getWt());
                Double  pH = FloatToDobuleUtil.parseDouble(bankStation.getPh());
                Double  dO = FloatToDobuleUtil.parseDouble(bankStation.getDo2());
                Double  tUB = FloatToDobuleUtil.parseDouble(bankStation.getTub());
                Double  tP = FloatToDobuleUtil.parseDouble(bankStation.getTp());
                Double  cOND = FloatToDobuleUtil.parseDouble(bankStation.getCond());
                Double  nH3N = FloatToDobuleUtil.parseDouble(bankStation.getNh3n());
                Double  uvcod = FloatToDobuleUtil.parseDouble(bankStation.getUvcod());
                Double  voltage = FloatToDobuleUtil.parseDouble(bankStation.getVoltage());

                Map<String,Double> map = new HashMap<>();
                map.put("wT",wT);
                map.put("pH",pH);
                map.put("dO",dO);
                map.put("tUB",tUB);
                map.put("tP",tP);
                map.put("cOND",cOND);
                map.put("nH3N",nH3N);
                map.put("COD(Mn)",uvcod);
                map.put("voltage",voltage);
                List<OpentsdbPutRequest> resultList = TransformToOpenTSDBUtil.transform(map,timestamp,tags);
                HttpUtils.postForJSON("http://117.131.40.152:8088/api/put?details",JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                logger.info("【设备类型:岸边站，总数："+total+"累计执行："+sum+",下次开始ID："+startId+"】："+JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                sum++;
            }
            count++;
        }
    }
}
