package com.advantech.iqescloud.entity.POJO;

public class TableTypeChurnRatePOJO {

    private String tableTypeDescribe;
    private Float ChurnRate;

    public String getTableTypeDescribe() {
        return tableTypeDescribe;
    }

    public void setTableTypeDescribe(String tableTypeDescribe) {
        this.tableTypeDescribe = tableTypeDescribe;
    }

    public Float getChurnRate() {
        return ChurnRate;
    }

    public void setChurnRate(Float churnRate) {
        ChurnRate = churnRate;
    }

    @Override
    public String toString() {
        return "TableTypeChurnRatePOJO{" +
                "tableTypeDescribe='" + tableTypeDescribe + '\'' +
                ", ChurnRate=" + ChurnRate +
                '}';
    }
}
