package com.jlalias.prices.application.adapter;

import com.jlalias.prices.application.dto.PriceNotFoundError;
import com.jlalias.prices.application.dto.ValidPriceResponse;
import com.jlalias.prices.application.port.PriceEvaluatorApi;
import com.jlalias.prices.domain.Price;
import com.jlalias.prices.domain.service.ValidPriceSelector;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/prices/getValidPrice")
public class PriceEvaluatorRestApiController implements PriceEvaluatorApi {

    private final ValidPriceSelector validPriceSelector;
    
    @Autowired
    public PriceEvaluatorRestApiController(ValidPriceSelector validPriceSelector) {
        this.validPriceSelector = validPriceSelector;
    }

    /**
     * GET /prices/getValidPrice : Get applicable price for a product
     * Get applicable price for a product in a particular date and for a brand.
     *
     * @param applicabilityDate The date to check the price for (required)
     * @param productId         The ID of the product (required)
     * @param brandId           (required)
     * @return Applicable price found! (status code 200)
     * or Price not found for given parameters (status code 404)
     */
    @Operation(
            operationId = "getValidPrice", summary = "Get applicable price for a product",
            description = "Get applicable price for a product in a particular date and for a brand.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Applicable price found!", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ValidPriceResponse.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Price not found for given parameters", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PriceNotFoundError.class))
                    })
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidPriceResponse> getValidPrice(
            @NotNull @Parameter(name = "applicabilityDate", description = "The date to check the price for", required = true, in = ParameterIn.QUERY)
            @Valid @RequestParam(value = "applicabilityDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicabilityDate,
            @NotNull @Parameter(name = "productId", description = "The ID of the product", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "productId", required = true) String productId,
            @NotNull @Parameter(name = "brandId", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "brandId", required = true) String brandId) {
        Optional<Price> optionalPrice = validPriceSelector.pickValidPrice(applicabilityDate, productId, brandId);

        return optionalPrice
                .map(priceResponse -> ResponseEntity.ok(priceResponse.toDTOResponse()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
