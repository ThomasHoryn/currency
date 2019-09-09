package com.tomhor.currency.service;

import com.tomhor.currency.model.ExchangeRates;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public interface ExchangeRatesService {
    ExchangeRates findByCode(String code);
    ExchangeRates saveOrUpdate(ExchangeRates exchangeRates);
    Set<ExchangeRates> findByDate(LocalDate date);
    void findTodayExchangeRatesAndSaveToDB();
    Boolean isTodayExchangeRates();
}
