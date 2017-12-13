package com.advantech.iqescloud.service;

import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantDetailInfoDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantSummaryInfoDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.SimpleRestaurantDTO;
import com.advantech.iqescloud.entity.Restaurant;
import com.advantech.iqescloud.repository.RestaurantDao;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public List<RestaurantSummaryInfoDTO> getSummaryInfoOfRestaurant() {
        List<Restaurant> restaurants = restaurantDao.findAll();
        List<RestaurantSummaryInfoDTO> restaurantSummaryInfoDTOS = new ArrayList<>();
        for (Restaurant r : restaurants) {
            RestaurantSummaryInfoDTO rs = new RestaurantSummaryInfoDTO();
            rs.setId(r.getId());
            rs.setBusinessHours(r.getOpenTime() + "—" + r.getEndTime());
            rs.setDetailAddress(r.getDetailAddress());
            rs.setName(r.getName());
            rs.setState("1");
            restaurantSummaryInfoDTOS.add(rs);
        }
        return restaurantSummaryInfoDTOS;
    }

    public RestaurantDetailInfoDTO getDetailInfoOfRestaurant(Long id) {
        Restaurant r = restaurantDao.findOne(id);

        RestaurantDetailInfoDTO rd = new RestaurantDetailInfoDTO();
        rd.setId(r.getId());
        rd.setName(r.getName());
        rd.setBusinessHours(r.getOpenTime() + "—" + r.getEndTime());
        rd.setTelephone(r.getTelephone());
        rd.setDetailAddress(r.getDetailAddress());

        return rd;
    }

    public List<SimpleRestaurantDTO> getSimpleRestaurant(){
        List<SimpleRestaurantDTO> simpleRestaurantList=new ArrayList<>();
        List<Restaurant> restaurants=restaurantDao.findAll();
        for (Restaurant r:restaurants){
            SimpleRestaurantDTO simpleRestaurant=new SimpleRestaurantDTO(r);
            simpleRestaurantList.add(simpleRestaurant);
        }
        return simpleRestaurantList;
    }
}
