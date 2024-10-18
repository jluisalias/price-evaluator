package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ValidPriceResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-18T10:28:11.007157+02:00[Europe/Madrid]", comments = "Generator version: 7.8.0")
public class ValidPriceResponse {

  private String productId;

  private String brandId;

  private String priceListId;

  private String validityPeriod;

  private String validPrice;

  public ValidPriceResponse productId(String productId) {
    this.productId = productId;
    return this;
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

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public ValidPriceResponse brandId(String brandId) {
    this.brandId = brandId;
    return this;
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

  public void setBrandId(String brandId) {
    this.brandId = brandId;
  }

  public ValidPriceResponse priceListId(String priceListId) {
    this.priceListId = priceListId;
    return this;
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

  public void setPriceListId(String priceListId) {
    this.priceListId = priceListId;
  }

  public ValidPriceResponse validityPeriod(String validityPeriod) {
    this.validityPeriod = validityPeriod;
    return this;
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

  public void setValidityPeriod(String validityPeriod) {
    this.validityPeriod = validityPeriod;
  }

  public ValidPriceResponse validPrice(String validPrice) {
    this.validPrice = validPrice;
    return this;
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

  public void setValidPrice(String validPrice) {
    this.validPrice = validPrice;
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
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidPriceResponse {\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    brandId: ").append(toIndentedString(brandId)).append("\n");
    sb.append("    priceListId: ").append(toIndentedString(priceListId)).append("\n");
    sb.append("    validityPeriod: ").append(toIndentedString(validityPeriod)).append("\n");
    sb.append("    validPrice: ").append(toIndentedString(validPrice)).append("\n");
    sb.append("}");
    return sb.toString();
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

