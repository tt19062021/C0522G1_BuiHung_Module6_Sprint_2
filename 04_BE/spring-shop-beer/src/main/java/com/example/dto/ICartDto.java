package com.example.dto;

import java.util.Date;

public interface ICartDto {
    Integer getId();
    Integer getQuantity();
    Double getPrice();
    Double getSumPerOne();
    String getImage();
    String getName();
    Integer getBeerId();
    Date getDatePayment();
}
