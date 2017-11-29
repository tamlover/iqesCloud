package com.advantech.iqescloud.entity.DTO;

import com.advantech.iqescloud.entity.IqesUser;

/**
 * @author huqili.tp
 */
public class UserDTO {

    private Long id;
    private String userName;
    private String password;
    private String tel;
    /** 会员积分*/
    private Integer memberIntegral;

    private Integer creditValue;

    public UserDTO(){

    }

    public Integer getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(Integer creditValue) {
        this.creditValue = creditValue;
    }

    public UserDTO(IqesUser user){
        this.id=user.getId();
        this.userName=user.getUserName();
        this.tel=user.getTel();
        this.memberIntegral=user.getMemberIntegral();
        this.creditValue=user.getCreditValue();
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

    public Integer getMemberIntegral() {
        return memberIntegral;
    }

    public void setMemberIntegral(Integer memberIntegral) {
        this.memberIntegral = memberIntegral;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
