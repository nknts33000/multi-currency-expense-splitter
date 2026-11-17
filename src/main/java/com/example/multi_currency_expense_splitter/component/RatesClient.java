package com.example.multi_currency_expense_splitter.component;

import com.example.multi_currency_expense_splitter.dto.CnbRatesResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RatesClient {
    private final RestClient restClient;


    public RatesClient(RestClient restClient){
        this.restClient = restClient;
    }

    public CnbRatesResponse currentCnbRates(){
        return restClient
                .get()
                .uri("/cnbapi/exrates/daily")
                .retrieve().body(CnbRatesResponse.class);
    }
}
