package com.haocang;

import com.haocang.controller.*;
import com.haocang.mapper.ExcessiveRuleMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-mybatis.xml");
        BankStationController bankStationController = (BankStationController) applicationContext.getBean("bankStationController");
        BuoyStationController buoyStationController = (BuoyStationController) applicationContext.getBean("buoyStationController");
        FlowActualController flowActualController = (FlowActualController) applicationContext.getBean("flowActualController");
        IntegrationStationController integrationStationController = (IntegrationStationController) applicationContext.getBean("integrationStationController");
        RainDeviceController rainDeviceController = (RainDeviceController) applicationContext.getBean("rainDeviceController");
        SSDeviceController ssDeviceController = (SSDeviceController) applicationContext.getBean("ssDeviceController");
        WaterDeviceController waterDeviceController = (WaterDeviceController) applicationContext.getBean("waterDeviceController");

        /**基础配置开始**/
        Long pageSize = 1000L;  //默认每次查询1000条
        /**基础配置结束**/

        if(args.length > 1 && StringUtils.isEmpty(args[1])){
            pageSize = Long.valueOf(args[1]);
        }
        if(args.length > 0 && StringUtils.isEmpty(args[0])){
            bankStationController.sendData(pageSize);
            buoyStationController.sendData(pageSize);
            flowActualController.sendData(pageSize);
            integrationStationController.sendData(pageSize);
            rainDeviceController.sendData(pageSize);
            ssDeviceController.sendData(pageSize);
            waterDeviceController.sendData(pageSize);
            waterDeviceController.sendData(pageSize);
        }else{
            switch (Integer.valueOf(args[0])){
                case 1:
                    bankStationController.sendData(pageSize);
                    break;
                case 2:
                    buoyStationController.sendData(pageSize);
                    break;
                case 3:
                    flowActualController.sendData(pageSize);
                    break;
                case 4:
                    integrationStationController.sendData(pageSize);
                    break;
                case 5:
                    rainDeviceController.sendData(pageSize);
                    break;
                case 6:
                    ssDeviceController.sendData(pageSize);
                    break;
                case 7:
                    waterDeviceController.sendData(pageSize);
                    break;
                default:
                    System.out.println("请输入正确的命令");
            }
        }
    }
}
