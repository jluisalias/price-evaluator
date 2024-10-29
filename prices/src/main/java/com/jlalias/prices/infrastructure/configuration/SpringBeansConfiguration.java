package com.jlalias.prices.infrastructure.configuration;

import com.jlalias.prices.domain.service.PickByDateAndPriorityValidPriceSelector;
import com.jlalias.prices.domain.service.ValidPriceSelector;
import com.jlalias.prices.infrastructure.repository.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeansConfiguration {
    @Bean
    ValidPriceSelector validPriceSelector(PriceRepository priceRepository) {
        return new PickByDateAndPriorityValidPriceSelector(priceRepository);
    }
}
