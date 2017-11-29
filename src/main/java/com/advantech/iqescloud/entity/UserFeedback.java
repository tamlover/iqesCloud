package com.advantech.iqescloud.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author huqili.tp
 * 用户的反馈
 */
@Entity
public class UserFeedback extends IdEntity {

    private String context;
    private String photoUrl;


    private IqesUser iqesUser;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @JoinColumn(name = "user_id")
    @ManyToOne
    public IqesUser getIqesUser() {
        return iqesUser;
    }

    public void setIqesUser(IqesUser iqesUser) {
        this.iqesUser = iqesUser;
    }
}
