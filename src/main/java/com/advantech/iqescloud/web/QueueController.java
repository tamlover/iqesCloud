package com.advantech.iqescloud.web;

import com.advantech.iqescloud.entity.DTO.QueueInfoDTO;
import com.advantech.iqescloud.service.QueueService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huqili.tp
 * 排队相关restful controller
 */
@RestController
@RequestMapping(value = "/app/queue")
public class QueueController {

    @Autowired
    private QueueService queueService;


    @RequestMapping(value = "/virtualQueue", method = RequestMethod.POST)
    public String virtualQueue(@RequestBody QueueInfoDTO queueInfoDTO){
        System.out.println(queueInfoDTO);
        System.out.println("虚拟排队");
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("localResponse",queueService.virtualQueue(queueInfoDTO));
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","1");
            jsonObject.put("ErrorMessage",e.getMessage());
        }
        return  jsonObject.toJSONString();
    }

    @RequestMapping(value = "/virtualQueue", method = RequestMethod.PUT)
    public String update(@RequestBody QueueInfoDTO queueInfoDTO){
        System.out.println(queueInfoDTO);
        System.out.println("虚拟排队");
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("localResponse",queueService.updateVirtual(queueInfoDTO));
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","1");
            jsonObject.put("ErrorMessage",e.getMessage());
        }
        return  jsonObject.toJSONString();
    }

    @RequestMapping(value = "/confirmQueue", method = RequestMethod.PATCH)
    public String getTime(@RequestParam(value = "queueId", defaultValue = "0")long queueId,
                          @RequestParam(value = "tel", defaultValue = "")String tel,
                          @RequestParam(value = "restaurantId")long restaurantId,
                          @RequestParam(value = "userId")long userId){
        System.out.println("确认排队");
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("localResponse",queueService.confirmQueue(queueId,tel,restaurantId,userId));
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","1");
            jsonObject.put("ErrorMessage",e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/queueInfo/id", method = RequestMethod.DELETE)
    public String cancel(@RequestParam(value = "queueId", defaultValue = "0")long queueId,
                         @RequestParam(value = "restaurantId")long restaurantId){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("localResponse",queueService.cancleQueue(queueId,restaurantId));
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
            jsonObject.put("ErrorMessage","");
            jsonObject.put("Msg","取消失败");
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/personalQueueInfo", method = RequestMethod.GET)
    public String checkPersonalQueueInfo(@RequestParam(value = "queueNumber", defaultValue = "0")String queueNumber,
                                         @RequestParam(value = "restaurantId")long restaurantId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("localResponse",queueService.checkPersonQueue(queueNumber,restaurantId));
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        } catch (Exception e) {
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/allQueueInfo",method = RequestMethod.GET)
    public String checkAllQueueInfo(@RequestParam(value = "restaurantId")long restaurantId){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("localResponse",queueService.checkAllQueue(restaurantId));
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
