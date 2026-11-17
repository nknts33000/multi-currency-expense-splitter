package com.example.multi_currency_expense_splitter.exception;

public class BadRatesResponse extends RuntimeException {
    public BadRatesResponse(String message) {
        super(message);
    }
}
