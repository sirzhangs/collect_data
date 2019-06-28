package com.haocang.utils;

import com.alibaba.fastjson.JSON;
import com.haocang.mapper.ExcessiveRuleMapper;
import com.haocang.pojo.ExcessiveRule;
import com.haocang.pojo.OpentsdbPutRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.util.*;

public class TransformToOpenTSDBUtil {

    private static Map<String,ExcessiveRule> excessiveRuleMap;

    static {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-mybatis.xml");
        ExcessiveRuleMapper excessiveRuleMapper = (ExcessiveRuleMapper) applicationContext.getBean("excessiveRuleMapper");
        List<ExcessiveRule> excessiveRule = excessiveRuleMapper.findAll();
        excessiveRuleMap = new HashMap<>();
        for(ExcessiveRule item:excessiveRule){
            String key = item.getEquipmentId()+"_"+item.getIndicatorsName();
            excessiveRuleMap.put(key,item);
        }
    }

    public static List<OpentsdbPutRequest> transform(Map<String,Double> map,Long timestamp,Map<String, String> tags){

        List<OpentsdbPutRequest> resultList = new ArrayList<>();

        if(!StringUtils.isEmpty(tags.get("cn"))){
            Integer cn = Integer.valueOf(tags.get("cn"));
            if(cn == 2061){
                return null;
            }
        }

        String deviceId = tags.get("deviceId");

        Set<String> keyList = map.keySet();
        for(String metric:keyList){
            Double value = map.get(metric);
            OpentsdbPutRequest opentsdbPutRequest = new OpentsdbPutRequest();

            if( StringUtils.isEmpty(metric) || value == null || timestamp == null){
                continue;
            }else{
                if(StringUtils.isEmpty(deviceId)){
                    continue;
                }else{
                    opentsdbPutRequest.setMetric(metric);
                    opentsdbPutRequest.setValue(value);
                    opentsdbPutRequest.setTimestamp(timestamp);
                    Map<String,String> newMap = new HashMap<>();
                    newMap.putAll(tags);
                    opentsdbPutRequest.setTags(newMap);
                    OpentsdbPutRequest disposeOpen = disposeData(opentsdbPutRequest);
                    resultList.add(disposeOpen);
                }
            }
        }
        return resultList;
    }

    public static OpentsdbPutRequest  disposeData(OpentsdbPutRequest opentsdbPutRequest){
        Map<String, String> tags = opentsdbPutRequest.getTags();
        String deviceId = tags.get("deviceId");
        String indicatorsName = opentsdbPutRequest.getMetric();
        Double indicatorsValue = opentsdbPutRequest.getValue();
        //获取指标映射
        ExcessiveRule excessiveRule = excessiveRuleMap.get(deviceId+"_"+toUpperCaseFirstOne(indicatorsName));
        if(excessiveRule == null && indicatorsName.equals("ss")){
            excessiveRule = excessiveRuleMap.get(deviceId+"_"+"SS");
        }
        if(excessiveRule == null && indicatorsName.equals("COD(Mn)")){
            excessiveRule = excessiveRuleMap.get(deviceId+"_"+"COD");
        }
        if(excessiveRule == null && indicatorsName.equals("COD(Mn)")){
            excessiveRule = excessiveRuleMap.get(deviceId+"_"+"KMNO4");
        }
        if(excessiveRule == null && indicatorsName.equals("wT")){
            excessiveRule = excessiveRuleMap.get(deviceId+"_"+"WT12");
        }

        if(indicatorsValue == null || indicatorsValue == 0D){
            tags.put("qualityTag", "empty");
        }else{
            if(excessiveRule == null){
                tags.put("qualityTag", "trusted");
            }else{
                Double indicatorsCeiling = excessiveRule.getIndicatorsCeiling(); // 上限值
                Double indicatorsLower = excessiveRule.getIndicatorsLower(); // 下限值
                Double indicatorsDefault = excessiveRule.getIndicatorsDefault(); // 默认值
//                System.out.println(JSON.toJSONString(excessiveRule));
                if(indicatorsValue > indicatorsCeiling || indicatorsValue <= indicatorsLower){
                    tags.put("qualityTag", "excessive");
                }else{
                    tags.put("qualityTag", "trusted");
                }
            }
        }
        System.out.println(JSON.toJSONString(opentsdbPutRequest));
        return opentsdbPutRequest;
    }

    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
