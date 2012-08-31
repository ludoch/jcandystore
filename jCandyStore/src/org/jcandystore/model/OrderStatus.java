/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jcandystore.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexismp
 */
@Entity
@Table(name = "ORDER_STATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderStatus.findAll", query = "SELECT o FROM OrderStatus o"),
    @NamedQuery(name = "OrderStatus.findByOrderId", query = "SELECT o FROM OrderStatus o WHERE o.orderStatusPK.orderId = :orderId"),
    @NamedQuery(name = "OrderStatus.findByLineNum", query = "SELECT o FROM OrderStatus o WHERE o.orderStatusPK.lineNum = :lineNum"),
    @NamedQuery(name = "OrderStatus.findByOrderTimestamp", query = "SELECT o FROM OrderStatus o WHERE o.orderTimestamp = :orderTimestamp"),
    @NamedQuery(name = "OrderStatus.findByStatus", query = "SELECT o FROM OrderStatus o WHERE o.status = :status")})
public class OrderStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderStatusPK orderStatusPK;
    @Basic(optional = false)
    @Column(name = "ORDER_TIMESTAMP", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderTimestamp;
    @Basic(optional = false)
    @Column(name = "STATUS", nullable = false, length = 2)
    private String status;

    public OrderStatus() {
    }

    public OrderStatus(OrderStatusPK orderStatusPK) {
        this.orderStatusPK = orderStatusPK;
    }

    public OrderStatus(OrderStatusPK orderStatusPK, Date orderTimestamp, String status) {
        this.orderStatusPK = orderStatusPK;
        this.orderTimestamp = orderTimestamp;
        this.status = status;
    }

    public OrderStatus(int orderId, int lineNum) {
        this.orderStatusPK = new OrderStatusPK(orderId, lineNum);
    }

    public OrderStatusPK getOrderStatusPK() {
        return orderStatusPK;
    }

    public void setOrderStatusPK(OrderStatusPK orderStatusPK) {
        this.orderStatusPK = orderStatusPK;
    }

    public Date getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(Date orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderStatusPK != null ? orderStatusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderStatus)) {
            return false;
        }
        OrderStatus other = (OrderStatus) object;
        if ((this.orderStatusPK == null && other.orderStatusPK != null) || (this.orderStatusPK != null && !this.orderStatusPK.equals(other.orderStatusPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.OrderStatus[ orderStatusPK=" + orderStatusPK + " ]";
    }
    
}
