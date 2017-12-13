package com.advantech.iqescloud.entity.DTO.restaurant;

/**
 * @author huqili.tp
 */
public class RestaurantSummaryInfoDTO {

    private Long id;
    private String state;
    private String name;
    private String detailAddress;
    private String businessHours;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    @Override
    public String toString() {
        return "SummaryRestaurantInfoDTO{" +
                "state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", businessHours='" + businessHours + '\'' +
                '}';
    }
}
