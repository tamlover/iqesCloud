package com.advantech.iqescloud.web;

import com.advantech.iqescloud.entity.DTO.UserDTO;
import com.advantech.iqescloud.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huqili.tp
 */
@RestController
@RequestMapping(value = "/app/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String userRegister(@RequestBody UserDTO userDTO){
        JSONObject jsonObject = new JSONObject();
        String msg;
        try{
            msg=userService.saveUser(userDTO);
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
            jsonObject.put("msg",msg);
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping( value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam("tel")String tel,@RequestParam("password")String password){
        JSONObject jsonObject = new JSONObject();
        UserDTO userDTO;
        try{
            if("".equals(tel)||"".equals(password)){
                throw new Exception("The tel or password cannot be empty!");
            }
            userDTO=userService.login(tel,password);
            jsonObject.put("user",userDTO);
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
    }
    @RequestMapping( value = "/feedback",method = RequestMethod.POST)
    public String feedback(@RequestParam("userId") long userId,@RequestParam(value = "context")String context){
        JSONObject jsonObject = new JSONObject();
        try{
            if("".equals(context)){
                throw new Exception("The context cannot be empty!");
            }
            userService.feedback(userId,context);
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

    @RequestMapping(value = "/order/userName",method = RequestMethod.GET)
    public String checkUserOrder(@RequestParam(value = "restaurantId")long restaurantId,
                                 @RequestParam(value = "userName") String userName){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("localResponse",userService.checkOrder(userName,restaurantId));
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

    @RequestMapping(value = "/order/userId",method = RequestMethod.GET)
    public String checkUserOrder(@RequestParam(value = "restaurantId")long restaurantId,
                                 @RequestParam(value = "userId") long userId){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("localResponse",userService.getUserOrder(userId,restaurantId));
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

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("aa","aa");
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
