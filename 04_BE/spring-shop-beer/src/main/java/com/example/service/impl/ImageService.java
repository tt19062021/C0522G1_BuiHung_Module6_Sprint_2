package com.example.service.impl;

import com.example.model.beer.Image;
import com.example.repository.IImageRepository;
import com.example.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepository imageRepository;
    @Override
    public List<Image> findAllImage(Integer id) {
        return imageRepository.findAllImage(id);
    }
}
