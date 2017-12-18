package com.advantech.iqescloud.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author huqili.tp
 * 云端登录账户
 */
@Entity
@Table(name = "shop_user")
public class ShopUser extends IdEntity {

    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
