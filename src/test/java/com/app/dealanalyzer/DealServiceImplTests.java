package com.app.dealanalyzer;

import com.app.dealanalyzer.dto.DealRequest;
import com.app.dealanalyzer.entity.Deal;
import com.app.dealanalyzer.exception.DealAlreadyExistsException;
import com.app.dealanalyzer.repository.DealRepository;
import com.app.dealanalyzer.service.DealServiceImpl;
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
public class DealServiceImplTests {

    @Mock
    private DealRepository repository;

    @InjectMocks
    private DealServiceImpl service;

    private DealRequest request;
    private Deal deal;

    @BeforeEach
    void setUp(){
        request = DealRequest.builder()
                .id("deal_1")
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
        when(repository.existsById(request.getId())).thenReturn(false);

        when(repository.save(any(Deal.class))).thenReturn(deal);

        Deal result = service.requestDeal(request);

        assertNotNull(result);
        assertEquals("deal_1", result.getId());
        verify(repository,times(1)).save(any(Deal.class));
    }

    @Test
    public void requestDealShouldThrowDealAlreadyExistsException() {
        when(repository.existsById(request.getId())).thenReturn(true);

        DealAlreadyExistsException exception = assertThrows(DealAlreadyExistsException.class,() -> service.requestDeal(request));

        assertEquals("There is Already A Deal With This Id : "+request.getId(),exception.getMessage());
    }
}
