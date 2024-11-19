package com.MonkCommerce.CouponManagement.controller;

import com.MonkCommerce.CouponManagement.model.CartItem;
import com.MonkCommerce.CouponManagement.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItem> createCart(@RequestBody CartItem cartItem) {
        return ResponseEntity.ok(cartItemService.createCart(cartItem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartById(@PathVariable String id) {
        return ResponseEntity.ok(cartItemService.getCartById(id));
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCarts() {
        return ResponseEntity.ok(cartItemService.getAllCarts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCart(@PathVariable String id, @RequestBody CartItem cartItem) {
        return ResponseEntity.ok(cartItemService.updateCart(id, cartItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        cartItemService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
