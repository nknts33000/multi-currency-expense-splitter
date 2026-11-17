package com.example.multi_currency_expense_splitter.service;

import com.example.multi_currency_expense_splitter.component.RatesClient;
import com.example.multi_currency_expense_splitter.dto.CnbRateDto;
import com.example.multi_currency_expense_splitter.dto.CnbRatesResponse;
import com.example.multi_currency_expense_splitter.entity.CnbRate;
import com.example.multi_currency_expense_splitter.mapper.CnbRateMapper;
import com.example.multi_currency_expense_splitter.repository.CnbRateRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CnbRateService {

    private final CnbRateMapper cnbRateMapper;
    private final CnbRateRepository cnbRateRepository;
    private final RatesClient ratesClient;

    @Autowired
    public CnbRateService(CnbRateMapper cnbRateMapper, CnbRateRepository cnbRateRepository, RatesClient ratesClient){
        this.cnbRateMapper = cnbRateMapper;
        this.cnbRateRepository = cnbRateRepository;
        this.ratesClient = ratesClient;
    }

    private List<CnbRateDto> retrieveCurrentRates(){
        CnbRatesResponse ratesResponse = ratesClient.currentCnbRates();
        if (ratesResponse == null || ratesResponse.rates() == null) {
            return Collections.emptyList();
        }
        return ratesResponse.rates();
    }

    public List<CnbRate> saveRetrievedRates(){
        List<CnbRateDto> newRateDtos = retrieveCurrentRates();
        List<CnbRate> newRates = newRateDtos.stream().map(cnbRateMapper::toEntity).toList();
        log.info("new rates: {}", newRates);
        return cnbRateRepository.saveAll(newRates);
    }
}
