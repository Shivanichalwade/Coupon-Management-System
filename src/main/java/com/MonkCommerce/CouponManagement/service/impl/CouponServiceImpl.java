package com.MonkCommerce.CouponManagement.service.impl;

import com.MonkCommerce.CouponManagement.exception.CartNotFoundException;
import com.MonkCommerce.CouponManagement.exception.CouponNotFoundException;
import com.MonkCommerce.CouponManagement.model.CartItem;
import com.MonkCommerce.CouponManagement.model.Coupons;
import com.MonkCommerce.CouponManagement.model.Products;
import com.MonkCommerce.CouponManagement.repository.CartRepository;
import com.MonkCommerce.CouponManagement.repository.CouponsRepository;
import com.MonkCommerce.CouponManagement.response.CouponResponse;
import com.MonkCommerce.CouponManagement.service.CouponsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponsService {
    private final CouponsRepository couponRepository;
    private final CartRepository cartRepository;



    @Override
    public Coupons validateCoupon(String code) {
        return couponRepository.findByCodeAndIsActive(code, true)
                .orElseThrow(() -> new CouponNotFoundException("Invalid or inactive coupon code."));
    }

    @Override
    public Coupons createCoupon(Coupons coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public void deleteCoupon(String code) {
        couponRepository.deleteById(code);
    }

    @Override
    public List<Coupons> getCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Optional<Coupons> getCoupon(String code) {
        return couponRepository.findByCodeAndIsActive(code, true);
    }

    @Override
    public Coupons updateCoupon(String code, Coupons coupons) {
        Optional<Coupons> existingCoupon = couponRepository.findByCodeAndIsActive(code, true);

        if (existingCoupon.isPresent()) {
            Coupons updatedCoupon = existingCoupon.get();
            updatedCoupon.setType(coupons.getType());
            updatedCoupon.setDiscountValue(coupons.getDiscountValue());
            updatedCoupon.setThreshold(coupons.getThreshold());
            updatedCoupon.setApplicableProducts(coupons.getApplicableProducts());
            updatedCoupon.setBxGyDeal(coupons.getBxGyDeal());
            updatedCoupon.setExpiryDate(coupons.getExpiryDate());
            updatedCoupon.setActive(coupons.isActive());

            return couponRepository.save(updatedCoupon);
        } else {
            throw new CouponNotFoundException("Coupon Code "+code+" not found.");
        }
    }

    @Override
    public List<Map<String, Object>> getApplicableCoupons(String id) {
        // Fetch the cart by ID
        CartItem cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart code"+id+" not found."));

        // Fetch all active coupons
        List<Coupons> coupons = couponRepository.findAllActiveCoupons();

        // Calculate discounts
        List<Map<String, Object>> applicableCoupons = new ArrayList<>();
        for (Coupons coupon : coupons) {
            double discount = calculateDiscount(cart, coupon);
            if (discount > 0) {
                Map<String, Object> couponDetails = new HashMap<>();
                couponDetails.put("couponCode", coupon.getCode());
                couponDetails.put("discount", discount);
                applicableCoupons.add(couponDetails);
            }
        }

        return applicableCoupons;
    }

    @Override
    public CartItem applyCouponToCart(String couponId, String cartId) {
        // Fetch the cart and coupon
        CartItem cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart code "+cartId+" not found"));
        Coupons coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException("Coupon Code "+couponId+" not found."));

        // Validate coupon
        if (!coupon.isActive() || coupon.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new CouponNotFoundException("Coupon Code "+couponId+" is invalid.");
        }

        // Apply discount
        double discount = calculateDiscount(cart, coupon);
        if (discount > 0) {
            cart.setTotalAmount(cart.getTotalAmount() - discount);
            cart.getAppliedCoupons().add(coupon.getCode());
        } else {
            throw new CouponNotFoundException("Coupon Code "+couponId+" not found.");
        }

        // Save and return the updated cart
        return cartRepository.save(cart);    }

    private double calculateDiscount(CartItem cart, Coupons coupon) {
        double discount = 0;

        if (coupon.getType().equals("PRODUCT_WISE")) {
            for (Products product : cart.getProducts()) {
                if (coupon.getApplicableProducts().contains(product.getProductId())) {
                    discount += product.getPrice() * product.getQuantity() * (coupon.getDiscountValue() / 100.0);
                }
            }
        } else if (coupon.getType().equals("BXGY")) {
            for (Products product : cart.getProducts()) {
                if (coupon.getBxGyDeal().getBuyProducts().contains(product.getProductId())) {
                    int buyQty = coupon.getBxGyDeal().getBuyQuantity();
                    int freeQty = coupon.getBxGyDeal().getGetQuantity();
                    int eligibleFreeItems = (product.getQuantity() / (buyQty + freeQty)) * freeQty;
                    discount += eligibleFreeItems * product.getPrice();
                }
            }
        }

        return discount;
    }
}
