package com.springdatajpa.springboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OrderResponse {
    private String orderTrackingNumber;
    private String status;
    private String message;
}
