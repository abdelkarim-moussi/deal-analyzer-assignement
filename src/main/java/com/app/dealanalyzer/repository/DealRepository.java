package com.app.dealanalyzer.repository;

import com.app.dealanalyzer.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal,String> {}
