package com.example.multi_currency_expense_splitter.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CNB_RATES")
@Getter
@Setter
public class CnbRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDate validFor;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private String currencyCode;
    @Column(nullable = false)
    private BigDecimal rate;
    @Column(nullable = false)
    private Instant fetchedAt;

    public CnbRate(LocalDate validFor, int amount, String currencyCode, BigDecimal rate){
        this.validFor = validFor;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.rate = rate;
        this.fetchedAt = Instant.now();
    }
}
