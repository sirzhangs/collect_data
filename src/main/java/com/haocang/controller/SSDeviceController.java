package com.haocang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.haocang.pojo.BuoyStation;
import com.haocang.pojo.OpentsdbPutRequest;
import com.haocang.pojo.SSDevice;
import com.haocang.service.SSDeviceService;
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

@Component("ssDeviceController")
public class SSDeviceController {

    private static Logger logger = Logger.getLogger(SSDeviceController.class);

    @Autowired
    private SSDeviceService ssDeviceService;

    public void sendData(Long pageSize){
        Integer startId = 0; //默认从0开始查询

        //获取数据库中数据总数
        Long total = ssDeviceService.countTotal();

        Long count = 1L;    //记录累计查询次数
        Long sum = 1L;      //累计执行

        while (((count-1)*pageSize)< total){
            List<SSDevice> ssDeviceList = ssDeviceService.findList(startId,pageSize);

            //得到最后一个数据的Id
            SSDevice ssDeviceLast = ssDeviceList.get(ssDeviceList.size()-1);
            startId = ssDeviceLast.getId();

            //遍历数据并且进行数据发送
            for(SSDevice ssDevice:ssDeviceList){
                /**公共属性**/
                String deviceId = ssDevice.getRtuid();
//                String cn = buoyStation.getCn();

                Date date = ssDevice.getRecdate();
                Long timestamp = date.getTime();

                HashMap<String,String> tags = new HashMap<>();
                tags.put("deviceId",deviceId);
//                tags.put("cn",cn);
                /**公共属性**/
                Double  ss = FloatToDobuleUtil.parseDouble(ssDevice.getSs());
                Double  voltage = FloatToDobuleUtil.parseDouble(ssDevice.getVoltage());

                Map<String,Double> map = new HashMap<>();
                map.put("ss",ss);
                map.put("voltage",voltage);

                List<OpentsdbPutRequest> resultList = TransformToOpenTSDBUtil.transform(map,timestamp,tags);
                HttpUtils.postForJSON("http://117.131.40.152:8088/api/put?details",JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                logger.info("【设备类型:SS计，总数："+total+"累计执行："+sum+"】："+JSON.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
                sum++;
            }
            count++;
        }
    }
}
