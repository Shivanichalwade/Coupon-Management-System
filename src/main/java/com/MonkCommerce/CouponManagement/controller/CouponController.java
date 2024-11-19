package com.MonkCommerce.CouponManagement.controller;

import com.MonkCommerce.CouponManagement.model.CartItem;
import com.MonkCommerce.CouponManagement.model.Coupons;
import com.MonkCommerce.CouponManagement.response.CouponResponse;
import com.MonkCommerce.CouponManagement.service.CouponsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponsService couponsService;

    @PostMapping
    public ResponseEntity<Coupons> createCoupon(@RequestBody Coupons coupons) {
        return ResponseEntity.ok(couponsService.createCoupon(coupons));
    }

    @GetMapping("/{code}")
    public ResponseEntity<Coupons> getCoupon(@PathVariable String code) {
        return ResponseEntity.ok(couponsService.validateCoupon(code));
    }

    @GetMapping
    public ResponseEntity<List<Coupons>> getAllCoupons() {
        return ResponseEntity.ok(couponsService.getCoupons());
    }

    @PutMapping("/{code}")
    public ResponseEntity<Coupons> updateCoupon(@PathVariable String code, @RequestBody Coupons coupons) {
        return ResponseEntity.ok(couponsService.updateCoupon(code,coupons));
    }

    @DeleteMapping("/{code}")
    public void deleteCoupon(@PathVariable String code) {
        couponsService.deleteCoupon(code);
    }

    @PostMapping("/applicableCoupons")
    public ResponseEntity<List<Map<String, Object>>> getApplicableCoupons(@RequestBody String cartId) {
        return ResponseEntity.ok(couponsService.getApplicableCoupons(cartId));
    }

    @PostMapping("/applyCoupon/{id}")
    public ResponseEntity<CartItem> applyCoupon(@PathVariable String id, @RequestBody String cartId) {
        return ResponseEntity.ok(couponsService.applyCouponToCart(id, cartId));
    }


}
