package com.example.service;

import com.example.dto.ICartDto;
import com.example.dto.ITotalDto;

import java.util.List;

public interface ICartService {
    List<ICartDto> getCartList();
    ITotalDto getTotalBill();
    void updateCart(Integer id);
    void insertToCart(Integer id);
    void updateQty(Integer id, Integer qty);
    void deleteProduct(Integer id);
    ICartDto findById(Integer id);
}
