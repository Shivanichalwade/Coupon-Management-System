package com.MonkCommerce.CouponManagement.model;

import lombok.Data;

import java.util.List;

@Data
public class BxGyDeal {
    private Integer buyQuantity;
    private Integer getQuantity;
    private List<String> buyProducts;
    private List<String> freeProducts;
    private Integer maxRepetition;
}
