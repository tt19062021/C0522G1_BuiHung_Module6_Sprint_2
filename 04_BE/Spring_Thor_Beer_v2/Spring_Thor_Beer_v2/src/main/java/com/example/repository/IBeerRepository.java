package com.example.repository;

import com.example.dto.IBeerDto;
import com.example.model.beer.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBeerRepository extends JpaRepository<Beer, Integer> {

    @Query(value = "select beer.image as beerImage, beer.name as beerName, beer.alcohol as beerAlcohol, beer.volume as beerVolume, " +
            "beer.detail as beerDetail, beer.price_old as beerPriceOld, beer.price_new as beerPriceNew, " +
            "beer.exp as beerExp, beer_type.name as beerTypeName, origin.name as originName, brand.name as brandName " +
            "from beer " +
            "join beer_type on beer.beer_type_id = beer_type.id " +
            "join origin on beer.origin_id = origin.id " +
            "join brand on beer.brand_id = brand.id " +
            "where beer.is_delete = 0 ", nativeQuery = true,
            countQuery = "select count(*) " +
                    "from beer " +
                    "join beer_type on beer.beer_type_id = beer_type.id " +
                    "join origin on beer.origin_id = origin.id " +
                    "join brand on beer.brand_id = brand.id " +
                    "where beer.is_delete = 0 ")
    Page<IBeerDto> findAllBeerByQuery(Pageable pageable);
}
//    select beer.name as beerName, beer.alcohol as beerAlcohol, beer.volume as beerVolume,
//        beer.detail as beerDetail,beer.price_old as beerPriceOld,beer.price_new as beerPriceNew,
//        beer.exp as beerExp, beer_type.name as beerTypeName, origin.name as originName, brand.name as brandName
//        from beer
//        join beer_type on beer.beer_type_id = beer_type.id
//        join origin on beer.origin_id = origin.id
//        join brand on beer.brand_id = brand.id
//        where beer.is_delete = 0;