package com.laptopstore.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptopstore.ecommerce.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
