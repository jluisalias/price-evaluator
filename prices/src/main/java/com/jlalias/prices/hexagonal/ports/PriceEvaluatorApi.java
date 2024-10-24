package com.jlalias.prices.hexagonal.ports;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface PriceEvaluatorApi {
    ResponseEntity<Object> getValidPrice(LocalDateTime applicabilityDate, String productId, String brandId);
}
