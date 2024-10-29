package com.jlalias.prices.domain.service;

import com.jlalias.prices.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ValidPriceSelector {
    Optional<Price> pickValidPrice(LocalDateTime applicabilityDate, String productId, String brandId);
}
