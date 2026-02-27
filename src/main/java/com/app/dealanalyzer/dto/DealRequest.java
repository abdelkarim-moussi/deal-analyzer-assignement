package com.app.dealanalyzer.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class DealRequest {
    @NotBlank
    private String id;
    @Pattern(regexp = "[A-Z]{3}", message = "currency mast have three uppercase letters (ex: 'MAD')")
    private String fromCurrency;
    @Pattern(regexp = "[A-Z]{3}",message = "currency mast have three uppercase letters (ex: 'MAD')")
    private String toCurrency;
    @NotNull(message = "amount can not be null")
    @Positive
    private BigDecimal dealAmount;
}
