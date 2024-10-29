package com.jlalias.prices.application.port;

import com.jlalias.prices.application.dto.ValidPriceResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface PriceEvaluatorApi {
    ResponseEntity<ValidPriceResponse> getValidPrice(LocalDateTime applicabilityDate, String productId, String brandId);
}
