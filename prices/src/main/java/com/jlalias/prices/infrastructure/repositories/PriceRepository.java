package com.jlalias.prices.infrastructure.repositories;

import com.jlalias.prices.infrastructure.db.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
