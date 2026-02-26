package com.app.dealanalyzer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fromCurrency;
    private String toCurrency;
    @CreationTimestamp
    private LocalDateTime dealTimestamp;
    private BigDecimal dealAmount;
}
