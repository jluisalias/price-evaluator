package com.jlalias.prices.domain.service;

import com.jlalias.prices.domain.Price;
import com.jlalias.prices.infrastructure.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PickByDateAndPriorityValidPriceSelector implements ValidPriceSelector{
    private final PriceRepository priceRepository;
    
    public PickByDateAndPriorityValidPriceSelector(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
    
    @Override
    public Optional<Price> pickValidPrice(LocalDateTime applicabilityDate, String productId, String brandId) {
        List<Price> pricesForProductAndBrand = priceRepository.findDistinctPricesByProductIdAndBrandId(productId, brandId).
                stream().map(priceDB -> Price.createBuilder().fromPriceDB(priceDB).build()).toList();
        return pricesForProductAndBrand.stream()
                .filter(priceRow -> priceRow.isPriceEnabledForTheDate(applicabilityDate))
                .max(Comparator.comparingInt(Price::getPriority));
    }
}
