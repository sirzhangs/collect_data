package com.haocang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.haocang.pojo.FlowActual;
import com.haocang.pojo.IntegrationStation;
import com.haocang.pojo.OpentsdbPutRequest;
import com.haocang.service.FlowActualService;
import com.haocang.service.IntegrationStationService;
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
public class IntegrationStationController {

    private static Logger logger = Logger.getLogger(IntegrationStationController.class);

    @Autowired
    private IntegrationStationService integrationStationService;

    public void sendData(Long pageSize){
        Integer startId = 0; //默认从0开始查询

        //获取数据库中数据总数
        Long total = integrationStationService.countTotalByTime();

        Long count = 1L;    //记录累计查询次数
        Long sum = 1L;      //累计执行

        while (((count-1)*pageSize)< total){
            List<IntegrationStation> integrationStationList = integrationStationService.findListByTime(startId,pageSize);

            //得到最后一个数据的Id
            IntegrationStation integrationStationLast = integrationStationList.get(integrationStationList.size()-1);
            startId = integrationStationLast.getId();

            //遍历数据并且进行数据发送
            for(IntegrationStation integrationStation:integrationStationList){
                /**公共属性**/
                String deviceId = integrationStation.getDeviceid();
                String cn = integrationStation.getCn();

                Date date = integrationStation.getRecdate();
                Long timestamp = date.getTime();//6

                HashMap<String,String> tags = new HashMap<>();
                tags.put("deviceId",deviceId);  // 1
                tags.put("cn",cn);

                /**公共属性**/
                Double  wT = FloatToDobuleUtil.parseDouble(integrationStation.getWt());
                Double  pH = FloatToDobuleUtil.parseDouble(integrationStation.getPh());
                Double  dO = FloatToDobuleUtil.parseDouble(integrationStation.getDo2());
                Double  tUB = FloatToDobuleUtil.parseDouble(integrationStation.getTub());
                Double  tP = FloatToDobuleUtil.parseDouble(integrationStation.getTp());
                Double  cOND = FloatToDobuleUtil.parseDouble(integrationStation.getCond());
                Double  nH3N = FloatToDobuleUtil.parseDouble(integrationStation.getNh3n());
                Double  kmon4 = FloatToDobuleUtil.parseDouble(integrationStation.getKmno4());
                Double  voltage = FloatToDobuleUtil.parseDouble(integrationStation.getVoltage());


                Map<String,Double> map = new HashMap<>();
                map.put("tP",tP);
                map.put("COD(Mn)",kmon4);
                map.put("wT",wT);
                map.put("pH",pH);
                map.put("dO",dO);
                map.put("tUB",tUB);
                map.put("cOND",cOND);
                map.put("nH3N",nH3N);
                map.put("voltage",voltage);
                List<OpentsdbPutRequest> resultList = TransformToOpenTSDBUtil.transform(map,timestamp,tags);
                HttpUtils.postForJSON("http://117.131.40.152:8088/api/put?details",JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                logger.info("【设备类型:集成站，总数："+total+",累计执行："+sum+"下次开始位置："+startId+"】："+JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                sum++;
            }
            count++;
        }
    }
}
