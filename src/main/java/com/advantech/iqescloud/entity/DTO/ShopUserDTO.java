package com.advantech.iqescloud.entity.DTO;

/**
 * @author huqili.tp
 * 登录账户信息传递类
 */
public class ShopUserDTO {

    private String account;
    private String pwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
