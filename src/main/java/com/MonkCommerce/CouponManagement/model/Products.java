package com.MonkCommerce.CouponManagement.model;

import lombok.Data;

@Data
public class Products {
    private String productId;
    private String name;
    private Double price;
    private Integer quantity;
}
