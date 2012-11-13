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
@Table(name = "BANNERDATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bannerdata.findAll", query = "SELECT b FROM Bannerdata b"),
    @NamedQuery(name = "Bannerdata.findByFavgategory", query = "SELECT b FROM Bannerdata b WHERE b.favgategory = :favgategory"),
    @NamedQuery(name = "Bannerdata.findByBannerName", query = "SELECT b FROM Bannerdata b WHERE b.bannerName = :bannerName")})
public class Bannerdata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FAVGATEGORY", nullable = false, length = 80)
    private String favgategory;
    @Column(name = "BANNER_NAME", length = 255)
    private String bannerName;

    public Bannerdata() {
    }

    public Bannerdata(String favgategory) {
        this.favgategory = favgategory;
    }

    public String getFavgategory() {
        return favgategory;
    }

    public void setFavgategory(String favgategory) {
        this.favgategory = favgategory;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (favgategory != null ? favgategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bannerdata)) {
            return false;
        }
        Bannerdata other = (Bannerdata) object;
        if ((this.favgategory == null && other.favgategory != null) || (this.favgategory != null && !this.favgategory.equals(other.favgategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.Bannerdata[ favgategory=" + favgategory + " ]";
    }
    
}
