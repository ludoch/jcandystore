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
@Table(name = "ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByUserId", query = "SELECT a FROM Account a WHERE a.userId = :userId"),
    @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
    @NamedQuery(name = "Account.findByFirstName", query = "SELECT a FROM Account a WHERE a.firstName = :firstName"),
    @NamedQuery(name = "Account.findByLastName", query = "SELECT a FROM Account a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "Account.findByStatus", query = "SELECT a FROM Account a WHERE a.status = :status"),
    @NamedQuery(name = "Account.findByAddr1", query = "SELECT a FROM Account a WHERE a.addr1 = :addr1"),
    @NamedQuery(name = "Account.findByAddr2", query = "SELECT a FROM Account a WHERE a.addr2 = :addr2"),
    @NamedQuery(name = "Account.findByCity", query = "SELECT a FROM Account a WHERE a.city = :city"),
    @NamedQuery(name = "Account.findByAcntState", query = "SELECT a FROM Account a WHERE a.acntState = :acntState"),
    @NamedQuery(name = "Account.findByZip", query = "SELECT a FROM Account a WHERE a.zip = :zip"),
    @NamedQuery(name = "Account.findByCountry", query = "SELECT a FROM Account a WHERE a.country = :country"),
    @NamedQuery(name = "Account.findByPhone", query = "SELECT a FROM Account a WHERE a.phone = :phone")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID", nullable = false, length = 80)
    private String userId;
    @Basic(optional = false)
    @Column(name = "EMAIL", nullable = false, length = 80)
    private String email;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME", nullable = false, length = 80)
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME", nullable = false, length = 80)
    private String lastName;
    @Column(name = "STATUS", length = 2)
    private String status;
    @Basic(optional = false)
    @Column(name = "ADDR1", nullable = false, length = 80)
    private String addr1;
    @Column(name = "ADDR2", length = 40)
    private String addr2;
    @Basic(optional = false)
    @Column(name = "CITY", nullable = false, length = 80)
    private String city;
    @Basic(optional = false)
    @Column(name = "ACNT_STATE", nullable = false, length = 80)
    private String acntState;
    @Basic(optional = false)
    @Column(name = "ZIP", nullable = false, length = 20)
    private String zip;
    @Basic(optional = false)
    @Column(name = "COUNTRY", nullable = false, length = 20)
    private String country;
    @Basic(optional = false)
    @Column(name = "PHONE", nullable = false, length = 80)
    private String phone;

    public Account() {
    }

    public Account(String userId) {
        this.userId = userId;
    }

    public Account(String userId, String email, String firstName, String lastName, String addr1, String city, String acntState, String zip, String country, String phone) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addr1 = addr1;
        this.city = city;
        this.acntState = acntState;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getAcntState() {
        return acntState;
    }

    public void setAcntState(String acntState) {
        this.acntState = acntState;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.Account[ userId=" + userId + " ]";
    }
    
}
