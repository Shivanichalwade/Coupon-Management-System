package com.MonkCommerce.CouponManagement.repository;

import com.MonkCommerce.CouponManagement.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<CartItem, String> {
    Optional<CartItem> findByUserId(String userId);
}
