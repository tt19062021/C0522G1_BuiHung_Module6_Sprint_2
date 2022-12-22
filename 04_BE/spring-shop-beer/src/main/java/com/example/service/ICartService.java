package com.example.service;

import com.example.dto.ICartDto;
import com.example.dto.ITotalDto;
import com.example.model.cart.Cart;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartService {
    List<ICartDto> getCartList(Integer customerId);
    void deleteProduct(Integer id);
    Cart findCartByUsername(String username);
    void payment(Integer customerId);

    List<ICartDto> historyShopping(String username);
}
