package com.advantech.iqescloud.service;

import com.advantech.iqescloud.entity.DTO.QueueInfoDTO;
import com.advantech.iqescloud.entity.QueueInfo;
import com.advantech.iqescloud.entity.RabbitCarrier;
import com.advantech.iqescloud.repository.QueueInfoDao;
import com.advantech.iqescloud.repository.UserDao;
import com.advantech.iqescloud.utils.RabbitMqSendMessageUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QueueService {

    @Autowired
    private QueueInfoDao queueInfoDao;

    @Autowired
    private UserDao userDao;


    public JSONObject virtualQueue(QueueInfoDTO queueInfoDTO) {

        RabbitCarrier rabbitCarrier = new RabbitCarrier();
        rabbitCarrier.setServiceName("QueueService");
        rabbitCarrier.setMethodName("virtualQueue");
        rabbitCarrier.setParameter(JSONObject.toJSONString(queueInfoDTO.getQueueInfo()));

        String restaurantId = String.valueOf(queueInfoDTO.getRestaurantId());
        JSONObject jsonObject = RabbitMqSendMessageUtils.sendMessage(rabbitCarrier, restaurantId);
        return jsonObject;
    }


    public JSONObject updateVirtual(QueueInfoDTO queueInfoDTO) {

        RabbitCarrier rabbitCarrier = new RabbitCarrier();
        rabbitCarrier.setServiceName("QueueService");
        rabbitCarrier.setMethodName("updateVirtual");
        rabbitCarrier.setParameter(JSONObject.toJSONString(queueInfoDTO.getQueueInfo()));


        JSONObject jsonObject = RabbitMqSendMessageUtils.sendMessage(rabbitCarrier, String.valueOf(queueInfoDTO.getRestaurantId()));
        return jsonObject;
    }

    public JSONObject confirmQueue(long queueId, String tel, long restaurantID, long userId) {

        String userName = userDao.findOne(userId).getUniqueName();

        RabbitCarrier rabbitCarrier = new RabbitCarrier();
        rabbitCarrier.setServiceName("QueueService");
        rabbitCarrier.setMethodName("confirmQueue");

        JSONObject confirmJsonObject = new JSONObject();
        confirmJsonObject.put("queueId", queueId);
        confirmJsonObject.put("tel", tel);
        confirmJsonObject.put("userName", userName);

        rabbitCarrier.setParameter(JSONObject.toJSONString(confirmJsonObject));

        JSONObject jsonObject = RabbitMqSendMessageUtils.sendMessage(rabbitCarrier, String.valueOf(restaurantID));
        return jsonObject;
    }

    public JSONObject cancleQueue(long queueId, long restaurantID) {

        RabbitCarrier rabbitCarrier = new RabbitCarrier();
        rabbitCarrier.setServiceName("QueueService");
        rabbitCarrier.setMethodName("cancleQueue");

        JSONObject cancleJsonObject = new JSONObject();
        cancleJsonObject.put("queueId", queueId);

        rabbitCarrier.setParameter(JSONObject.toJSONString(cancleJsonObject));

        JSONObject jsonObject = RabbitMqSendMessageUtils.sendMessage(rabbitCarrier, String.valueOf(restaurantID));
        return jsonObject;
    }

    public JSONObject checkPersonQueue(String queueNumber, long restaurantID) {

        RabbitCarrier rabbitCarrier = new RabbitCarrier();
        rabbitCarrier.setServiceName("QueueService");
        rabbitCarrier.setMethodName("checkPersonQueue");

        JSONObject personQueueJsonObject = new JSONObject();
        personQueueJsonObject.put("queueNumber", queueNumber);

        rabbitCarrier.setParameter(JSONObject.toJSONString(personQueueJsonObject));

        JSONObject jsonObject = RabbitMqSendMessageUtils.sendMessage(rabbitCarrier, String.valueOf(restaurantID));
        return jsonObject;
    }

    public JSONObject checkAllQueue(long restaurantID) {

        RabbitCarrier rabbitCarrier = new RabbitCarrier();
        rabbitCarrier.setServiceName("QueueService");
        rabbitCarrier.setMethodName("checkAllQueue");

        JSONObject jsonObject = RabbitMqSendMessageUtils.sendMessage(rabbitCarrier, String.valueOf(restaurantID));
        return jsonObject;
    }

    public void saveQueueHistory(String jsonData) {

        System.out.println("save queueHistory..................");
        QueueInfo queueInfo = JSON.parseObject(jsonData, QueueInfo.class);
        System.out.println(queueInfo);
        queueInfoDao.save(queueInfo);
        System.out.println("save queueHistory once");
    }

}
