package com.jlalias.prices.infrastructure.db;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="brand_id")
    private final String brandId;

    @Column(name="start_date")
    private final LocalDateTime startDate;

    @Column(name="end_date")
    private final LocalDateTime endDate;

    @Column(name="product_id")
    private final String productId;

    @Column(name="price_list")
    private final String priceList;

    @Column(name="priority")
    private final Integer priority;

    @Column(name="price")
    private final Double price;

    @Column(name="currency")
    private final String currency;

    public Price(Builder builder) {
        this.brandId = builder.brandId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.productId = builder.productId;
        this.priceList = builder.priceList;
        this.priority = builder.priority;
        this.price = builder.price;
        this.currency = builder.currency;
    }

    protected Price() {
        this.id = UUID.randomUUID();
        this.brandId = "";
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now();
        this.productId = "";
        this.priceList = "";
        this.priority = 0;
        this.price = 0.0;
        this.currency = "";
    }

    public UUID getId() {
        return id;
    }

    public String getBrandId() {
        return brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceList() {
        return priceList;
    }

    public Integer getPriority() {
        return priority;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "PriceRow{" +
                "id=" + id +
                ", brandId='" + brandId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", productId='" + productId + '\'' +
                ", price_list='" + priceList + '\'' +
                ", priority=" + priority +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }

    public static class Builder {
        private String brandId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String productId;
        private String priceList;
        private Integer priority;
        private Double price;
        private String currency;

        public Builder withBrandId(String brandId) {
            this.brandId = brandId;
            return this;
        }

        public Builder withStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder withPriceList(String priceList) {
            this.priceList = priceList;
            return this;
        }

        public Builder withPriority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Price build() {
            return new Price(this);
        }
    }
}
