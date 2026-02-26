package com.app.dealanalyzer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository repository;

    public Deal requestDeal(DealRequest request){

        if(repository.existsDealByFromCurrencyAndToCurrencyAndAmount(request.getFromCurrency(),
                request.getToCurrency(),request.getDealAmount()) >= 1){
            throw new DealAlreadyExistException("There is Already A Deal With This Specifications");
        }

        Deal deal = Deal.builder()
                .fromCurrency(request.getFromCurrency())
                .toCurrency(request.getToCurrency())
                .dealAmount(request.getDealAmount())
                .build();

        return repository.save(deal);
    }
}
