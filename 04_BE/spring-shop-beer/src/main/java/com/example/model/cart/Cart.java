package com.example.model.cart;

import com.example.model.beer.Beer;
import com.example.model.customer.Customer;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dayPayment;
    private Integer quantity;
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "beer_id", referencedColumnName = "id")
    private Beer beer;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public Cart() {
    }

    public Cart(Integer id, Integer quantity, boolean isDelete, Beer beer, Customer customer) {
        this.id = id;
        this.quantity = quantity;
        this.isDelete = isDelete;
        this.beer = beer;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
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
}
