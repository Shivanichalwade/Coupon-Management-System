package com.MonkCommerce.CouponManagement.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponResponse {
    private String couponCode;
    private double discountAmount;

    public CouponResponse(String couponCode, double discountAmount) {
        this.couponCode = couponCode;
        this.discountAmount = discountAmount;
    }
}
