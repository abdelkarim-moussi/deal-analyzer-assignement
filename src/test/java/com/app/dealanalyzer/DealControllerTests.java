package com.app.dealanalyzer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DealControllerTests {

    @Mock
    private DealService service;

    @InjectMocks
    private DealController controller;

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
    public void requestDealShouldCallServiceAndReturnDeal(){
        when(service.requestDeal(request)).thenReturn(deal);

        ResponseEntity<Deal> result = controller.requestDeal(request);

        assertNotNull(result.getBody());
        assertEquals(HttpStatusCode.valueOf(200),result.getStatusCode());
        verify(service,times(1)).requestDeal(request);
    }
}
