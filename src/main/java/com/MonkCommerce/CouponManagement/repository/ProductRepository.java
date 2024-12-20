package com.MonkCommerce.CouponManagement.repository;

import com.MonkCommerce.CouponManagement.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Products, String> {
}
