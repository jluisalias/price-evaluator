package com.jlalias.prices.domain;

import com.jlalias.prices.application.dto.ValidPriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {
    private static final String PRODUCT_ID = "35455";
    private static final String BRAND_ID = "1";
    private static final LocalDateTime START_DATE_1 = LocalDateTime.parse("2020-06-14T10:00:00");
    private static final LocalDateTime START_DATE_2 = LocalDateTime.parse("2020-06-18T10:00:00");
    private static final LocalDateTime END_DATE_1 = LocalDateTime.parse("2020-06-20T10:00:00");
    private static final LocalDateTime END_DATE_2 = LocalDateTime.parse("2020-06-22T10:00:00");
    private static final String PRICE_LIST_ID_1 = "1";
    private static final String PRICE_LIST_ID_2 = "2";
    private static final String EUR_CURRENCY = "EUR";
    private Price priceSUT;
    private Price anotherPriceSUTButNotThatImportant;
    private com.jlalias.prices.infrastructure.db.Price priceDB1;
    private com.jlalias.prices.infrastructure.db.Price priceDB2;

    @BeforeEach
    void setUp() {
        instantiatePriceEntities();
        instantiatePrices();
    }

    @Test
    void isPriceEnabledForTheDate_shouldBeValidJustForTheFirstPrice_ifTheDateIsJustValidForFirstPrice() {
        final LocalDateTime dateJustValidForFirstPrice = LocalDateTime.parse("2020-06-15T10:00:00");

        assertTrue(priceSUT.isPriceEnabledForTheDate(dateJustValidForFirstPrice));
        assertFalse(anotherPriceSUTButNotThatImportant.isPriceEnabledForTheDate(dateJustValidForFirstPrice));
    }

    @Test
    void isPriceEnabledForTheDate_shouldBeValidJustForTheSecondPrice_ifTheDateIsJustValidForTheSecondPrice() {
        final LocalDateTime dateJustValidForSecondPrice = LocalDateTime.parse("2020-06-21T10:00:00");

        assertFalse(priceSUT.isPriceEnabledForTheDate(dateJustValidForSecondPrice));
        assertTrue(anotherPriceSUTButNotThatImportant.isPriceEnabledForTheDate(dateJustValidForSecondPrice));
    }

    @Test
    void isPriceEnabledForTheDate_shouldBeValidForBothPrices_ifTheDateIsValidForBothPrices() {
        final LocalDateTime dateValidForBothPrices = LocalDateTime.parse("2020-06-19T10:00:00");

        assertTrue(priceSUT.isPriceEnabledForTheDate(dateValidForBothPrices));
        assertTrue(anotherPriceSUTButNotThatImportant.isPriceEnabledForTheDate(dateValidForBothPrices));
    }

    @Test
    void isPriceEnabledForTheDate_shouldBeInvalidForBothPrices_ifTheDateIsNotValidForBothPrices() {
        final LocalDateTime dateNotValidForBothPrices = LocalDateTime.parse("2023-06-19T10:00:00");

        assertFalse(priceSUT.isPriceEnabledForTheDate(dateNotValidForBothPrices));
        assertFalse(anotherPriceSUTButNotThatImportant.isPriceEnabledForTheDate(dateNotValidForBothPrices));
    }

    @Test
    void getResponseFromPrice_shouldReturnAResponseWithTheSameDataThanPrice() {
        ValidPriceResponse response = priceSUT.getResponseFromPrice();
        String validityPeriod = "From ".concat(START_DATE_1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).
                concat(" to "). concat(END_DATE_1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String validPrice = String.format("%.2f", 12.50).concat(" ").concat(EUR_CURRENCY);
        assertEquals(PRODUCT_ID, response.getProductId());
        assertEquals(BRAND_ID, response.getBrandId());
        assertEquals(PRICE_LIST_ID_1, response.getPriceListId());
        assertEquals(validityPeriod, response.getValidityPeriod());
        assertEquals(validPrice, response.getValidPrice());
    }

    private void instantiatePrices() {
        priceSUT = new Price.Builder()
                .fromPriceDB(priceDB1)
                .build();
        anotherPriceSUTButNotThatImportant = new Price.Builder()
                .fromPriceDB(priceDB2)
                .build();
    }

    private void instantiatePriceEntities() {
        priceDB1 = new com.jlalias.prices.infrastructure.db.Price.Builder()
                .withBrandId(BRAND_ID)
                .withStartDate(START_DATE_1)
                .withEndDate(END_DATE_1)
                .withProductId(PRODUCT_ID)
                .withPriceList(PRICE_LIST_ID_1)
                .withPriority(0)
                .withPrice(12.50)
                .withCurrency(EUR_CURRENCY)
                .build();
        priceDB2 = new com.jlalias.prices.infrastructure.db.Price.Builder()
                .withBrandId(BRAND_ID)
                .withStartDate(START_DATE_2)
                .withEndDate(END_DATE_2)
                .withProductId(PRODUCT_ID)
                .withPriceList(PRICE_LIST_ID_2)
                .withPriority(1)
                .withPrice(21.50)
                .withCurrency(EUR_CURRENCY)
                .build();
    }
}