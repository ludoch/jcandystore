/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jcandystore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexismp
 */
@Entity
@Table(name = "ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByProdId", query = "SELECT i FROM Item i WHERE i.product.prodId = :prodId"),
    @NamedQuery(name = "Item.findByStatus", query = "SELECT i FROM Item i WHERE i.status = :status")})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ITEM_ID", nullable = false, length = 10)
    private String itemId;
    @Column(name = "LIST_PRICE", precision = 10, scale = 2)
    private BigDecimal listPrice;
    @Column(name = "UNIT_COST", precision = 10, scale = 2)
    private BigDecimal unitCost;
    @Column(name = "STATUS", length = 2)
    private String status;
    @Column(name = "ATTR1", length = 80)
    private String attr1;
    @Column(name = "ATTR2", length = 80)
    private String attr2;
    @Column(name = "ATTR3", length = 80)
    private String attr3;
    @Column(name = "ATTR4", length = 80)
    private String attr4;
    @Column(name = "ATTR5", length = 80)
    private String attr5;
    @JoinColumns({
//        @JoinColumn(name = "SUPPLIER", referencedColumnName = "SUPP_ID"),
        @JoinColumn(name = "SUPPLIER", referencedColumnName = "SUPP_ID")})
    @ManyToOne
    private Supplier supplier;
    @JoinColumns({
//        @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PROD_ID", nullable = false),
        @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PROD_ID", nullable = false)})
    @ManyToOne(optional = false)
    private Product product;

    public Item() {
    }

    public Item(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.Item[ itemId=" + itemId + " ]";
    }
    
}
