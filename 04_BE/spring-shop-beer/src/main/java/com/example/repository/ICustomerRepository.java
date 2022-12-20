package com.example.repository;

import com.example.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select * from customer where is_delete = 0 and username = :username ", nativeQuery = true)
    Customer findCustomerByUserName(@Param("username") String username);
}
