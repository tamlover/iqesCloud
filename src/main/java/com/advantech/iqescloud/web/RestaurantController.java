package com.advantech.iqescloud.web;

import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantDetailInfoDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantSummaryInfoDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.SimpleRestaurantDTO;
import com.advantech.iqescloud.service.RestaurantService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huqili.tp
 */
@RequestMapping(value = "/restaurant")
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/summaryInfo",method = RequestMethod.GET)
    public String getSummaryInfoOfRestaurant(){
        JSONObject jsonObject = new JSONObject();
        List<RestaurantSummaryInfoDTO> restaurantSummaryInfoDTOS;
        try{
            restaurantSummaryInfoDTOS=restaurantService.getSummaryInfoOfRestaurant();
            jsonObject.put("restaurantsSummaryInfo",restaurantSummaryInfoDTOS);
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
            jsonObject.put("test","test");
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/detailedInfo/id",method = RequestMethod.GET)
    public String getDetailInfoOfRestaurant(@RequestParam(value = "id")long id){
        JSONObject jsonObject = new JSONObject();
        RestaurantDetailInfoDTO restaurantDetailInfoDTO;
        try{
            restaurantDetailInfoDTO=restaurantService.getDetailInfoOfRestaurant(id);
            jsonObject.put("restaurantDetailInfo",restaurantDetailInfoDTO);
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/simple",method = RequestMethod.GET)
    public String getSimpleRestaurant(){
        JSONObject jsonObject = new JSONObject();
        List<SimpleRestaurantDTO> simpleRestaurantList;
        try{
            simpleRestaurantList=restaurantService.getSimpleRestaurant();
            jsonObject.put("simpleRestaurantList",simpleRestaurantList);
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
