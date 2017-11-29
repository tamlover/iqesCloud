package com.advantech.iqescloud.entity.DTO;

/**
 * @author huqili.tp
 * 给APP首页传递信息的工具类
 */
public class APPHomePageDTO {

    private String restaurantName;
    private String address;
    private String allqueueinfo;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAllqueueinfo() {
        return allqueueinfo;
    }

    public void setAllqueueinfo(String allqueueinfo) {
        this.allqueueinfo = allqueueinfo;
    }
}
