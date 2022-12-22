package com.example.repository;

import com.example.dto.IBeerDto;
import com.example.dto.ITotalDto;
import com.example.model.beer.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface IBeerRepository extends JpaRepository<Beer, Integer> {

    @Query(value = "select beer.id as beerId, beer.image as beerImage, beer.name as beerName, beer.alcohol as beerAlcohol, beer.volume as beerVolume, " +
            "beer.detail as beerDetail, beer.price_old as beerPriceOld, beer.price_new as beerPriceNew, " +
            "beer.exp as beerExp, beer_type.name as beerTypeName, origin.name as originName, brand.name as brandName " +
            "from beer " +
            "join beer_type on beer.beer_type_id = beer_type.id " +
            "join origin on beer.origin_id = origin.id " +
            "join brand on beer.brand_id = brand.id " +
            "where beer.name like %:nameSearch% " +
            "and beer.is_delete = 0 ", nativeQuery = true,
            countQuery = "select count(*) " +
                    "from beer " +
                    "join beer_type on beer.beer_type_id = beer_type.id " +
                    "join origin on beer.origin_id = origin.id " +
                    "join brand on beer.brand_id = brand.id " +
                    "where beer.name like %:nameSearch% " +
                    "and beer.is_delete = 0 ")
    Page<IBeerDto> findAllBeerByQuery(Pageable pageable,
                                      @Param("nameSearch") String nameSearch);

    @Query(value = "select beer.id as beerId, beer.image as beerImage, beer.name as beerName, beer.alcohol as beerAlcohol, beer.volume as beerVolume, " +
            "beer.detail as beerDetail, beer.price_old as beerPriceOld, beer.price_new as beerPriceNew, " +
            "beer.exp as beerExp, beer_type.name as beerTypeName, origin.name as originName, brand.name as brandName " +
            "from beer " +
            "join beer_type on beer.beer_type_id = beer_type.id " +
            "join origin on beer.origin_id = origin.id " +
            "join brand on beer.brand_id = brand.id " +
            "where beer.name like %:nameSearch% " +
            "and (beer.price_new between :startPrice and :endPrice) " +
            "and beer.is_delete = 0 ", nativeQuery = true,
            countQuery = "select count(*) " +
                    "from beer " +
                    "join beer_type on beer.beer_type_id = beer_type.id " +
                    "join origin on beer.origin_id = origin.id " +
                    "join brand on beer.brand_id = brand.id " +
                    "where beer.name like %:nameSearch% " +
                    "and (beer.price_new between :startPrice and :endPrice) " +
                    "and beer.is_delete = 0 ")
    Page<IBeerDto> findAllBeerByPrice(Pageable pageable,
                                      @Param("nameSearch") String searchNameBeer,
                                      @Param("startPrice") Integer startPrice,
                                      @Param("endPrice") Integer endPrice);

    @Query(value = "select beer.id as beerId, beer.image as beerImage, beer.name as beerName, beer.alcohol as beerAlcohol, beer.volume as beerVolume, " +
            "beer.detail as beerDetail, beer.price_old as beerPriceOld, beer.price_new as beerPriceNew, " +
            "beer.exp as beerExp, beer_type.name as beerTypeName, origin.name as originName, brand.name as brandName " +
            "from beer " +
            "join beer_type on beer.beer_type_id = beer_type.id " +
            "join origin on beer.origin_id = origin.id " +
            "join brand on beer.brand_id = brand.id " +
            "where beer.name like %:nameSearch% " +
            "and (beer.alcohol between :startAlcohol and :endAlcohol) " +
            "and beer.is_delete = 0 ", nativeQuery = true,
            countQuery = "select count(*) " +
                    "from beer " +
                    "join beer_type on beer.beer_type_id = beer_type.id " +
                    "join origin on beer.origin_id = origin.id " +
                    "join brand on beer.brand_id = brand.id " +
                    "where beer.name like %:nameSearch% " +
                    "and (beer.alcohol between :startAlcohol and :endAlcohol) " +
                    "and beer.is_delete = 0 ")
    Page<IBeerDto> findAllBeerByAlcohol(Pageable pageable,
                                        @Param("nameSearch") String searchNameBeer,
                                        @Param("startAlcohol") Double startAlcohol,
                                        @Param("endAlcohol") Double endAlcohol);

    @Query(value = "select beer.id as beerId, beer.name as beerName,beer.alcohol as beerAlcohol,beer.volume as beerVolume, " +
            "beer.detail as beerDetail,beer.price_old as beerPriceOld,beer.price_new as beerPriceNew, " +
            "beer.exp as beerExp,beer_type.name as beerTypeName,origin.name as originName,brand.name as brandName, " +
            "beer.image as beerImage " +
            "from beer " +
            "join beer_type on beer.beer_type_id = beer_type.id " +
            "join origin on beer.origin_id = origin.id " +
            "join brand on beer.brand_id = brand.id " +
            "where beer.id = :id " +
            "and beer.is_delete = 0 ", nativeQuery = true)
    Optional<IBeerDto> findBeerById(@Param("id") Integer id);

    @Query(value = "select beer.id, customer.username as username from cart " +
            "join beer on cart.beer_id = beer.id " +
            "join customer on customer.id = cart.customer_id " +
            "where cart.beer_id = :id and customer.username = :username and cart.is_delete = 0 ", nativeQuery = true)
    IBeerDto findIdBeerByUsername(@Param("id") Integer id,
                                  @Param("username") String username);

    @Modifying
    @Query(value = "insert into cart(customer_id, beer_id, quantity,day_payment) values(:cartId, :id, 1, now()) ", nativeQuery = true)
    void insertProductToCart(@Param("id") Integer id, @Param("cartId") Integer cartId);

    @Modifying
    @Query(value = "update cart set quantity = :quantity " +
            "where cart.beer_id = :id and customer_id = :customerId and is_delete = 0 ", nativeQuery = true)
    void updateQty(Integer id, Integer quantity, Integer customerId);

    @Modifying
    @Query(value = "update cart set quantity = quantity + 1 " +
            "where cart.beer_id = :id and is_delete = 0 ", nativeQuery = true)
    void addToCart(Integer id);

    @Modifying
    @Query(value = "delete from cart " +
            "where beer_id = :id ", nativeQuery = true)
    void deleteProduct(Integer id);

    @Query(value = "select sum(cart.quantity * beer.price_new) as totalBill, count(cart.id) as countProduct " +
            "from cart " +
            "join beer on cart.beer_id = beer.id " +
            "where cart.customer_id = :customerId and cart.is_delete = 0 ", nativeQuery = true)
    ITotalDto getTotalBill(Integer customerId);

//    @Query(value = "select beer.image as beerImage, beer.name as beerName, beer.alcohol as beerAlcohol, beer.volume as beerVolume, " +
//            "beer.detail as beerDetail, beer.price_old as beerPriceOld, beer.price_new as beerPriceNew, " +
//            "beer.exp as beerExp, beer_type.name as beerTypeName, origin.name as originName, brand.name as brandName " +
//            "from beer " +
//            "join beer_type on beer.beer_type_id = beer_type.id " +
//            "join origin on beer.origin_id = origin.id " +
//            "join brand on beer.brand_id = brand.id " +
//            "where beer.name like %:nameSearch% " +
//            "and (beer.price_new between :startPrice and :endPrice) " +
//            "and beer.is_delete = 0 ", nativeQuery = true,
//            countQuery = "select count(*) " +
//                    "from beer " +
//                    "join beer_type on beer.beer_type_id = beer_type.id " +
//                    "join origin on beer.origin_id = origin.id " +
//                    "join brand on beer.brand_id = brand.id " +
//                    "where beer.name like %:nameSearch% " +
//                    "and (beer.price_new between :startPrice and :endPrice) " +
//                    "and beer.is_delete = 0 ")
//    Page<IBeerDto> findAllBeerByPrice(Pageable pageable,
//                                      @Param("nameSearch") String nameSearch,
//                                      @Param("startPrice") Integer startPrice,
//                                      @Param("endPrice") Integer endPrice);
//
//    @Query(value = "select beer.name as beerName,beer.alcohol as beerAlcohol,beer.volume as beerVolume, " +
//            "beer.detail as beerDetail,beer.price_old as beerPriceOld,beer.price_new as beerPriceNew, " +
//            "beer.exp as beerExp,beer_type.name as beerTypeName,origin.name as originName,brand.name as brandName " +
//            "from beer " +
//            "join beer_type on beer.beer_type_id = beer_type.id " +
//            "join origin on beer.origin_id = origin.id " +
//            "join brand on beer.brand_id = brand.id " +
//            "where beer.name like %:nameSearch% " +
//            "and (beer.alcohol between :startAlcohol and :endAlcohol) " +
//            "beer.is_delete = 0 ", nativeQuery = true,
//            countQuery = "select  count(*)" +
//                    "from beer " +
//                    "join beer_type on beer.beer_type_id = beer_type.id " +
//                    "join origin on beer.origin_id = origin.id " +
//                    "join brand on beer.brand_id = brand.id " +
//                    "where beer.name like %:nameSearch% " +
//                    "and (beer.alcohol between :startAlcohol and :endAlcohol) " +
//                    "and beer.is_delete = 0 ")
//    Page<IBeerDto> findAllBeerByAlcohol(Pageable pageable,
//                                        @Param("nameSearch") String nameSearch,
//                                        @Param("startAlcohol") Integer startAlcohol,
//                                        @Param("endAlcohol") Integer endAlcohol);
}
//    select beer.name as beerName,beer.alcohol as beerAlcohol,beer.volume as beerVolume,
//        beer.detail as beerDetail,beer.price_old as beerPriceOld,beer.price_new as beerPriceNew,
//        beer.exp as beerExp,beer_type.name as beerTypeName,origin.name as originName,brand.name as brandName
//        from beer
//        join beer_type on beer.beer_type_id=beer_type.id
//        join origin on beer.origin_id=origin.id
//        join brand on beer.brand_id=brand.id
//        where beer.is_delete=0;