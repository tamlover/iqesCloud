package com.advantech.iqescloud.utils;

import com.advantech.iqescloud.entity.RabbitCarrier;
import com.advantech.iqescloud.rabbitMQ.RPCClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @author huqili.tp
 * 发送消息的工具类
 */
public class RabbitMqSendMessageUtils {

    public static JSONObject sendMessage(RabbitCarrier rabbitCarrier, String restaurantId) {

        RPCClient rpcClient = null;
        String response;
        JSONObject jsonObject=null;

        String message= JSON.toJSONString(rabbitCarrier);

        /**
         * 区分不同店的队列
         */
        String  requestQueueName="restaurant_"+restaurantId+"_rpc_queue";
        System.out.println(requestQueueName);

        try {
            rpcClient = new RPCClient();
            System.out.println(" [x] Requesting"+rabbitCarrier);
            response = rpcClient.call(message,requestQueueName);
            jsonObject=JSONObject.parseObject(response);
            jsonObject.put("restaurantId",restaurantId);
            System.out.println(" [.] Got '" + response + "'");
        }
        catch  (Exception e) {
            e.printStackTrace();
        } finally {
            if (rpcClient!= null) {
                try {
                    rpcClient.close();
                }
                catch (IOException _ignore) {
                }
            }
            return jsonObject;
        }
    }
}
