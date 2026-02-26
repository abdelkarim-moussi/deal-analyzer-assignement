package com.app.dealanalyzer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface dealRepository extends JpaRepository<String,Deal> {
}
