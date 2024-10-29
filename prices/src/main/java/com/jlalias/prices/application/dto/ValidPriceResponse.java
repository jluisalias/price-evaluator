package com.jlalias.prices.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * ValidPriceResponse This is a copy of the ValidPriceResponse generated from OpenAPI
 */
public class ValidPriceResponse {

  private final String productId;

  private final String brandId;

  private final String priceListId;

  private final String validityPeriod;

  private final String validPrice;

  public ValidPriceResponse(String productId, String brandId, String priceListId, String validityPeriod, String validPrice) {
    this.productId = productId;
    this.brandId = brandId;
    this.priceListId = priceListId;
    this.validityPeriod = validityPeriod;
    this.validPrice = validPrice;
  }

  /**
   * Get productId
   * @return productId
   */
  @Schema(name = "productId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productId")
  public String getProductId() {
    return productId;
  }
  
  /**
   * Get brandId
   * @return brandId
   */
  @Schema(name = "brandId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("brandId")
  public String getBrandId() {
    return brandId;
  }

  /**
   * Get priceListId
   * @return priceListId
   */
  @Schema(name = "priceListId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("priceListId")
  public String getPriceListId() {
    return priceListId;
  }

  /**
   * Get validityPeriod
   * @return validityPeriod
   */
  @Schema(name = "validityPeriod", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("validityPeriod")
  public String getValidityPeriod() {
    return validityPeriod;
  }
  
  /**
   * Get validPrice
   * @return validPrice
   */
  @Schema(name = "validPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("validPrice")
  public String getValidPrice() {
    return validPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidPriceResponse validPriceResponse = (ValidPriceResponse) o;
    return Objects.equals(this.productId, validPriceResponse.productId) &&
        Objects.equals(this.brandId, validPriceResponse.brandId) &&
        Objects.equals(this.priceListId, validPriceResponse.priceListId) &&
        Objects.equals(this.validityPeriod, validPriceResponse.validityPeriod) &&
        Objects.equals(this.validPrice, validPriceResponse.validPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, brandId, priceListId, validityPeriod, validPrice);
  }

  @Override
  public String toString() {
    return "class ValidPriceResponse {\n" +
            "    productId: " + toIndentedString(productId) + "\n" +
            "    brandId: " + toIndentedString(brandId) + "\n" +
            "    priceListId: " + toIndentedString(priceListId) + "\n" +
            "    validityPeriod: " + toIndentedString(validityPeriod) + "\n" +
            "    validPrice: " + toIndentedString(validPrice) + "\n" +
            "}";
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

