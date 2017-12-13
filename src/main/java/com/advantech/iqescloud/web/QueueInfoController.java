package com.advantech.iqescloud.web;

import com.advantech.iqescloud.entity.DTO.queueInfo.AverageQueueTimeDTO;
import com.advantech.iqescloud.entity.DTO.queueInfo.ChurnRateDTO;
import com.advantech.iqescloud.entity.DTO.queueInfo.QueueInfoOfRestaurantDTO;
import com.advantech.iqescloud.entity.DTO.queueInfo.TableTypePercentageDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantsChurnRateContrastDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantsQueueTimeContrastDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantsQueuesContrastDTO;
import com.advantech.iqescloud.service.QueueInfoService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/queueInfo")
public class QueueInfoController {

    @Autowired
    private QueueInfoService queueInfoService;

    @RequestMapping(value = "/oneRestaurant/table",method = RequestMethod.GET)
    public String getQueueInfosByRestaurantAndDate(@RequestParam(value = "restaurantId")long id,
                                                   @RequestParam(value = "date")String date){
        JSONObject jsonObject = new JSONObject();
        List<QueueInfoOfRestaurantDTO> queueInfoOfRestaurantDTOS;
        try{
            queueInfoOfRestaurantDTOS=queueInfoService.getQueueInfosByRestaurantAndDate(id,date);
            jsonObject.put("queueInfos",queueInfoOfRestaurantDTOS);
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

    @RequestMapping(value = "/oneRestaurant/chart/averageQueueTime",method = RequestMethod.GET)
    public String getAverageQueueTime(@RequestParam(value = "restaurantId")long id,
                                                   @RequestParam(value = "date")String date){
        JSONObject jsonObject = new JSONObject();
        List<AverageQueueTimeDTO> averageQueueTimeDTOList;
        try{
            averageQueueTimeDTOList=queueInfoService.getAverageQueueTime(id,date);
            jsonObject.put("averageQueueTime",averageQueueTimeDTOList);
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

    @RequestMapping(value = "/oneRestaurant/chart/tableTypePercentage",method = RequestMethod.GET)
    public String getTableTypePercentage(@RequestParam(value = "restaurantId")long id){
        JSONObject jsonObject = new JSONObject();
        List<TableTypePercentageDTO> tableTypePercentageDTOList;
        try{
            tableTypePercentageDTOList=queueInfoService.getTableTypePercentage(id);
            jsonObject.put("tableTypePercentageList",tableTypePercentageDTOList);
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

    @RequestMapping(value = "/oneRestaurant/chart/churnRate",method = RequestMethod.GET)
    public String getChurnRate(@RequestParam(value = "restaurantId")long id,@RequestParam(value = "date")String date){
        JSONObject jsonObject = new JSONObject();
        List<ChurnRateDTO> churnRateDTOList;
        try{
            churnRateDTOList=queueInfoService.getChurnRateDTO(id,date);
            jsonObject.put("churnRateList",churnRateDTOList);
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

    @RequestMapping(value = "/manyRestaurants/chart/averageQueueTime",method = RequestMethod.GET)
    public String getTableTypePercentage(@RequestParam(value = "restaurantIds")String restaurantIds,@RequestParam(value = "date")String date){
        JSONObject jsonObject = new JSONObject();
        List<RestaurantsQueueTimeContrastDTO> restaurantsQueueTimeContrastDTOList;

        /**
         * String convert to Long[]
         */
        String[] rids=restaurantIds.split(",");
        Long[] ids=new Long[rids.length];
        for (int i=0;i<rids.length;i++){
            ids[i]= Long.valueOf(rids[i]);
        }

        try{
            restaurantsQueueTimeContrastDTOList=queueInfoService.getRestaurantsQueueTimeContrast(ids,date);
            jsonObject.put("queueTimeContrast",restaurantsQueueTimeContrastDTOList);
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

    @RequestMapping(value = "/manyRestaurants/chart/churnRate",method = RequestMethod.GET)
    public String getChurnRateContrast(@RequestParam(value = "restaurantIds")String restaurantIds,@RequestParam(value = "date")String date){
        JSONObject jsonObject = new JSONObject();
        List<RestaurantsChurnRateContrastDTO> restaurantsChurnRateContrastDTOList;

        /**
         * String convert to Long[]
         */
        String[] rids=restaurantIds.split(",");
        Long[] ids=new Long[rids.length];
        for (int i=0;i<rids.length;i++){
            ids[i]= Long.valueOf(rids[i]);
        }
        try{
            restaurantsChurnRateContrastDTOList=queueInfoService.getRestaurantsChurnRateContrast(ids,date);
            jsonObject.put("churnRateContrast",restaurantsChurnRateContrastDTOList);
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

    @RequestMapping(value = "/manyRestaurants/chart/queues",method = RequestMethod.GET)
    public String getQueuesContrast(@RequestParam(value = "restaurantIds")String restaurantIds,@RequestParam(value = "date")String date){
        JSONObject jsonObject = new JSONObject();
        List<RestaurantsQueuesContrastDTO> restaurantsQueuesContrastDTOList;

        /**
         * String convert to Long[]
         */
        String[] rids=restaurantIds.split(",");
        Long[] ids=new Long[rids.length];
        for (int i=0;i<rids.length;i++){
            ids[i]= Long.valueOf(rids[i]);
        }

        try{
            restaurantsQueuesContrastDTOList=queueInfoService.getRestaurantsQueuesContrash(ids,date);
            jsonObject.put("queuesContrast",restaurantsQueuesContrastDTOList);
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
}
