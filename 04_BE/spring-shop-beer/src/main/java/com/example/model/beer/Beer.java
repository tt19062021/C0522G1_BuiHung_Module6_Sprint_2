package com.example.model.beer;

import javax.persistence.*;

@Entity
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double alcohol;
    private Integer volume;
    private Integer amount;
    private String detail;
    private Integer priceOld;
    private Integer priceNew;
    private Integer exp;
    private boolean isDelete;
    private String image;

    @ManyToOne
    @JoinColumn(name = "beer_type_id", referencedColumnName = "id")
    private BeerType beerType;

    @ManyToOne
    @JoinColumn(name = "origin_id", referencedColumnName = "id")
    private Origin origin;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    public Beer() {
    }

    public Beer(Integer id, String name, Double alcohol, Integer volume, Integer amount, String detail,
                Integer priceOld, Integer priceNew, Integer exp, boolean isDelete, String image,
                BeerType beerType, Origin origin, Brand brand) {
        this.id = id;
        this.name = name;
        this.alcohol = alcohol;
        this.volume = volume;
        this.amount = amount;
        this.detail = detail;
        this.priceOld = priceOld;
        this.priceNew = priceNew;
        this.exp = exp;
        this.isDelete = isDelete;
        this.image = image;
        this.beerType = beerType;
        this.origin = origin;
        this.brand = brand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Double alcohol) {
        this.alcohol = alcohol;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Integer priceOld) {
        this.priceOld = priceOld;
    }

    public Integer getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(Integer priceNew) {
        this.priceNew = priceNew;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BeerType getBeerType() {
        return beerType;
    }

    public void setBeerType(BeerType beerType) {
        this.beerType = beerType;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
