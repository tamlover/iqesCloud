package com.advantech.iqescloud.service;

import com.advantech.iqescloud.entity.RabbitCarrier;
import com.advantech.iqescloud.entity.Restaurant;
import com.advantech.iqescloud.repository.RestaurantDao;
import com.advantech.iqescloud.utils.RabbitMqSendMessageUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShowService {

    @Autowired
    private RestaurantDao restaurantDao;

    public List<JSONObject> appHomePage() throws IOException, InterruptedException {

        RabbitCarrier rabbitCarrier=new RabbitCarrier();
        rabbitCarrier.setServiceName("APPShowService");
        rabbitCarrier.setMethodName("checkRestaurantInfoAndAllQueueInfo");


        List<JSONObject> restaurantList= new ArrayList<>();

        List<Restaurant> restaurants=restaurantDao.findAll();
        for (Restaurant r:restaurants){
            String restaurantID= String.valueOf(r.getId());
            JSONObject jsonObject= RabbitMqSendMessageUtils.sendMessage(rabbitCarrier, restaurantID);
            restaurantList.add(jsonObject);
        }
        return restaurantList;
    }

    public JSONObject getMenus(long restaurantID) throws IOException, InterruptedException {

        RabbitCarrier rabbitCarrier=new RabbitCarrier();
        rabbitCarrier.setServiceName("APPShowService");
        rabbitCarrier.setMethodName("getMenus");


        JSONObject jsonObject= RabbitMqSendMessageUtils.sendMessage(rabbitCarrier,String.valueOf(restaurantID));
        return jsonObject;
    }

    public JSONObject getMenusContainKinds(long restaurantID) throws IOException, InterruptedException {

        RabbitCarrier rabbitCarrier=new RabbitCarrier();
        rabbitCarrier.setServiceName("APPShowService");
        rabbitCarrier.setMethodName("getMenusContainKinds");


        JSONObject jsonObject= RabbitMqSendMessageUtils.sendMessage(rabbitCarrier,String.valueOf(restaurantID));
        return jsonObject;
    }
}
