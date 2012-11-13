/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jcandystore.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexismp
 */
@Entity
@Table(name = "PROFILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p"),
    @NamedQuery(name = "Profile.findByUserId", query = "SELECT p FROM Profile p WHERE p.userId = :userId"),
    @NamedQuery(name = "Profile.findByLangPref", query = "SELECT p FROM Profile p WHERE p.langPref = :langPref"),
    @NamedQuery(name = "Profile.findByFavgategory", query = "SELECT p FROM Profile p WHERE p.favgategory = :favgategory"),
    @NamedQuery(name = "Profile.findByMylistOpt", query = "SELECT p FROM Profile p WHERE p.mylistOpt = :mylistOpt"),
    @NamedQuery(name = "Profile.findByBannerOpt", query = "SELECT p FROM Profile p WHERE p.bannerOpt = :bannerOpt")})
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID", nullable = false, length = 80)
    private String userId;
    @Basic(optional = false)
    @Column(name = "LANG_PREF", nullable = false, length = 80)
    private String langPref;
    @Column(name = "FAVGATEGORY", length = 30)
    private String favgategory;
    @Column(name = "MYLIST_OPT")
    private Short mylistOpt;
    @Column(name = "BANNER_OPT")
    private Short bannerOpt;

    public Profile() {
    }

    public Profile(String userId) {
        this.userId = userId;
    }

    public Profile(String userId, String langPref) {
        this.userId = userId;
        this.langPref = langPref;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLangPref() {
        return langPref;
    }

    public void setLangPref(String langPref) {
        this.langPref = langPref;
    }

    public String getFavgategory() {
        return favgategory;
    }

    public void setFavgategory(String favgategory) {
        this.favgategory = favgategory;
    }

    public Short getMylistOpt() {
        return mylistOpt;
    }

    public void setMylistOpt(Short mylistOpt) {
        this.mylistOpt = mylistOpt;
    }

    public Short getBannerOpt() {
        return bannerOpt;
    }

    public void setBannerOpt(Short bannerOpt) {
        this.bannerOpt = bannerOpt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.Profile[ userId=" + userId + " ]";
    }
    
}
