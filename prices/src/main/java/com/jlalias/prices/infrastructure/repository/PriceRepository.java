package com.jlalias.prices.infrastructure.repository;

import com.jlalias.prices.infrastructure.db.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findDistinctPricesByProductIdAndBrandId(String productId, String brandId);
}
