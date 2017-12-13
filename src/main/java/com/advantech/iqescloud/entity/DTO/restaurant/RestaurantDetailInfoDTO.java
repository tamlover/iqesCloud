package com.advantech.iqescloud.entity.DTO.restaurant;

import com.advantech.iqescloud.entity.POJO.TableTypePOJO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huqili.tp
 */
public class RestaurantDetailInfoDTO {

    private Long id;
    private String name;
    private String telephone;
    private String detailAddress;
    private String businessHours;
    private List<TableTypePOJO> tableTypes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    /**
     * 此处本应由云端到本地取数据
       实为偷懒之举，数据直接写死
     * @return
     */
    public List<TableTypePOJO> getTableTypes() {
        List<TableTypePOJO> tableTypes=new ArrayList<>();
        for (int i=0;i<3;i++){
            TableTypePOJO tableType=new TableTypePOJO(i);
            tableTypes.add(tableType);
        }
        return tableTypes;
    }

    public void setTableTypes(List<TableTypePOJO> tableTypes) {
        this.tableTypes = tableTypes;
    }
}
