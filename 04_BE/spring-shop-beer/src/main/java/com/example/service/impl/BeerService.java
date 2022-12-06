package com.example.service.impl;

import com.example.dto.IBeerDto;
import com.example.repository.IBeerRepository;
import com.example.service.IBeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BeerService implements IBeerService {

    @Autowired
    private IBeerRepository beerRepository;

    @Override
    public Page<IBeerDto> findAllBeer(Pageable pageable) {
        return beerRepository.findAllBeerByQuery(pageable);
    }
}
