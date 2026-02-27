package com.app.dealanalyzer.controller;

import com.app.dealanalyzer.entity.Deal;
import com.app.dealanalyzer.dto.DealRequest;
import com.app.dealanalyzer.service.DealServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealServiceImpl service;

    @PostMapping
    public ResponseEntity<Deal> requestDeal(@RequestBody @Valid DealRequest request){
        Deal saved = service.requestDeal(request);
        return ResponseEntity.ok(saved);
    }
}
