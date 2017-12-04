package com.advantech.iqescloud.entity;

import javax.persistence.Entity;

/**
 * @author huqili.tp
 * 用户的实体类
 */
@Entity
public class IqesUser extends IdEntity {

    private String userName;
    private String tel;
    private String password;
    /** 会员积分*/
    private Integer memberIntegral;
    /**信誉值*/
    private Integer creditValue;

    /**暂时的唯一标识*/
    private String uniqueName;

    public Integer getCreditValue() {
        return creditValue;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public void setCreditValue(Integer creditValue) {
        this.creditValue = creditValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMemberIntegral() {
        return memberIntegral;
    }

    public void setMemberIntegral(Integer memberIntegral) {
        this.memberIntegral = memberIntegral;
    }
}
