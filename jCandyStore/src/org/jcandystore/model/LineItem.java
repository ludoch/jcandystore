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
@Table(name = "LINE_ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineItem.findAll", query = "SELECT l FROM LineItem l"),
    @NamedQuery(name = "LineItem.findByOrderId", query = "SELECT l FROM LineItem l WHERE l.lineItemPK.orderId = :orderId"),
    @NamedQuery(name = "LineItem.findByLineNum", query = "SELECT l FROM LineItem l WHERE l.lineItemPK.lineNum = :lineNum"),
    @NamedQuery(name = "LineItem.findByItemId", query = "SELECT l FROM LineItem l WHERE l.itemId = :itemId"),
    @NamedQuery(name = "LineItem.findByQuantity", query = "SELECT l FROM LineItem l WHERE l.quantity = :quantity"),
    @NamedQuery(name = "LineItem.findByUnitPrice", query = "SELECT l FROM LineItem l WHERE l.unitPrice = :unitPrice")})
public class LineItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LineItemPK lineItemPK;
    @Basic(optional = false)
    @Column(name = "ITEM_ID", nullable = false, length = 10)
    private String itemId;
    @Basic(optional = false)
    @Column(name = "QUANTITY", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "UNIT_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    public LineItem() {
    }

    public LineItem(LineItemPK lineItemPK) {
        this.lineItemPK = lineItemPK;
    }

    public LineItem(LineItemPK lineItemPK, String itemId, int quantity, BigDecimal unitPrice) {
        this.lineItemPK = lineItemPK;
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public LineItem(int orderId, int lineNum) {
        this.lineItemPK = new LineItemPK(orderId, lineNum);
    }

    public LineItemPK getLineItemPK() {
        return lineItemPK;
    }

    public void setLineItemPK(LineItemPK lineItemPK) {
        this.lineItemPK = lineItemPK;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineItemPK != null ? lineItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineItem)) {
            return false;
        }
        LineItem other = (LineItem) object;
        if ((this.lineItemPK == null && other.lineItemPK != null) || (this.lineItemPK != null && !this.lineItemPK.equals(other.lineItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.LineItem[ lineItemPK=" + lineItemPK + " ]";
    }
    
}
