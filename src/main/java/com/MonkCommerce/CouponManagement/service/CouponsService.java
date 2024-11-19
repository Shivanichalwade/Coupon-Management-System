package com.MonkCommerce.CouponManagement.service;

import com.MonkCommerce.CouponManagement.model.CartItem;
import com.MonkCommerce.CouponManagement.model.Coupons;
import com.MonkCommerce.CouponManagement.response.CouponResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CouponsService {
    Coupons validateCoupon(String code);
    Coupons createCoupon(Coupons coupon);
    void deleteCoupon(String code);
    List<Coupons> getCoupons();
    Optional<Coupons> getCoupon(String code);
    Coupons updateCoupon(String code,Coupons coupon);
    List<Map<String, Object>> getApplicableCoupons(String cartId);
    CartItem applyCouponToCart(String id, String cartId);
}
