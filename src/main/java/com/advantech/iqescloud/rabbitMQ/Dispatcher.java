package com.advantech.iqescloud.rabbitMQ;

import com.advantech.iqescloud.entity.RabbitCarrier;
import com.advantech.iqescloud.service.QueueService;
import com.advantech.iqescloud.service.RestaurantService;
import com.advantech.iqescloud.service.TestService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huqili.tp
 * dispatcher command
 */
@Component
public class Dispatcher {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private TestService testService;

    @Autowired
    private QueueService queueService;

    public void dispatcherCommmand(String jsonString){
        RabbitCarrier rabbitCarrie= JSONObject.parseObject(jsonString,RabbitCarrier.class);

        System.out.println(rabbitCarrie);
        String serviceName=rabbitCarrie.getServiceName();
        String methodName=rabbitCarrie.getMethodName();


        switch (serviceName){
            case "restaurantInfoService" :
                switch (methodName){
                    case "updateInfo":restaurantService.updateInfo(rabbitCarrie.getParameter());break;
                    default:
                        System.out.println("no-method-match");
                }break;
            case "TestService":
                switch (methodName){
                    case "uploadPhoto":testService.uploadPhoto(rabbitCarrie.getParameter());break;
                    default:
                        System.out.println("no-method-match");
                }
            case "QueueService":
                switch (methodName){
                    case "saveQueueHistoty":queueService.saveQueueHistory(rabbitCarrie.getParameter());break;
                    default:
                        System.out.println("no-method-match");
                }
            default:
                System.out.println("no-service-match");
        }
    }
}
