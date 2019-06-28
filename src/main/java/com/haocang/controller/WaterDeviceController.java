package com.haocang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.haocang.pojo.OpentsdbPutRequest;
import com.haocang.pojo.WaterDevice;
import com.haocang.service.WaterDeviceService;
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
public class WaterDeviceController {

    private static Logger logger = Logger.getLogger(WaterDeviceController.class);

    @Autowired
    private WaterDeviceService waterDeviceService;

    public void sendData(Long pageSize){
        Long startId = 0L; //默认从0开始查询

        //获取数据库中数据总数
        Long total = waterDeviceService.countTotal();

        Long count = 1L;    //记录累计查询次数
        Long sum = 1L;      //累计发送个数

        while (((count-1)*pageSize)< total){
            List<WaterDevice> waterDeviceList = waterDeviceService.findList(startId,pageSize);

            //得到最后一个数据的Id
            WaterDevice waterDeviceLast = waterDeviceList.get(waterDeviceList.size()-1);
            startId = waterDeviceLast.getId();

            //遍历数据并且进行数据发送
            for(WaterDevice waterDevice:waterDeviceList){
                /**公共属性**/
                String deviceId = waterDevice.getDeviceid();
//                String cn = buoyStation.getCn();

                Date date = waterDevice.getRecdate();
                Long timestamp = date.getTime();

                HashMap<String,String> tags = new HashMap<>();
                tags.put("deviceId",deviceId);
//                tags.put("cn",cn);
                /**公共属性**/
                Double  pd = FloatToDobuleUtil.parseDouble(waterDevice.getPd());
                Double  hd = FloatToDobuleUtil.parseDouble(waterDevice.getHd());

                Map<String,Double> map = new HashMap<>();
                map.put("pd",pd);
                map.put("hd",hd);

                List<OpentsdbPutRequest> resultList = TransformToOpenTSDBUtil.transform(map,timestamp,tags);
                HttpUtils.postForJSON("http://117.131.40.152:8088/api/put?details",JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                logger.info("【设备类型:液位计，总数："+total+"累计执行："+sum+"】："+JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                sum++;
            }
            count++;
        }
    }
}
