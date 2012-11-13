/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jcandystore.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexismp
 */
@Entity
@Table(name = "SUPPLIER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findBySuppId", query = "SELECT s FROM Supplier s WHERE s.suppId = :suppId"),
    @NamedQuery(name = "Supplier.findBySuppName", query = "SELECT s FROM Supplier s WHERE s.suppName = :suppName"),
    @NamedQuery(name = "Supplier.findByStatus", query = "SELECT s FROM Supplier s WHERE s.status = :status"),
    @NamedQuery(name = "Supplier.findByAddr1", query = "SELECT s FROM Supplier s WHERE s.addr1 = :addr1"),
    @NamedQuery(name = "Supplier.findByAddr2", query = "SELECT s FROM Supplier s WHERE s.addr2 = :addr2"),
    @NamedQuery(name = "Supplier.findByCity", query = "SELECT s FROM Supplier s WHERE s.city = :city"),
    @NamedQuery(name = "Supplier.findBySuppState", query = "SELECT s FROM Supplier s WHERE s.suppState = :suppState"),
    @NamedQuery(name = "Supplier.findByZip", query = "SELECT s FROM Supplier s WHERE s.zip = :zip"),
    @NamedQuery(name = "Supplier.findByPhone", query = "SELECT s FROM Supplier s WHERE s.phone = :phone")})
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SUPP_ID", nullable = false)
    private Integer suppId;
    @Column(name = "SUPP_NAME", length = 80)
    private String suppName;
    @Basic(optional = false)
    @Column(name = "STATUS", nullable = false, length = 2)
    private String status;
    @Column(name = "ADDR1", length = 80)
    private String addr1;
    @Column(name = "ADDR2", length = 80)
    private String addr2;
    @Column(name = "CITY", length = 80)
    private String city;
    @Column(name = "SUPP_STATE", length = 80)
    private String suppState;
    @Column(name = "ZIP", length = 5)
    private String zip;
    @Column(name = "PHONE", length = 80)
    private String phone;
    @OneToMany(mappedBy = "supplier")
    private Collection<Item> itemCollection;

    public Supplier() {
    }

    public Supplier(Integer suppId) {
        this.suppId = suppId;
    }

    public Supplier(Integer suppId, String status) {
        this.suppId = suppId;
        this.status = status;
    }

    public Integer getSuppId() {
        return suppId;
    }

    public void setSuppId(Integer suppId) {
        this.suppId = suppId;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuppState() {
        return suppState;
    }

    public void setSuppState(String suppState) {
        this.suppState = suppState;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlTransient
    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (suppId != null ? suppId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.suppId == null && other.suppId != null) || (this.suppId != null && !this.suppId.equals(other.suppId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.Supplier[ suppId=" + suppId + " ]";
    }
    
}
