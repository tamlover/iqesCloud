package com.advantech.iqescloud.entity.DTO.restaurant;

import com.advantech.iqescloud.entity.POJO.TableTypeQueueTimePOJO;

import java.util.List;

/**
 * @author huqili.tp
 * 多店排队时间对比
 */
public class RestaurantsQueueTimeContrastDTO {

    private String restaurantName;
    private List<TableTypeQueueTimePOJO> tableTypeQueueTimes;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<TableTypeQueueTimePOJO> getTableTypeQueueTimes() {
        return tableTypeQueueTimes;
    }

    public void setTableTypeQueueTimes(List<TableTypeQueueTimePOJO> tableTypeQueueTimes) {
        this.tableTypeQueueTimes = tableTypeQueueTimes;
    }

    @Override
    public String toString() {
        return "RestaurantsQueueTimeContrastDTO{" +
                "restaurantName='" + restaurantName + '\'' +
                ", tableTypeQueueTimes=" + tableTypeQueueTimes +
                '}';
    }
}
