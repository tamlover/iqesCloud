package com.advantech.iqescloud.entity.DTO.restaurant;


import com.advantech.iqescloud.entity.Restaurant;

/**
 * @author huqili.tp
 */
public class SimpleRestaurantDTO {

    private Long restaurantId;
    private String restaurantName;

    public SimpleRestaurantDTO(){}

    public SimpleRestaurantDTO(Restaurant r){
        this.restaurantId=r.getId();
        this.restaurantName=r.getName();
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public String toString() {
        return "SimpleRestaurantDTO{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }
}
