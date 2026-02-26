package com.app.dealanalyzer;

import jakarta.validation.GroupSequence;
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
    @NotBlank
    @Pattern(regexp = "^[A-B]{3}$")
    private String fromCurrency;
    @NotBlank
    @Pattern(regexp = "[A-B]{3}")
    private String toCurrency;
    @NotNull
    @Min(value = 1)
    private BigDecimal dealAmount;
}
