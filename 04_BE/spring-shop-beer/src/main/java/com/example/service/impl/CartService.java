package com.example.service.impl;

import com.example.dto.ICartDto;
import com.example.dto.ITotalDto;
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
    public List<ICartDto> getCartList() {
        return cartRepository.getCartList();
    }

    @Override
    public ITotalDto getTotalBill() {
        return cartRepository.getTotalBill();
    }

    @Override
    public void updateCart(Integer id) {
        cartRepository.updateCart(id);
    }

    @Override
    public void insertToCart(Integer id) {
        cartRepository.insertToCart(id);
    }

    @Override
    public void updateQty(Integer id, Integer quantity) {
        cartRepository.updateQty(id,quantity);
    }

    @Override
    public void deleteProduct(Integer id) {
        cartRepository.removeCart(id);
    }

    @Override
    public ICartDto findById(Integer id) {
        return cartRepository.findByIdBeer(id);
    }
}
