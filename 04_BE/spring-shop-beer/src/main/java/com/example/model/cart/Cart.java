package com.example.model.cart;

import com.example.model.beer.Beer;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "beer_id", referencedColumnName = "id")
    private Beer beer;


    public Cart() {
    }

    public Cart(Integer id, Integer quantity, boolean isDelete, Beer beer) {
        this.id = id;
        this.quantity = quantity;
        this.isDelete = isDelete;
        this.beer = beer;
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
}
