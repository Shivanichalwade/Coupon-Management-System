package com.MonkCommerce.CouponManagement.repository;

import com.MonkCommerce.CouponManagement.model.Coupons;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponsRepository extends MongoRepository<Coupons, String> {
    Optional<Coupons> findByCodeAndIsActive(String code, boolean isActive);

    @Query("SELECT c FROM Coupon c WHERE c.active = true AND c.expiryDate > CURRENT_TIMESTAMP")
    List<Coupons> findAllActiveCoupons();
}
