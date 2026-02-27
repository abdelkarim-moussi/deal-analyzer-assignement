package com.app.dealanalyzer.service;

import com.app.dealanalyzer.entity.Deal;
import com.app.dealanalyzer.dto.DealRequest;

public interface DealService {
    public Deal requestDeal(DealRequest request);
}
