package com.app.dealanalyzer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class DealRequest {
    @Pattern(regexp = "[A-Z]{3}", message = "currency mast have three uppercase letters (ex: 'MAD')")
    private String fromCurrency;
    @Pattern(regexp = "[A-Z]{3}",message = "currency mast have three uppercase letters (ex: 'MAD')")
    private String toCurrency;
    @NotNull(message = "amount can not be null")
    @Min(value = 1, message = "amount must be greater then or equals 1")
    private BigDecimal dealAmount;
}
