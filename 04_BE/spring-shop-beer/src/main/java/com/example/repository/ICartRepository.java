package com.example.repository;

import com.example.dto.ICartDto;
import com.example.dto.ITotalDto;
import com.example.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ICartRepository extends JpaRepository<Cart, Integer> {
    //    @Query(value = "select order_detail.id as id, beer.name as name, beer.price_new as price, " +
//            "order_detail.quantity as quantity, beer.image as image " +
//            "from order_detail " +
//            "join beer on beer.id = order_detail.beer_id " +
//            "where order_detail.is_pay = 0 and order_detail.is_delete = 0 and order_detail.quantity > 0 " +
//            "and order_detail.customer_id = :id ",
//            nativeQuery = true)
//    List<IBeerCartDto> findCartByUser(@Param("id") Integer id);
    @Modifying
//    @Query(value = "update order_detail set quantity = (quantity + 1) where is_pay = 0 and id = :id ",
//            nativeQuery = true)
//    void ascQuantity(@Param("id") Integer id);
//    @Modifying
//    @Query(value = "update order_detail set quantity = (quantity - 1) where is_pay = 0 and id = :id ",
//            nativeQuery = true)
//    void descQuantity(@Param("id") Integer id);
//    @Query(value = "select * from order_detail " +
//            "where is_pay = 0 and beer_id = :beerId ",
//            nativeQuery = true)
//    Optional<OrderDetail> getOrderDetailCart(@Param("beerId") Integer beerId);
//    @Modifying
//    @Query(value = "update order_detail set quantity = :quantity " +
//            "where is_pay = 0  and beer_id = :beerId ",
//            nativeQuery = true)
//        void setQuantityOrderDetailCart(@Param("quantity") Integer quantity,
//                                        @Param("beerId") Integer beerId);
//    @Modifying
//    @Query(value = "insert into order_detail (date_payment, quantity, beer_id) " +
//            "values (now(), :quantity, :beerId) ",
//            nativeQuery = true)
//    void addOrderDetailCart(@Param("quantity") Integer quantity,
//                            @Param("beerId") Integer beerId);
//    @Query(value = "select sum(quantity) as sumQuantityCart from order_detail where is_pay = 0 and customer_id = :id ",
//            nativeQuery = true)
//    Optional<Integer> sumQuantityCart(@Param("id") Integer id);

    @Query(value = "select (cart.quantity * beer.price_new) as sumPerOne, cart.id as id, cart.quantity as quantity, " +
            "beer.name as name, beer.price_new as price, beer.image as image, beer.id as beerId " +
            "from cart " +
            "join beer on cart.beer_id = beer.id " +
            "where cart.is_delete = 0 and cart.customer_id = :customerId " +
            "group by beer.id ", nativeQuery = true)
    List<ICartDto> getCartList(@Param("customerId") Integer customerId);



    @Query(value = "select * from cart where beer_id = :id and is_delete = 0 ", nativeQuery = true)
    ICartDto findByIdBeer(Integer id);
    @Modifying
    @Query(value = "DELETE FROM cart WHERE id = :id ", nativeQuery = true)
    void removeCart(@Param("id") Integer id);
    @Modifying
    @Query(value = "update cart set is_delete = 1 where customer_id =:customerId ", nativeQuery = true)
    void payment(@Param("customerId") Integer customerId);
    @Query(value = "select beer.name, " +
            "cart.day_payment as datePayment, beer.price_new as price, " +
            "cart.quantity as quantity, beer.image as image, " +
            "(beer.price_new * cart.quantity) as sumPerOne " +
            "from cart " +
            "join customer on cart.customer_id = customer.id " +
            "join beer on beer.id = cart.beer_id " +
            "where username = :username and cart.is_delete = 1 ", nativeQuery = true)
    List<ICartDto> findAllHistoryShopping(@Param("username") String username);
}
