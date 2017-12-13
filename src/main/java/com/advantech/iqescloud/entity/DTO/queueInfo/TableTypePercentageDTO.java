package com.advantech.iqescloud.entity.DTO.queueInfo;

/**
 * @author huqili.tp
 *
 */
public class TableTypePercentageDTO {
    private String tableTypeDescribe;
    private Integer number;

    public String getTableTypeDescribe() {
        return tableTypeDescribe;
    }

    public void setTableTypeDescribe(String tableTypeDescribe) {
        this.tableTypeDescribe = tableTypeDescribe;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "TableTypePercentageDTO{" +
                "tableTypeDescribe='" + tableTypeDescribe + '\'' +
                ", number=" + number +
                '}';
    }
}
