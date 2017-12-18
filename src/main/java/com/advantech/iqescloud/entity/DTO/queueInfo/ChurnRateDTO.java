package com.advantech.iqescloud.entity.DTO.queueInfo;

import com.advantech.iqescloud.entity.POJO.TableTypeChurnRatePOJO;

import java.util.List;

/**
 * @author huqili.tp
 * 流失率
 */
public class ChurnRateDTO {

    private Integer queueTime;
    private List<TableTypeChurnRatePOJO> tableTypeChurnRatePOJOList;


    public List<TableTypeChurnRatePOJO> getTableTypeChurnRatePOJOList() {
        return tableTypeChurnRatePOJOList;
    }

    public void setTableTypeChurnRatePOJOList(List<TableTypeChurnRatePOJO> tableTypeChurnRatePOJOList) {
        this.tableTypeChurnRatePOJOList = tableTypeChurnRatePOJOList;
    }

    public Integer getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(Integer queueTime) {
        this.queueTime = queueTime;
    }

    @Override
    public String toString() {
        return "ChurnRateDTO{" +
                "queueTime=" + queueTime +
                ", tableTypeChurnRatePOJOList=" + tableTypeChurnRatePOJOList +
                '}';
    }
}
