package com.example.multi_currency_expense_splitter.scheluder;

import com.example.multi_currency_expense_splitter.service.CnbRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CnbRateFetchJob {
    private static final Logger log = LoggerFactory.getLogger(CnbRateFetchJob.class);
    private final CnbRateService cnbRateService;

    CnbRateFetchJob(CnbRateService cnbRateService) {
        this.cnbRateService = cnbRateService;
    }

    @Scheduled(cron = "0 45 14 * * MON-FRI", zone = "Europe/Prague")
    public void fetchRates(){
        try{
            cnbRateService.saveRetrievedRates();
            log.info("CNB rate fetched");
        }catch (RuntimeException e){
            log.error("CNB rate fetch failed", e);
        }
    }
}
