package com.advantech.iqescloud.web;

import com.advantech.iqescloud.service.ShowService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huqili.tp
 */
@RestController
@RequestMapping(value = "/app")
public class ShowController {

    @Autowired
    private ShowService showService;

    @RequestMapping(value = "/homePage",method = RequestMethod.GET)
    public String showHomePage(){
        JSONObject jsonObject = new JSONObject();

       List<JSONObject> restaurantList;

        try {
            restaurantList=showService.appHomePage();
            jsonObject.put("restaurantList",restaurantList);
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "0");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
        }
        return JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/menus",method = RequestMethod.GET)
    public String showMenus(@RequestParam(value = "restaurantId") long restaurantId){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("menus",showService.getMenus(restaurantId));
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "0");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
        }
        return JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/menus/kinds",method = RequestMethod.GET)
    public String showMenusByKinds(@RequestParam(value = "restaurantId") long restaurantId){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("response",showService.getMenusContainKinds(restaurantId));
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "0");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
        }
        return JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
    }
}
