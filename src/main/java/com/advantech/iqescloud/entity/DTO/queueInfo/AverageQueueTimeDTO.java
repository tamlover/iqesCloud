package com.advantech.iqescloud.entity.DTO.queueInfo;

import com.advantech.iqescloud.entity.POJO.TableTypeQueueTimePOJO;

import java.util.List;

/**
 * @author huqili.tp
 * 平均等待时间
 */
public class AverageQueueTimeDTO {

    private String date;
    private List<TableTypeQueueTimePOJO> tableTypeQueueTimePOJOList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TableTypeQueueTimePOJO> getTableTypeQueueTimePOJOList() {
        return tableTypeQueueTimePOJOList;
    }

    public void setTableTypeQueueTimePOJOList(List<TableTypeQueueTimePOJO> tableTypeQueueTimePOJOList) {
        this.tableTypeQueueTimePOJOList = tableTypeQueueTimePOJOList;
    }

    @Override
    public String toString() {
        return "AverageQueueTimeDTO{" +
                "date='" + date + '\'' +
                ", tableTypeQueueTimes=" + tableTypeQueueTimePOJOList +
                '}';
    }
}
