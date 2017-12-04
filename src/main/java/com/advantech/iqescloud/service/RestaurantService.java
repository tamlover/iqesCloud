package com.advantech.iqescloud.service;

import com.advantech.iqescloud.entity.DTO.RestaurantDTO;
import com.advantech.iqescloud.entity.Restaurant;
import com.advantech.iqescloud.repository.RestaurantDao;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huqili.tp
 */
@Service
@Transactional
public class RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    public void updateInfo(String jsonMessage) {
        RestaurantDTO restaurantDTO = JSON.parseObject(jsonMessage, RestaurantDTO.class);
        String message;
        Restaurant restaurant;
        if (restaurantDTO.getCloudId() == null) {
            message = "cloudId is empty";
        } else {
            restaurant = restaurantDao.findOne(restaurantDTO.getCloudId());
            if (restaurant == null) {
                message = "The restaurant is non-existent";
            } else {
                restaurantDao.save(new Restaurant(restaurantDTO));
                message = "update successful";
            }
        }
        System.out.println(message);
    }
}
