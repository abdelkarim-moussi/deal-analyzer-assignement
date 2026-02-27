package com.app.dealanalyzer.service;

import com.app.dealanalyzer.entity.Deal;
import com.app.dealanalyzer.repository.DealRepository;
import com.app.dealanalyzer.dto.DealRequest;
import com.app.dealanalyzer.exception.CurrenciesMatchException;
import com.app.dealanalyzer.exception.DealAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {

    private final DealRepository repository;

    public Deal requestDeal(DealRequest request){
        log.info("requesting a deal");

        checkCurrencyMatch(request);

        boolean exists = repository.existsById(request.getId());

        if(exists){
            throw new DealAlreadyExistsException("There is Already A Deal With This Id : "+request.getId());
        }

        Deal deal = Deal.builder()
                .id(request.getId())
                .fromCurrency(request.getFromCurrency())
                .toCurrency(request.getToCurrency())
                .dealAmount(request.getDealAmount())
                .build();

        return repository.save(deal);
    }

    private void checkCurrencyMatch(DealRequest request){
        if(request.getFromCurrency().equals(request.getToCurrency())){
            log.error("From currency {} must be deffer from to currency {}",
                    request.getFromCurrency() ,request.getToCurrency());

            throw new CurrenciesMatchException("from currency can not be converted to it self");
        }
    }
}
