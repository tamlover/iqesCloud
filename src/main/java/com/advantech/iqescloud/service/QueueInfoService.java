package com.advantech.iqescloud.service;

import com.advantech.iqescloud.entity.DTO.queueInfo.AverageQueueTimeDTO;
import com.advantech.iqescloud.entity.DTO.queueInfo.ChurnRateDTO;
import com.advantech.iqescloud.entity.DTO.queueInfo.QueueInfoOfRestaurantDTO;
import com.advantech.iqescloud.entity.DTO.queueInfo.TableTypePercentageDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantsChurnRateContrastDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantsQueueTimeContrastDTO;
import com.advantech.iqescloud.entity.DTO.restaurant.RestaurantsQueuesContrastDTO;
import com.advantech.iqescloud.entity.POJO.TableTypeChurnRatePOJO;
import com.advantech.iqescloud.entity.POJO.TableTypeQueueTimePOJO;
import com.advantech.iqescloud.entity.QueueInfo;
import com.advantech.iqescloud.repository.QueueInfoDao;
import com.advantech.iqescloud.repository.RestaurantDao;
import com.advantech.iqescloud.utils.TimeFormatTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huqili.tp
 * about queueInfo
 */
@Service
@Transactional
public class QueueInfoService {

    @Autowired
    private QueueInfoDao queueInfoDao;

    @Autowired
    private RestaurantDao restaurantDao;

    public List<QueueInfoOfRestaurantDTO> getQueueInfosByRestaurantAndDate(Long restaurantId, String date) throws ParseException {
        List<QueueInfo> queueInfos = queueInfoDao.findByRestaurantIdAndQueueStartTimeLike(restaurantId, date + "%");
        List<QueueInfoOfRestaurantDTO> queueInfoOfRestaurantDTOS = new ArrayList<>();

        for (QueueInfo q : queueInfos) {
            QueueInfoOfRestaurantDTO qR = new QueueInfoOfRestaurantDTO();
            qR.setCustomerName(q.getCustomerName());
            qR.setEatNumber(q.getEatNumber());
            qR.setQueueEndTime(TimeFormatTool.getHoursAndMinutes(q.getQueueEndTime()));
            qR.setQueueStartTime(TimeFormatTool.getHoursAndMinutes(q.getQueueStartTime()));
            qR.setQueueTime(q.getQueueTime());
            qR.setSeatFlag(q.getSeatFlag());
            qR.setTableNumber(q.getTableNumber());
            qR.setTableTypeDescribe(q.getTableTypeDescribe());
            qR.setCustomerTel(q.getCustomerTel());

            queueInfoOfRestaurantDTOS.add(qR);
        }
        return queueInfoOfRestaurantDTOS;
    }

    public List<AverageQueueTimeDTO> getAverageQueueTime(Long restaurantId, String date) throws ParseException {
        List<AverageQueueTimeDTO> averageQueueTimeDTOList = new ArrayList<>();
        List<String> week = TimeFormatTool.getNextWeek(date);
        for (String day : week) {
            AverageQueueTimeDTO averageQueueTimeDTO = new AverageQueueTimeDTO();

            averageQueueTimeDTO.setDate(day);
            averageQueueTimeDTO.setTableTypeQueueTimePOJOList(getAverageQueueTimeByDay(restaurantId, day));

            averageQueueTimeDTOList.add(averageQueueTimeDTO);
        }
        return averageQueueTimeDTOList;
    }

    private List<TableTypeQueueTimePOJO> getAverageQueueTimeByDay(Long restaurantId, String date) {
        List<TableTypeQueueTimePOJO> tableTypeQueueTimePOJOS = new ArrayList<>();

        List<String> tableTypeDescribes = queueInfoDao.getTableTypeDescribe(restaurantId);
        for (String tableType : tableTypeDescribes) {
            TableTypeQueueTimePOJO tableTypeQueueTimePOJO = new TableTypeQueueTimePOJO();

            tableTypeQueueTimePOJO.setTableTypeDescribe(tableType);
            tableTypeQueueTimePOJO.setQueueTime(queueInfoDao.
                    getAverageOfQueueTimeByQueueStartTime(date + "%", tableType, restaurantId));

            tableTypeQueueTimePOJOS.add(tableTypeQueueTimePOJO);
        }
        return tableTypeQueueTimePOJOS;
    }

