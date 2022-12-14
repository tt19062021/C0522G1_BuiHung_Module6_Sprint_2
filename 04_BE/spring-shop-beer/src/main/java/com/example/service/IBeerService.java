package com.example.service;

import com.example.dto.IBeerDto;
import com.example.dto.ITotalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBeerService {
    Page<IBeerDto> findAllBeer(Pageable pageable, String searchNameBeer);

    Page<IBeerDto> findAllBeerByPrice(Pageable pageable, String searchNameBeer, Integer startPrice, Integer endPrice);

    Page<IBeerDto> findAllBeerByAlcohol(Pageable pageable, String searchNameBeer, Double startAlcohol, Double endAlcohol);
    Optional<IBeerDto> findBeerById(Integer id);
    IBeerDto findById(Integer id, String username);
    void insertProductToCart(Integer id, Integer customerId);
    void updateQty(Integer id, Integer quantity, Integer customerId);
    void addToCart(Integer id);
    void deleteProduct(Integer id);
    ITotalDto getTotalBill(Integer customerId);
}
