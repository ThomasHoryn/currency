package com.tomhor.currency.service;

import com.tomhor.currency.exception.EntityNotFoundException;
import com.tomhor.currency.model.ExchangeRates;
import com.tomhor.currency.repository.ExchangeRatesRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {
    private final ExchangeRatesRepository exchangeRatesRepository;
    private final CurrencyExchangeServiceProxy proxy;

    public ExchangeRatesServiceImpl(ExchangeRatesRepository exchangeRatesRepository, CurrencyExchangeServiceProxy proxy) {
        this.exchangeRatesRepository = exchangeRatesRepository;
        this.proxy = proxy;
    }

    @Override
    public ExchangeRates findByCode(String code) {
        Optional<ExchangeRates> exchangeRatesOptional = exchangeRatesRepository.findByCode(code);
        exchangeRatesOptional.orElseThrow(() -> new EntityNotFoundException("Nie znaleziono waluty o podanym kodzie: ! " + code));
        return exchangeRatesOptional.get();
    }

    @Override
    public ExchangeRates saveOrUpdate(ExchangeRates exchangeRates) {
        return exchangeRatesRepository.save(exchangeRates);
    }

    @Override
    public Set<ExchangeRates> findByDate(LocalDate date) {
        return exchangeRatesRepository.findByDate(date);
    }

    @Override
    public Boolean isTodayExchangeRates() {
        if (this.findByDate(LocalDate.now()).size() > 0) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    @Override
    public void findTodayExchangeRatesAndSaveToDB() {
        String response = proxy.retrieveExchangeRates();
        JSONArray tableRatesArray = new JSONArray(response);

        JSONObject tableRateObject = tableRatesArray.getJSONObject(0);
        JSONArray ratesArray = tableRateObject.getJSONArray("rates");


        for (int i =0; i < ratesArray.length(); i++){
            JSONObject rate = ratesArray.getJSONObject(i);
            ExchangeRates exchangeRates = new ExchangeRates();

            String code = rate.getString("code");
            float mid = rate.getFloat("mid");
            String currency = rate.getString("currency");

            exchangeRates.setCode(code);
            exchangeRates.setMid(mid);
            exchangeRates.setCurrency(currency);
            exchangeRates.setDate(LocalDate.now());

            this.saveOrUpdate(exchangeRates);

        }
    }
}
