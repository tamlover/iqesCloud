package com.advantech.iqescloud.entity.POJO;

public class TableTypeQueueTimePOJO {
    String tableTypeDescribe;
    Long queueTime;

    public String getTableTypeDescribe() {
        return tableTypeDescribe;
    }

    public void setTableTypeDescribe(String tableTypeDescribe) {
        this.tableTypeDescribe = tableTypeDescribe;
    }

    public Long getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(Long queueTime) {
        this.queueTime = queueTime;
    }

    @Override
    public String toString() {
        return "TableTypeQueueTimePOJO{" +
                "tableTypeDescribe='" + tableTypeDescribe + '\'' +
                ", queueTime=" + queueTime +
                '}';
    }
}
