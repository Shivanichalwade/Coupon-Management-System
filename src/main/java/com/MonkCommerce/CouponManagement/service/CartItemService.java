package com.MonkCommerce.CouponManagement.service;

import com.MonkCommerce.CouponManagement.model.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem createCart(CartItem cartItem);
    CartItem getCartById(String id);
    List<CartItem> getAllCarts();
    CartItem updateCart(String id, CartItem cartItem);
    void deleteCart(String id);
}
