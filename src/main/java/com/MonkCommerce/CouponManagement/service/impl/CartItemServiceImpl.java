package com.MonkCommerce.CouponManagement.service.impl;

import com.MonkCommerce.CouponManagement.exception.CartNotFoundException;
import com.MonkCommerce.CouponManagement.model.CartItem;
import com.MonkCommerce.CouponManagement.repository.CartRepository;
import com.MonkCommerce.CouponManagement.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartRepository cartRepository;

    @Override
    public CartItem createCart(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }

    @Override
    public CartItem getCartById(String id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart code "+id+" not found."));
    }

    @Override
    public List<CartItem> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public CartItem updateCart(String id, CartItem cartItem) {
        CartItem existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart code "+id+" not found."));
        existingCart.setUserId(cartItem.getUserId());
        existingCart.setProducts(cartItem.getProducts());
        existingCart.setTotalAmount(cartItem.getTotalAmount());
        existingCart.setAppliedCoupons(cartItem.getAppliedCoupons());
        return cartRepository.save(existingCart);
    }

    @Override
    public void deleteCart(String id) {
        if (!cartRepository.existsById(id)) {
            throw new CartNotFoundException("Cart code "+id+" not found.");
        }
        cartRepository.deleteById(id);
    }
}
