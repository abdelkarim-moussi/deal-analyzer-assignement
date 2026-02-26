package com.app.dealanalyzer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface DealRepository extends JpaRepository<Deal,String> {


    @Query("SELECT COUNT(d) FROM Deal d WHERE d.fromCurrency = :fromCurrency " +
            "AND d.toCurrency = :toCurrency " +
            "AND d.dealAmount = :amount")
    int existsDealByFromCurrencyAndToCurrencyAndAmount(String fromCurrency, String toCurrency, BigDecimal amount);
}
