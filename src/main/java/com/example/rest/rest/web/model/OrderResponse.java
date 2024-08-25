package com.example.rest.rest.web.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {

    private Long id;

    private String product;

    private BigDecimal cost;
}
