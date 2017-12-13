package com.advantech.iqescloud.entity.DTO.restaurant;

/**
 * @author huqili.tp
 */
public class RestaurantsChurnRateContrastDTO {

    private String restaurantName;
    private Float rate;


    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RestaurantsChurnRateContrastDTO{" +
                "restaurantName='" + restaurantName + '\'' +
                ", rate=" + rate +
                '}';
    }
}
