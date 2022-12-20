package com.example.repository;

import com.example.model.beer.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IImageRepository extends JpaRepository<Image,Integer> {

    @Query(value = "select * from image " +
            "join beer on beer.id = image.beer_id " +
            "where image.beer_id = :id and image.is_delete = 0 ",nativeQuery = true)
    List<Image> findAllImage(@Param("id") Integer id);
}
