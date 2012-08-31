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
@Table(name = "SIGNON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Signon.findAll", query = "SELECT s FROM Signon s"),
    @NamedQuery(name = "Signon.findByUsername", query = "SELECT s FROM Signon s WHERE s.username = :username"),
    @NamedQuery(name = "Signon.findByPassword", query = "SELECT s FROM Signon s WHERE s.password = :password")})
public class Signon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USERNAME", nullable = false, length = 25)
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 25)
    private String password;

    public Signon() {
    }

    public Signon(String username) {
        this.username = username;
    }

    public Signon(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Signon)) {
            return false;
        }
        Signon other = (Signon) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.Signon[ username=" + username + " ]";
    }
    
}
