package com.example.multi_currency_expense_splitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.regex.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CnbRateDto(String validFor,
                  int amount,
                  String currencyCode,
                  BigDecimal rate) {

    private static final Pattern ISO_CODE = Pattern.compile("[A-Z]{3}");

    public CnbRateDto {
        Objects.requireNonNull(validFor, "validFor");
        Objects.requireNonNull(currencyCode, "currencyCode");
        Objects.requireNonNull(rate, "rate");
        if (!ISO_CODE.matcher(currencyCode).matches())
            throw new IllegalArgumentException("bad currency code: " + currencyCode);
        if (amount <= 0)
            throw new IllegalArgumentException("amount must be > 0 for " + currencyCode);
        if (rate.signum() <= 0)
            throw new IllegalArgumentException("rate must be > 0 for " + currencyCode);
        rate = rate.setScale(3, RoundingMode.UNNECESSARY);
    }
}