    public List<TableTypePercentageDTO> getTableTypePercentage(Long restaurantId) {
        List<TableTypePercentageDTO> tableTypePercentageDTOList = new ArrayList<>();

        List<String> tableTypeDescribes = queueInfoDao.getTableTypeDescribe(restaurantId);
        for (String tableType : tableTypeDescribes) {

            TableTypePercentageDTO tableTypePercentageDTO = new TableTypePercentageDTO();
            tableTypePercentageDTO.setTableTypeDescribe(tableType);
            tableTypePercentageDTO.setNumber(queueInfoDao.getCountsByTableTypeDescribe(tableType, restaurantId));

            tableTypePercentageDTOList.add(tableTypePercentageDTO);
        }
        return tableTypePercentageDTOList;
    }

    public List<ChurnRateDTO> getChurnRateDTO(Long restaurantId) throws ParseException {
        List<ChurnRateDTO> churnRateDTOList = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");

        List<String> tableTypeList = queueInfoDao.getTableTypeDescribe(restaurantId);

        for (int time = 5; time < 50; time += 5) {
            ChurnRateDTO churnRateDTO = new ChurnRateDTO();
            churnRateDTO.setQueueTime(time);

            List<TableTypeChurnRatePOJO> tableTypeChurnRateList = new ArrayList<>();
            for (String tableType : tableTypeList) {
                TableTypeChurnRatePOJO t = new TableTypeChurnRatePOJO();
                t.setTableTypeDescribe(tableType);

                int all = queueInfoDao.getByQueueTimeBetween(restaurantId, tableType, time - 5, time);
                int churn = queueInfoDao.getByQueueTimeBetweenAndQueueState(restaurantId, tableType, time - 5, time);

                float rate;

                if (all != 0) {
                    rate = Float.parseFloat(decimalFormat.format((float) churn / all));
                } else {
                    rate = 0;
                }
                t.setChurnRate(rate);
                tableTypeChurnRateList.add(t);
            }
            churnRateDTO.setTableTypeChurnRatePOJOList(tableTypeChurnRateList);
            churnRateDTOList.add(churnRateDTO);
        }
        return churnRateDTOList;
    }

    public List<RestaurantsQueueTimeContrastDTO> getRestaurantsQueueTimeContrast(Long[] restaurantIds, String date) {
        List<RestaurantsQueueTimeContrastDTO> restaurantsQueueTimeContrastDTOList = new ArrayList<>();
        for (int i = 0; i < restaurantIds.length; i++) {
            RestaurantsQueueTimeContrastDTO restaurantsQueueTime = new RestaurantsQueueTimeContrastDTO();
            restaurantsQueueTime.setRestaurantName(restaurantDao.findOne(restaurantIds[i]).getName());
            restaurantsQueueTime.setTableTypeQueueTimes(getAverageQueueTimeByDay(restaurantIds[i], date));

            restaurantsQueueTimeContrastDTOList.add(restaurantsQueueTime);
        }
        return restaurantsQueueTimeContrastDTOList;
    }

    public List<RestaurantsChurnRateContrastDTO> getRestaurantsChurnRateContrast(Long[] restaurantIds, String date) {
        List<RestaurantsChurnRateContrastDTO> restaurantsChurnRateContrastDTOList = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        for (int i = 0; i < restaurantIds.length; i++) {
            RestaurantsChurnRateContrastDTO restaurantsChurnRate = new RestaurantsChurnRateContrastDTO();
            restaurantsChurnRate.setRestaurantName(restaurantDao.findOne(restaurantIds[i]).getName());


            int all = queueInfoDao.getByQueueStartTime(date + "%", restaurantIds[i]);
            int churn = queueInfoDao.getByQueueStartTimeAndQueueState(date + "%", restaurantIds[i]);
            float rate = Float.parseFloat(decimalFormat.format((float) churn / all));

            restaurantsChurnRate.setRate(rate);

            restaurantsChurnRateContrastDTOList.add(restaurantsChurnRate);
        }
        return restaurantsChurnRateContrastDTOList;
    }

    public List<RestaurantsQueuesContrastDTO> getRestaurantsQueuesContrash(Long[] restaurantIds, String date) {
        List<RestaurantsQueuesContrastDTO> restaurantsQueuesContrastDTOList = new ArrayList<>();

        for (int i = 0; i < restaurantIds.length; i++) {
            RestaurantsQueuesContrastDTO restaurantsQueues = new RestaurantsQueuesContrastDTO();
            restaurantsQueues.setRestaurantName(restaurantDao.findOne(restaurantIds[i]).getName());
            restaurantsQueues.setQueueQuantity(queueInfoDao.getByQueueStartTime(date + "%", restaurantIds[i]));

            restaurantsQueuesContrastDTOList.add(restaurantsQueues);
        }
        return restaurantsQueuesContrastDTOList;
    }
}
