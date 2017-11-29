package com.advantech.iqescloud.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author huqili.tp
 */
@Entity
@Table(name = "queue_info")
public class QueueInfo extends IdEntity {

    private String customerName;
    /**
     *
     *联系方式
     * */
    private String customerTel;
    /**
     *
     *用餐人数
     * */
    private Integer eatNumber;
    /**
     *
     *座位号
     * */
    private long tableId;
    /**
     *
     *座位号
     * 接收前台的座位号
     * */
    private String seatNum;
    /**
     *
     *是否被叫号标志
     * */
    private String extractFlag;
    /**
     *
     *桌型
     * */
    private long tableTypeId;
    /**
     *
     *排队开始时间
     * */
    private String queueStartTime;
    /**
     *
     *排队结束时间
     * */
    private String queueEndTime;
    /**
     *
     *排队号
     * */
    private Long queueId;
    /**
     *
     *抽号次数
     * */
    private Integer extractCount;

    /**
     *
     *叫号次数
     * */
    private Integer callCount;
    /**
     * 是否选坐标志
     */
    private Boolean seatFlag;

    /**
     *第一次抽号的时间
     */
    private String firstExtractTime;


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

    public Integer getEatNumber() {
        return eatNumber;
    }

    public void setEatNumber(Integer eatNumber) {
        this.eatNumber = eatNumber;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getExtractFlag() {
        return extractFlag;
    }

    public void setExtractFlag(String extractFlag) {
        this.extractFlag = extractFlag;
    }

    public long getTableTypeId() {
        return tableTypeId;
    }

    public void setTableTypeId(long tableTypeId) {
        this.tableTypeId = tableTypeId;
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

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    public Integer getExtractCount() {
        return extractCount;
    }

    public void setExtractCount(Integer extractCount) {
        this.extractCount = extractCount;
    }

    public Integer getCallCount() {
        return callCount;
    }

    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
    }

    public Boolean getSeatFlag() {
        return seatFlag;
    }

    public void setSeatFlag(Boolean seatFlag) {
        this.seatFlag = seatFlag;
    }

    public String getFirstExtractTime() {
        return firstExtractTime;
    }

    public void setFirstExtractTime(String firstExtractTime) {
        this.firstExtractTime = firstExtractTime;
    }

    @Override
    public String toString() {
        return "QueueInfo{" +
                "customerName='" + customerName + '\'' +
                ", customerTel='" + customerTel + '\'' +
                ", eatNumber=" + eatNumber +
                ", tableId=" + tableId +
                ", seatNum='" + seatNum + '\'' +
                ", extractFlag='" + extractFlag + '\'' +
                ", tableTypeId=" + tableTypeId +
                ", queueStartTime='" + queueStartTime + '\'' +
                ", queueEndTime='" + queueEndTime + '\'' +
                ", queueId=" + queueId +
                ", extractCount=" + extractCount +
                ", callCount=" + callCount +
                ", seatFlag=" + seatFlag +
                ", firstExtractTime='" + firstExtractTime + '\'' +
                '}';
    }
}
