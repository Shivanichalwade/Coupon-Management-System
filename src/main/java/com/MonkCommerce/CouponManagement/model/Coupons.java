package com.MonkCommerce.CouponManagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "coupons")
@Data
public class Coupons {
    @Id
    private String id;
    private String type;
    private String code;
    private Double discountValue;
    private Double threshold;
    private List<String> applicableProducts;
    private BxGyDeal bxGyDeal;
    private boolean isActive;
    private LocalDateTime expiryDate;
}
