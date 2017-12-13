package com.advantech.iqescloud.entity.DTO.queueInfo;

/**
 * @author huqili.tp
 * 流失率
 */
public class ChurnRateDTO {

    private String date;
    private Float rate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ChurnRateDTO{" +
                "date='" + date + '\'' +
                ", rate=" + rate +
                '}';
    }
}
