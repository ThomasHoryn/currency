package com.tomhor.currency.service;

import com.tomhor.currency.model.ExchangeRates;
import com.tomhor.currency.model.ExchangeValue;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class ExchangeValueServiceImpl implements ExchangeValueService {
    private final ExchangeRatesService exchangeRatesService;

    public ExchangeValueServiceImpl(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @Override
    public ExchangeValue count(String fromCode, String toCode, float quantity) {
        ExchangeRates exchangeRatesFrom = exchangeRatesService.findByCode(fromCode);
        ExchangeRates exchangeRatesTo = exchangeRatesService.findByCode(toCode);

        BigDecimal value = BigDecimal.valueOf((exchangeRatesFrom.getMid() / exchangeRatesTo.getMid()) * quantity);

        ExchangeValue exchangeValue = new ExchangeValue();
        exchangeValue.setFrom(exchangeRatesFrom.getCode());
        exchangeValue.setTo(exchangeRatesTo.getCode());
        exchangeValue.setQuantity(quantity);
        exchangeValue.setValue(value);

        return exchangeValue;
    }
}
