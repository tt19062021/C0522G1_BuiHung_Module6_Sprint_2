package com.example.service.impl;

import com.example.dto.ICartDto;
import com.example.dto.ITotalDto;
import com.example.model.cart.Cart;
import com.example.repository.ICartRepository;
import com.example.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;
    @Override
    public List<ICartDto> getCartList(Integer customerId) {
        return cartRepository.getCartList(customerId);
    }

    @Override
    public void deleteProduct(Integer id) {
        cartRepository.removeCart(id);
    }


    @Override
    public Cart findCartByUsername(String username) {
        return null;
    }

    @Override
    public void payment(Integer customerId) {
        cartRepository.payment(customerId);
    }

    @Override
    public List<ICartDto> historyShopping(String username) {
        return cartRepository.findAllHistoryShopping(username);
    }
}
