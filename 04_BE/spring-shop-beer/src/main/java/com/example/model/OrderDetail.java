package com.example.model;

import com.example.model.beer.Beer;
import com.example.model.customer.Customer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name = "beer_id", referencedColumnName = "id")
    private Beer beer;

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    private boolean isDelete;

    public OrderDetail() {
    }

    public OrderDetail(Beer beer, Customer customer, boolean isDelete) {
        this.beer = beer;
        this.customer = customer;
        this.isDelete = isDelete;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
