package com.example.rest.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    private Long id;

    private String product;

    private BigDecimal cost; //Стоимость

    private Client client;

    private Instant createAt;

    private Instant updateAt;

}
