/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jcandystore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexismp
 */
@Entity
@Table(name = "ORDERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderId", query = "SELECT o FROM Orders o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "Orders.findByUserId", query = "SELECT o FROM Orders o WHERE o.userId = :userId"),
    @NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "Orders.findByShipAddr1", query = "SELECT o FROM Orders o WHERE o.shipAddr1 = :shipAddr1"),
    @NamedQuery(name = "Orders.findByShipAddr2", query = "SELECT o FROM Orders o WHERE o.shipAddr2 = :shipAddr2"),
    @NamedQuery(name = "Orders.findByShipCity", query = "SELECT o FROM Orders o WHERE o.shipCity = :shipCity"),
    @NamedQuery(name = "Orders.findByShipState", query = "SELECT o FROM Orders o WHERE o.shipState = :shipState"),
    @NamedQuery(name = "Orders.findByShipZip", query = "SELECT o FROM Orders o WHERE o.shipZip = :shipZip"),
    @NamedQuery(name = "Orders.findByShipCountry", query = "SELECT o FROM Orders o WHERE o.shipCountry = :shipCountry"),
    @NamedQuery(name = "Orders.findByBillAddr1", query = "SELECT o FROM Orders o WHERE o.billAddr1 = :billAddr1"),
    @NamedQuery(name = "Orders.findByBillAddr2", query = "SELECT o FROM Orders o WHERE o.billAddr2 = :billAddr2"),
    @NamedQuery(name = "Orders.findByBillCity", query = "SELECT o FROM Orders o WHERE o.billCity = :billCity"),
    @NamedQuery(name = "Orders.findByBillState", query = "SELECT o FROM Orders o WHERE o.billState = :billState"),
    @NamedQuery(name = "Orders.findByBillZip", query = "SELECT o FROM Orders o WHERE o.billZip = :billZip"),
    @NamedQuery(name = "Orders.findByBillCountry", query = "SELECT o FROM Orders o WHERE o.billCountry = :billCountry"),
    @NamedQuery(name = "Orders.findByCourier", query = "SELECT o FROM Orders o WHERE o.courier = :courier"),
    @NamedQuery(name = "Orders.findByTotalPrice", query = "SELECT o FROM Orders o WHERE o.totalPrice = :totalPrice"),
    @NamedQuery(name = "Orders.findByBillToFirstname", query = "SELECT o FROM Orders o WHERE o.billToFirstname = :billToFirstname"),
    @NamedQuery(name = "Orders.findByBillToLastname", query = "SELECT o FROM Orders o WHERE o.billToLastname = :billToLastname"),
    @NamedQuery(name = "Orders.findByShipToFirstname", query = "SELECT o FROM Orders o WHERE o.shipToFirstname = :shipToFirstname"),
    @NamedQuery(name = "Orders.findBySihpToLastname", query = "SELECT o FROM Orders o WHERE o.sihpToLastname = :sihpToLastname"),
    @NamedQuery(name = "Orders.findByCreditCard", query = "SELECT o FROM Orders o WHERE o.creditCard = :creditCard"),
    @NamedQuery(name = "Orders.findByExprDate", query = "SELECT o FROM Orders o WHERE o.exprDate = :exprDate"),
    @NamedQuery(name = "Orders.findByCardType", query = "SELECT o FROM Orders o WHERE o.cardType = :cardType"),
    @NamedQuery(name = "Orders.findByLocale", query = "SELECT o FROM Orders o WHERE o.locale = :locale")})
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ORDER_ID", nullable = false)
    private Integer orderId;
    @Basic(optional = false)
    @Column(name = "USER_ID", nullable = false, length = 80)
    private String userId;
    @Basic(optional = false)
    @Column(name = "ORDER_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Basic(optional = false)
    @Column(name = "SHIP_ADDR1", nullable = false, length = 80)
    private String shipAddr1;
    @Column(name = "SHIP_ADDR2", length = 80)
    private String shipAddr2;
    @Basic(optional = false)
    @Column(name = "SHIP_CITY", nullable = false, length = 80)
    private String shipCity;
    @Basic(optional = false)
    @Column(name = "SHIP_STATE", nullable = false, length = 80)
    private String shipState;
    @Basic(optional = false)
    @Column(name = "SHIP_ZIP", nullable = false, length = 20)
    private String shipZip;
    @Basic(optional = false)
    @Column(name = "SHIP_COUNTRY", nullable = false, length = 20)
    private String shipCountry;
    @Basic(optional = false)
    @Column(name = "BILL_ADDR1", nullable = false, length = 80)
    private String billAddr1;
    @Column(name = "BILL_ADDR2", length = 80)
    private String billAddr2;
    @Basic(optional = false)
    @Column(name = "BILL_CITY", nullable = false, length = 80)
    private String billCity;
    @Basic(optional = false)
    @Column(name = "BILL_STATE", nullable = false, length = 80)
    private String billState;
    @Basic(optional = false)
    @Column(name = "BILL_ZIP", nullable = false, length = 20)
    private String billZip;
    @Basic(optional = false)
    @Column(name = "BILL_COUNTRY", nullable = false, length = 20)
    private String billCountry;
    @Basic(optional = false)
    @Column(name = "COURIER", nullable = false, length = 80)
    private String courier;
    @Basic(optional = false)
    @Column(name = "TOTAL_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;
    @Basic(optional = false)
    @Column(name = "BILL_TO_FIRSTNAME", nullable = false, length = 80)
    private String billToFirstname;
    @Basic(optional = false)
    @Column(name = "BILL_TO_LASTNAME", nullable = false, length = 80)
    private String billToLastname;
    @Basic(optional = false)
    @Column(name = "SHIP_TO_FIRSTNAME", nullable = false, length = 80)
    private String shipToFirstname;
    @Basic(optional = false)
    @Column(name = "SIHP_TO_LASTNAME", nullable = false, length = 80)
    private String sihpToLastname;
    @Basic(optional = false)
    @Column(name = "CREDIT_CARD", nullable = false, length = 80)
    private String creditCard;
    @Basic(optional = false)
    @Column(name = "EXPR_DATE", nullable = false, length = 7)
    private String exprDate;
    @Basic(optional = false)
    @Column(name = "CARD_TYPE", nullable = false, length = 80)
    private String cardType;
    @Basic(optional = false)
    @Column(name = "LOCALE", nullable = false, length = 80)
    private String locale;

    public Orders() {
    }

    public Orders(Integer orderId) {
        this.orderId = orderId;
    }

    public Orders(Integer orderId, String userId, Date orderDate, String shipAddr1, String shipCity, String shipState, String shipZip, String shipCountry, String billAddr1, String billCity, String billState, String billZip, String billCountry, String courier, BigDecimal totalPrice, String billToFirstname, String billToLastname, String shipToFirstname, String sihpToLastname, String creditCard, String exprDate, String cardType, String locale) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.shipAddr1 = shipAddr1;
        this.shipCity = shipCity;
        this.shipState = shipState;
        this.shipZip = shipZip;
        this.shipCountry = shipCountry;
        this.billAddr1 = billAddr1;
        this.billCity = billCity;
        this.billState = billState;
        this.billZip = billZip;
        this.billCountry = billCountry;
        this.courier = courier;
        this.totalPrice = totalPrice;
        this.billToFirstname = billToFirstname;
        this.billToLastname = billToLastname;
        this.shipToFirstname = shipToFirstname;
        this.sihpToLastname = sihpToLastname;
        this.creditCard = creditCard;
        this.exprDate = exprDate;
        this.cardType = cardType;
        this.locale = locale;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipAddr1() {
        return shipAddr1;
    }

    public void setShipAddr1(String shipAddr1) {
        this.shipAddr1 = shipAddr1;
    }

    public String getShipAddr2() {
        return shipAddr2;
    }

    public void setShipAddr2(String shipAddr2) {
        this.shipAddr2 = shipAddr2;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipState() {
        return shipState;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
    }

    public String getShipZip() {
        return shipZip;
    }

    public void setShipZip(String shipZip) {
        this.shipZip = shipZip;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getBillAddr1() {
        return billAddr1;
    }

    public void setBillAddr1(String billAddr1) {
        this.billAddr1 = billAddr1;
    }

    public String getBillAddr2() {
        return billAddr2;
    }

    public void setBillAddr2(String billAddr2) {
        this.billAddr2 = billAddr2;
    }

    public String getBillCity() {
        return billCity;
    }

    public void setBillCity(String billCity) {
        this.billCity = billCity;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

    public String getBillZip() {
        return billZip;
    }

    public void setBillZip(String billZip) {
        this.billZip = billZip;
    }

    public String getBillCountry() {
        return billCountry;
    }

    public void setBillCountry(String billCountry) {
        this.billCountry = billCountry;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBillToFirstname() {
        return billToFirstname;
    }

    public void setBillToFirstname(String billToFirstname) {
        this.billToFirstname = billToFirstname;
    }

    public String getBillToLastname() {
        return billToLastname;
    }

    public void setBillToLastname(String billToLastname) {
        this.billToLastname = billToLastname;
    }

    public String getShipToFirstname() {
        return shipToFirstname;
    }

    public void setShipToFirstname(String shipToFirstname) {
        this.shipToFirstname = shipToFirstname;
    }

    public String getSihpToLastname() {
        return sihpToLastname;
    }

    public void setSihpToLastname(String sihpToLastname) {
        this.sihpToLastname = sihpToLastname;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getExprDate() {
        return exprDate;
    }

    public void setExprDate(String exprDate) {
        this.exprDate = exprDate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.Orders[ orderId=" + orderId + " ]";
    }
    
}
