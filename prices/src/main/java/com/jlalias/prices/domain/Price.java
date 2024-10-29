package com.jlalias.prices.domain;

import com.jlalias.prices.application.dto.ValidPriceResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Price {
    private final String brandId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String productId;
    private final String priceList;
    private final Integer priority;
    private final Double priceValue;
    private final String currency;

    public Price(Builder builder) {
        com.jlalias.prices.infrastructure.db.Price priceDB = builder.priceDB;
        brandId = priceDB.getBrandId();
        startDate = priceDB.getStartDate();
        endDate = priceDB.getEndDate();
        productId = priceDB.getProductId();
        priceList = priceDB.getPriceList();
        priority = priceDB.getPriority();
        priceValue = priceDB.getPrice();
        currency = priceDB.getCurrency();
    }

    public boolean isPriceEnabledForTheDate(LocalDateTime dateToCheck) {
        boolean isDateAfterOrEqualsStartDate = dateToCheck.isAfter(startDate) || dateToCheck.isEqual(startDate);
        boolean isDateBeforeOrEqualsEndDate = dateToCheck.isBefore(endDate) || dateToCheck.isEqual(endDate);
        return isDateAfterOrEqualsStartDate && isDateBeforeOrEqualsEndDate;
    }

    public ValidPriceResponse getResponseFromPrice() {
        String validityPeriod = "From ".concat(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).
                concat(" to "). concat(endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String validPrice = String.format("%.2f", priceValue).concat(" ").concat(currency);
        return new ValidPriceResponse(productId, brandId, priceList, validityPeriod, validPrice);
    }

    public Integer getPriority() {
        return priority;
    }

    public static class Builder {
        private com.jlalias.prices.infrastructure.db.Price priceDB;

        public Builder fromPriceDB(com.jlalias.prices.infrastructure.db.Price priceDB) {
            this.priceDB = priceDB;
            return this;
        }

        public Price build() {
            return new Price(this);
        }
    }
}
