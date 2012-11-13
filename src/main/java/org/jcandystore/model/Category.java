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
@Table(name = "CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByCatId", query = "SELECT c FROM Category c WHERE c.catId = :catId"),
    @NamedQuery(name = "Category.findByCatName", query = "SELECT c FROM Category c WHERE c.catName = :catName"),
    @NamedQuery(name = "Category.findByDescription", query = "SELECT c FROM Category c WHERE c.description = :description")})
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CAT_ID", nullable = false, length = 10)
    private String catId;
    @Column(name = "CAT_NAME", length = 80)
    private String catName;
    @Column(name = "DESCRIPTION", length = 255)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Collection<Product> productCollection;

    public Category() {
    }

    public Category(String catId) {
        this.catId = catId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catId != null ? catId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.catId == null && other.catId != null) || (this.catId != null && !this.catId.equals(other.catId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.Category[ catId=" + catId + " ]";
    }
    
}
