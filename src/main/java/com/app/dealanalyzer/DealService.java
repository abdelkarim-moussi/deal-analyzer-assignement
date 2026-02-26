package com.app.dealanalyzer;

import com.app.dealanalyzer.exception.DealAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealService {

    private final DealRepository repository;

    public Deal requestDeal(DealRequest request){
        log.info("requesting a deal");

        if(repository.existsDealByFromCurrencyAndToCurrencyAndAmount(request.getFromCurrency(),
                request.getToCurrency(),request.getDealAmount()) >= 1){
            throw new DealAlreadyExistsException("There is Already A Deal With This Specifications");
        }

        Deal deal = Deal.builder()
                .fromCurrency(request.getFromCurrency())
                .toCurrency(request.getToCurrency())
                .dealAmount(request.getDealAmount())
                .build();

        return repository.save(deal);
    }
}
