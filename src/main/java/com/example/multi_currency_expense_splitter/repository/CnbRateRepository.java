package com.example.multi_currency_expense_splitter.repository;

import com.example.multi_currency_expense_splitter.entity.CnbRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnbRateRepository extends JpaRepository<CnbRate, Long> {
}
