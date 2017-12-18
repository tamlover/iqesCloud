package com.advantech.iqescloud.web;

import com.advantech.iqescloud.entity.DTO.ShopUserDTO;
import com.advantech.iqescloud.service.ShopUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private ShopUserService shopUserService;

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestBody ShopUserDTO shopUserDTO){
        JSONObject jsonObject = new JSONObject();
        try{
            shopUserService.loginValid(shopUserDTO);
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
