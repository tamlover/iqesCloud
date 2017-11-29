package com.advantech.iqescloud.entity;

import com.advantech.iqescloud.entity.DTO.RestaurantDTO;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author
 * 门店基本信息实体类
 */
@Entity
@Table(name = "restaurant")
public class Restaurant extends IdEntity {

    private String name;
    private String country;
    private String province;
    private String city;
    private String longtitude;
    private String latitude;
    private String detailAddress;
    private String openTime;
    private String endTime;
    private String telephone;

    public Restaurant(){

    }
    public Restaurant(RestaurantDTO restaurantDTO){

        this.id=restaurantDTO.getCloudId();
        this.name=restaurantDTO.getName();
        this.country=restaurantDTO.getCountry();
        this.province=restaurantDTO.getProvince();
        this.city=restaurantDTO.getCity();
        this.longtitude=restaurantDTO.getLongtitude();
        this.latitude=restaurantDTO.getLatitude();
        this.detailAddress=restaurantDTO.getDetailAddress();
        this.openTime=restaurantDTO.getOpenTime();
        this.endTime=restaurantDTO.getEndTime();
        this.telephone=restaurantDTO.getTelephone();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
