package com.advantech.iqescloud.entity.DTO.restaurant;

public class RestaurantsQueuesContrastDTO {

    private String restaurantName;
    private Integer queueQuantity;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getQueueQuantity() {
        return queueQuantity;
    }

    public void setQueueQuantity(Integer queueQuantity) {
        this.queueQuantity = queueQuantity;
    }

    @Override
    public String toString() {
        return "RestaurantsQueuesContrastDTO{" +
                "restaurantName='" + restaurantName + '\'' +
                ", queueQuantity=" + queueQuantity +
                '}';
    }
}
