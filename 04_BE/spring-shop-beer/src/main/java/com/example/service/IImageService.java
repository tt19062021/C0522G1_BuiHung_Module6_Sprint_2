package com.example.service;

import com.example.model.beer.Image;

import java.util.List;
import java.util.Optional;

public interface IImageService {
    List<Image> findAllImage(Integer id);
}
