package com.jlalias.prices.application.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jlalias.prices.domain.service.ValidPriceSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceEvaluatorRestApiController.class)
class PriceEvaluatorRestApiControllerTest {
    private static final String PICK_DATE_1 = "2020-06-14T10:00:00";
    private static final String PICK_DATE_2 = "2020-06-14T16:00:00";
    private static final String PICK_DATE_3 = "2020-06-14T21:00:00";
    private static final String PICK_DATE_4 = "2020-06-15T10:00:00";
    private static final String PICK_DATE_5 = "2020-06-16T21:00:00";
    private static final String PRODUCT_ID = "35455";
    private static final String BRAND_ID = "1";
    private static final LocalDateTime START_DATE_1 = LocalDateTime.parse("2020-06-14T10:00:00");
    private static final LocalDateTime START_DATE_2 = LocalDateTime.parse("2020-06-14T14:00:00");
    private static final LocalDateTime END_DATE_1 = LocalDateTime.parse("2020-12-31T23:59:59");
    private static final LocalDateTime END_DATE_2 = LocalDateTime.parse("2020-06-15T10:00:00");
    private static final String PRICE_LIST_ID_1 = "1";
    private static final String PRICE_LIST_ID_2 = "2";
    private static final String EUR_CURRENCY = "EUR";
    private static final String PRODUCT_ID_JSON_STRING = "$.productId";
    private static final String BRAND_ID_JSON_STRING = "$.brandId";
    private static final String PRICE_ID_JSON_STRING = "$.priceListId";
    private static final String VALIDITY_PERIOD_JSON_STRING = "$.validityPeriod";
    private static final String VALID_PRICE_JSON_STRING = "$.validPrice";
    private static final String PRICE_NOT_FOUND_MESSAGE = "Couldn't find any price row for productId = %s and brandId = %s";

    private static com.jlalias.prices.domain.Price price1;
    private static com.jlalias.prices.domain.Price price2;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ValidPriceSelector validPriceSelectorMock;

    @ParameterizedTest
    @MethodSource("returnBatchOfInputs")
    void pickValidPrice_shouldReturnPrice_whenPriceExists(String inputDate, 
                                                                     com.jlalias.prices.domain.Price priceReturned) 
            throws Exception {
        when(validPriceSelectorMock.pickValidPrice(LocalDateTime.parse(inputDate), PRODUCT_ID, BRAND_ID)).
                thenReturn(Optional.of(priceReturned));
        String validityPeriod = priceReturned.toDTOResponse().getValidityPeriod();
        String validPrice = priceReturned.toDTOResponse().getValidPrice();
        mvc.perform( MockMvcRequestBuilders
                        .get("/prices/getValidPrice")
                        .param("applicabilityDate", inputDate)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(PRODUCT_ID_JSON_STRING).exists())
                .andExpect(MockMvcResultMatchers.jsonPath(PRODUCT_ID_JSON_STRING).isString())
                .andExpect(MockMvcResultMatchers.jsonPath(PRODUCT_ID_JSON_STRING).value(PRODUCT_ID))
                .andExpect(MockMvcResultMatchers.jsonPath(BRAND_ID_JSON_STRING).exists())
                .andExpect(MockMvcResultMatchers.jsonPath(BRAND_ID_JSON_STRING).isString())
                .andExpect(MockMvcResultMatchers.jsonPath(BRAND_ID_JSON_STRING).value(BRAND_ID))
                .andExpect(MockMvcResultMatchers.jsonPath(PRICE_ID_JSON_STRING).exists())
                .andExpect(MockMvcResultMatchers.jsonPath(PRICE_ID_JSON_STRING).isString())
                .andExpect(MockMvcResultMatchers.jsonPath(PRICE_ID_JSON_STRING).value(priceReturned.toDTOResponse().getPriceListId()))
                .andExpect(MockMvcResultMatchers.jsonPath(VALIDITY_PERIOD_JSON_STRING).exists())
                .andExpect(MockMvcResultMatchers.jsonPath(VALIDITY_PERIOD_JSON_STRING).isString())
                .andExpect(MockMvcResultMatchers.jsonPath(VALIDITY_PERIOD_JSON_STRING).value(validityPeriod))
                .andExpect(MockMvcResultMatchers.jsonPath(VALID_PRICE_JSON_STRING).exists())
                .andExpect(MockMvcResultMatchers.jsonPath(VALID_PRICE_JSON_STRING).isString())
                .andExpect(MockMvcResultMatchers.jsonPath(VALID_PRICE_JSON_STRING).value(validPrice));

    }

    @ParameterizedTest
    @MethodSource("returnBatchOfInputs")
    void pickValidPrice_shouldReturnEmpty_forFirstPickDate(String inputDate)
            throws Exception {
        when(validPriceSelectorMock.pickValidPrice(LocalDateTime.parse(inputDate), PRODUCT_ID, BRAND_ID)).
                thenReturn(Optional.empty());
        mvc.perform( MockMvcRequestBuilders
                        .get("/prices/getValidPrice")
                        .param("applicabilityDate", inputDate)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
    
    private static Stream<Arguments> returnBatchOfInputs() {
        instantiatePrices();
        return Stream.of(
                arguments(PICK_DATE_1, price1),
                arguments(PICK_DATE_2, price2),
                arguments(PICK_DATE_3, price2),
                arguments(PICK_DATE_4, price1),
                arguments(PICK_DATE_5, price1));
    }

    private static void instantiatePrices() {
        price1 = new com.jlalias.prices.domain.Price.Builder()
                .fromPriceDB(new com.jlalias.prices.infrastructure.db.Price.Builder()
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
                .fromPriceDB(new com.jlalias.prices.infrastructure.db.Price.Builder()
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

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}