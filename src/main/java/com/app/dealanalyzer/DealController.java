package com.app.dealanalyzer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService service;

    @PostMapping
    public ResponseEntity<Deal> requestDeal(@RequestBody @Valid DealRequest request){
        Deal saved = service.requestDeal(request);
        return ResponseEntity.ok(saved);
    }
}
