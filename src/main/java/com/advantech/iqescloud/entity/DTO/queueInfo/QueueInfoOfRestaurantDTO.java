package com.advantech.iqescloud.entity.DTO.queueInfo;

/**
 * @author huqili.tp
 * about restaurant
 */
public class QueueInfoOfRestaurantDTO {
    private String customerName;
    private String customerTel;
    private String queueStartTime;
    private String queueEndTime;
    private Integer queueTime;
    private String tableTypeDescribe;
    private Integer eatNumber;
    private Boolean seatFlag;
    private String tableNumber;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getQueueStartTime() {
        return queueStartTime;
    }

    public void setQueueStartTime(String queueStartTime) {
        this.queueStartTime = queueStartTime;
    }

    public String getQueueEndTime() {
        return queueEndTime;
    }

    public void setQueueEndTime(String queueEndTime) {
        this.queueEndTime = queueEndTime;
    }

    public Integer getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(Integer queueTime) {
        this.queueTime = queueTime;
    }

    public String getTableTypeDescribe() {
        return tableTypeDescribe;
    }

    public void setTableTypeDescribe(String tableTypeDescribe) {
        this.tableTypeDescribe = tableTypeDescribe;
    }

    public Integer getEatNumber() {
        return eatNumber;
    }

    public void setEatNumber(Integer eatNumber) {
        this.eatNumber = eatNumber;
    }

    public Boolean getSeatFlag() {
        return seatFlag;
    }

    public void setSeatFlag(Boolean seatFlag) {
        this.seatFlag = seatFlag;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public String toString() {
        return "QueueInfoOfRestaurantDTO{" +
                "customerName='" + customerName + '\'' +
                ", telephone='" + customerTel + '\'' +
                ", queueStartTime='" + queueStartTime + '\'' +
                ", queueEndTime='" + queueEndTime + '\'' +
                ", queueTime=" + queueTime +
                ", tableTypeDescribe='" + tableTypeDescribe + '\'' +
                ", eatNumber=" + eatNumber +
                ", seatFlag='" + seatFlag + '\'' +
                ", tableNumber='" + tableNumber + '\'' +
                '}';
    }
}
