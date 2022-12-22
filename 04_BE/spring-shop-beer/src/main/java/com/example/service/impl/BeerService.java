package com.example.service.impl;

import com.example.dto.IBeerDto;
import com.example.dto.ITotalDto;
import com.example.repository.IBeerRepository;
import com.example.service.IBeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BeerService implements IBeerService {

    @Autowired
    private IBeerRepository beerRepository;

    @Override
    public Page<IBeerDto> findAllBeer(Pageable pageable, String searchNameBeer) {
        return beerRepository.findAllBeerByQuery(pageable, searchNameBeer);
    }

    @Override
    public Page<IBeerDto> findAllBeerByPrice(Pageable pageable, String searchNameBeer, Integer startPrice, Integer endPrice) {
        return beerRepository.findAllBeerByPrice(pageable, searchNameBeer, startPrice, endPrice);
    }

    @Override
    public Page<IBeerDto> findAllBeerByAlcohol(Pageable pageable, String searchNameBeer, Double startAlcohol, Double endAlcohol) {
        return beerRepository.findAllBeerByAlcohol(pageable, searchNameBeer, startAlcohol, endAlcohol);
    }

    @Override
    public Optional<IBeerDto> findBeerById(Integer id) {
        return beerRepository.findBeerById(id);
    }

    @Override
    public IBeerDto findById(Integer id, String username) {
        return beerRepository.findIdBeerByUsername(id,username);
    }

    @Override
    public void insertProductToCart(Integer id, Integer customerId) {
        beerRepository.insertProductToCart(id,customerId);
    }

    @Override
    public void updateQty(Integer id, Integer quantity, Integer customerId) {
        beerRepository.updateQty(id,quantity,customerId);
    }

    @Override
    public void addToCart(Integer id) {
        beerRepository.addToCart(id);
    }

    @Override
    public void deleteProduct(Integer id) {
        beerRepository.deleteProduct(id);
    }

    @Override
    public ITotalDto getTotalBill(Integer customerId) {
        return beerRepository.getTotalBill(customerId);
    }

}
