package com.jlalias.prices.domain.service;

import com.jlalias.prices.infrastructure.db.Price;
import com.jlalias.prices.infrastructure.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PickByDateAndPriorityValidPriceSelectorTest {
    private static final String PRICE_SHOULD_BE_PRESENT = "Price should be present";
    private static final String PRICE_SHOULD_NOT_BE_PRESENT = "Price shouldn't be present";
    private static final String PRODUCT_ID = "35455";
    private static final String BRAND_ID = "1";
    private static final LocalDateTime START_DATE_1 = LocalDateTime.parse("2020-06-14T10:00:00");
    private static final LocalDateTime START_DATE_2 = LocalDateTime.parse("2020-06-14T14:00:00");
    private static final LocalDateTime END_DATE_1 = LocalDateTime.parse("2020-12-31T23:59:59");
    private static final LocalDateTime END_DATE_2 = LocalDateTime.parse("2020-06-15T10:00:00");
    private static final String PRICE_LIST_ID_1 = "1";
    private static final String PRICE_LIST_ID_2 = "2";
    private static final String EUR_CURRENCY = "EUR";
    private Price priceDB1;
    private Price priceDB2;
    private com.jlalias.prices.domain.Price price1;
    private com.jlalias.prices.domain.Price price2;
    private Optional<com.jlalias.prices.domain.Price> optionalPrice;
    
    @InjectMocks
    private PickByDateAndPriorityValidPriceSelector pickByDateAndPriorityValidPriceSelectorSUT;
    @Mock
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        pickByDateAndPriorityValidPriceSelectorSUT = new PickByDateAndPriorityValidPriceSelector(priceRepository);
        instantiatePriceDBs();
        instantiatePrices();
    }

    @Test
    void pickValidPrice_shouldReturnOnePrice_whenJustOnePriceExists() {
        when(priceRepository.findDistinctPricesByProductIdAndBrandId(PRODUCT_ID, BRAND_ID)).
                thenReturn(List.of(priceDB1));
        optionalPrice = pickByDateAndPriorityValidPriceSelectorSUT.pickValidPrice(
                START_DATE_1.plusDays(3L), PRODUCT_ID, BRAND_ID);
        
        assertTrue(optionalPrice.isPresent(), PRICE_SHOULD_BE_PRESENT);
        assertThat(optionalPrice.get(), samePropertyValuesAs(price1));
    }

    @Test
    void pickValidPrice_shouldReturnCorrectPrice_whenSomePriceExistsButJustOneWithValidDate() {
        when(priceRepository.findDistinctPricesByProductIdAndBrandId(PRODUCT_ID, BRAND_ID)).
                thenReturn(List.of(priceDB1, priceDB2));
        optionalPrice = pickByDateAndPriorityValidPriceSelectorSUT.pickValidPrice(
                START_DATE_1.plusHours(2L), PRODUCT_ID, BRAND_ID);

        assertTrue(optionalPrice.isPresent(), PRICE_SHOULD_BE_PRESENT);
        assertThat(optionalPrice.get(), samePropertyValuesAs(price1));
    }

    @Test
    void pickValidPrice_shouldReturnCorrectPrice_whenSomePricesExistsAndMoreThanOneWithValidDatePickingByPriority() {
        when(priceRepository.findDistinctPricesByProductIdAndBrandId(PRODUCT_ID, BRAND_ID)).
                thenReturn(List.of(priceDB1, priceDB2));
        optionalPrice = pickByDateAndPriorityValidPriceSelectorSUT.pickValidPrice(
                START_DATE_1.plusHours(5L), PRODUCT_ID, BRAND_ID);
        assertTrue(optionalPrice.isPresent(), PRICE_SHOULD_BE_PRESENT);
        assertThat(optionalPrice.get(), samePropertyValuesAs(price2));
    }

   @Test
    void pickValidPrice_shouldReturnEmptyPriceRow_whenNoPriceRowsExistForThatProduct() {
        when(priceRepository.findDistinctPricesByProductIdAndBrandId(PRODUCT_ID, BRAND_ID)).
                thenReturn(Collections.emptyList());
        optionalPrice = pickByDateAndPriorityValidPriceSelectorSUT.pickValidPrice(
                START_DATE_1.plusHours(5L), PRODUCT_ID, BRAND_ID);
        assertFalse(optionalPrice.isPresent(), PRICE_SHOULD_NOT_BE_PRESENT);
    }

    private void instantiatePriceDBs() {
        priceDB1 = new Price.Builder()
                .withBrandId(BRAND_ID)
                .withProductId(PRODUCT_ID)
                .withStartDate(START_DATE_1)
                .withEndDate(END_DATE_1)
                .withPriceList(PRICE_LIST_ID_1)
                .withPriority(0)
                .withPrice(12.50)
                .withCurrency(EUR_CURRENCY)
                .build();
        priceDB2 = new Price.Builder()
                .withBrandId(BRAND_ID)
                .withProductId(PRODUCT_ID)
                .withStartDate(START_DATE_2)
                .withEndDate(END_DATE_2)
                .withPriceList(PRICE_LIST_ID_2)
                .withPriority(1)
                .withPrice(21.50)
                .withCurrency(EUR_CURRENCY)
                .build();
    }

    private void instantiatePrices() {
        price1 = new com.jlalias.prices.domain.Price.Builder()
                .fromPriceDB(new Price.Builder()
                        .withBrandId(BRAND_ID)
                        .withProductId(PRODUCT_ID)
                        .withStartDate(START_DATE_1)
                        .withEndDate(END_DATE_1)
                        .withPriceList(PRICE_LIST_ID_1)
                        .withPriority(0)
                        .withPrice(12.50)
                        .withCurrency(EUR_CURRENCY)
                        .build())
                .build();
        price2 = new com.jlalias.prices.domain.Price.Builder()
                .fromPriceDB(new Price.Builder()
                        .withBrandId(BRAND_ID)
                        .withProductId(PRODUCT_ID)
                        .withStartDate(START_DATE_2)
                        .withEndDate(END_DATE_2)
                        .withPriceList(PRICE_LIST_ID_2)
                        .withPriority(1)
                        .withPrice(21.50)
                        .withCurrency(EUR_CURRENCY)
                        .build())
                .build();
    }
}