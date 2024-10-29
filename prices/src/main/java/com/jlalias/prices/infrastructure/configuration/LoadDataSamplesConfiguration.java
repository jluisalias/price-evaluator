package com.jlalias.prices.infrastructure.configuration;

import com.jlalias.prices.infrastructure.db.Price;
import com.jlalias.prices.infrastructure.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LoadDataSamplesConfiguration {
    private static final Logger log = LoggerFactory.getLogger(LoadDataSamplesConfiguration.class);
    private static final String PRODUCT_ID = "35455";
    private static final String CURRENCY_EUR = "EUR";
    private static final String BRAND_ID = "1";
    private static final String PRICE_LIST_1 = "1";
    private static final String PRICE_LIST_2 = "2";
    private static final String PRICE_LIST_3 = "3";
    private static final String PRICE_LIST_4 = "4";
    private static final String PRELOADING_LOG_MESSAGE = "Preloading {}";
    private static final String PRICE_1_START_DATE = "2020-06-14T00:00:00";
    private static final String PRICE_1_END_DATE = "2020-12-31T23:59:59";
    private static final String PRICE_2_START_DATE = "2020-06-14T15:00:00";
    private static final String PRICE_2_END_DATE = "2020-06-14T18:30:00";
    private static final String PRICE_3_START_DATE = "2020-06-15T00:00:00";
    private static final String PRICE_3_END_DATE = "2020-06-15T11:00:00";
    private static final String PRICE_4_START_DATE = "2020-06-15T16:00:00";
    private static final String PRICE_4_END_DATE = "2020-12-31T23:59:59";

    @Bean
    CommandLineRunner initDatabase(PriceRepository repository) {
        return args -> {
            log.info(PRELOADING_LOG_MESSAGE, repository.save(new Price.Builder()
                    .withProductId(PRODUCT_ID)
                    .withStartDate(LocalDateTime.parse(PRICE_1_START_DATE))
                    .withEndDate(LocalDateTime.parse(PRICE_1_END_DATE))
                    .withBrandId(BRAND_ID)
                    .withPriceList(PRICE_LIST_1)
                    .withPriority(0)
                    .withPrice(35.50)
                    .withCurrency(CURRENCY_EUR)
                    .build()));
            log.info(PRELOADING_LOG_MESSAGE, repository.save(new Price.Builder()
                    .withProductId(PRODUCT_ID)
                    .withStartDate(LocalDateTime.parse(PRICE_2_START_DATE))
                    .withEndDate(LocalDateTime.parse(PRICE_2_END_DATE))
                    .withBrandId(BRAND_ID)
                    .withPriceList(PRICE_LIST_2)
                    .withPriority(1)
                    .withPrice(25.45)
                    .withCurrency(CURRENCY_EUR)
                    .build()));
            
            log.info(PRELOADING_LOG_MESSAGE, repository.save(new Price.Builder()
                    .withProductId(PRODUCT_ID)
                    .withStartDate(LocalDateTime.parse(PRICE_3_START_DATE))
                    .withEndDate(LocalDateTime.parse(PRICE_3_END_DATE))
                    .withBrandId(BRAND_ID)
                    .withPriceList(PRICE_LIST_3)
                    .withPriority(1)
                    .withPrice(30.50)
                    .withCurrency(CURRENCY_EUR)
                    .build()));
            log.info(PRELOADING_LOG_MESSAGE, repository.save(new Price.Builder()
                    .withProductId(PRODUCT_ID)
                    .withStartDate(LocalDateTime.parse(PRICE_4_START_DATE))
                    .withEndDate(LocalDateTime.parse(PRICE_4_END_DATE))
                    .withBrandId(BRAND_ID)
                    .withPriceList(PRICE_LIST_4)
                    .withPriority(1)
                    .withPrice(38.95)
                    .withCurrency(CURRENCY_EUR)
                    .build()));
            log.info("Created {} different prices in memory", repository.findAll().size());
        };
        
    }
}
