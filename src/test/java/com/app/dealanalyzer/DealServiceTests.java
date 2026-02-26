package com.app.dealanalyzer;

import com.app.dealanalyzer.exception.DealAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DealServiceTests {

    @Mock
    private DealRepository repository;

    @InjectMocks
    private DealService service;

    private DealRequest request;
    private Deal deal;

    @BeforeEach
    void setUp(){
        request = DealRequest.builder()
                .fromCurrency("MAD")
                .toCurrency("JOD")
                .dealAmount(BigDecimal.valueOf(100))
                .build();

        deal = Deal.builder()
                .id("deal_1")
                .fromCurrency("MAD")
                .toCurrency("JOD")
                .dealAmount(BigDecimal.valueOf(100))
                .build();

    }

    @Test
    public void requestDealShouldCreateDeal(){
        when(repository.existsDealByFromCurrencyAndToCurrencyAndAmount(
                request.getFromCurrency(),
                request.getToCurrency(),
                request.getDealAmount())).thenReturn(0);

        when(repository.save(any(Deal.class))).thenReturn(deal);

        Deal result = service.requestDeal(request);

        assertNotNull(result);
        assertEquals("deal_1", result.getId());
        verify(repository,times(1)).save(any(Deal.class));
    }

    @Test
    public void requestDealShouldThrowDealAlreadyExistsException() {
        when(repository.existsDealByFromCurrencyAndToCurrencyAndAmount(
                request.getFromCurrency(),
                request.getToCurrency(),
                request.getDealAmount())).thenReturn(1);

        DealAlreadyExistsException exception = assertThrows(DealAlreadyExistsException.class,() -> service.requestDeal(request));

        assertEquals("There is Already A Deal With This Specifications",exception.getMessage());
    }
}
