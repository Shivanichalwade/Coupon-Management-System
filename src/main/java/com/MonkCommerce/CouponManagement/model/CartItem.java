package com.MonkCommerce.CouponManagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "carts")
@Data
public class CartItem {
    @Id
    private String id;
    private String userId;
    private List<Products> products;
    private Double totalAmount;
    private List<String> appliedCoupons;
}
