package org.example.entity;

import org.example.utils.OrderState;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String typePresta;
    private String designation;
    private Integer nbDays;
    private Float unitPrice;
    @Column(name = "state", columnDefinition = "BIT")
    private OrderState orderState;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    public Order() {
    }

    public Order(Integer id, String typePresta, String designation, Integer nbDays, Float unitPrice, OrderState orderState, Client client) {
        this.id = id;
        this.typePresta = typePresta;
        this.designation = designation;
        this.nbDays = nbDays;
        this.unitPrice = unitPrice;
        this.orderState = orderState;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypePresta() {
        return typePresta;
    }

    public void setTypePresta(String typePresta) {
        this.typePresta = typePresta;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getNbDays() {
        return nbDays;
    }

    public void setNbDays(Integer nbDays) {
        this.nbDays = nbDays;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setNotNullData(Order newOrderData) {

        if (newOrderData.getTypePresta() != null) {
            this.setTypePresta(newOrderData.getTypePresta());
        }

        if (newOrderData.getDesignation() != null) {
            this.setDesignation(newOrderData.getDesignation());
        }

        if (newOrderData.getNbDays() != null) {
            this.setNbDays(newOrderData.getNbDays());
        }

        if (newOrderData.getUnitPrice() != null) {
            this.setUnitPrice(newOrderData.getUnitPrice());
        }

        if (newOrderData.getOrderState() != null) {
            this.setOrderState(newOrderData.getOrderState());
        }
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", typePresta='" + typePresta + '\'' + ", designation='" + designation + '\'' +
                ", nbDays=" + nbDays + ", unitPrice=" + unitPrice + ", orderState=" + orderState + ", client=" + client + '}';
    }
}