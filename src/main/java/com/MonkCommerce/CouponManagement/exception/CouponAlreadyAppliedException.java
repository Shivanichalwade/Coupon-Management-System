package com.MonkCommerce.CouponManagement.exception;

public class CouponAlreadyAppliedException extends RuntimeException {
    public CouponAlreadyAppliedException(String message) {
        super(message);
    }
}
