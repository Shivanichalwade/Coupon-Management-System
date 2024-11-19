package com.MonkCommerce.CouponManagement.exception;

public class InvalidCartStateException extends RuntimeException {
    public InvalidCartStateException(String message) {
        super(message);
    }
}
