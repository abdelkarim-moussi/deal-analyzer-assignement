package com.app.dealanalyzer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deals")
public class Deal {
    @Id
    private String id;
    private String fromCurrency;
    private String toCurrency;
    @CreationTimestamp
    private LocalDateTime dealTimestamp;
    private BigDecimal dealAmount;
}
