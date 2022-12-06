package com.example.service;

import com.example.dto.IBeerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBeerService {
    Page<IBeerDto> findAllBeer(Pageable pageable);
}
