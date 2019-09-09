package com.tomhor.currency.controller;

import com.tomhor.currency.model.ExchangeValue;
import com.tomhor.currency.service.ExchangeRatesService;
import com.tomhor.currency.service.ExchangeValueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    private final ExchangeRatesService exchangeRatesService;
    private final ExchangeValueService exchangeValueService;

    public CurrencyExchangeController(ExchangeRatesService exchangeRatesService, ExchangeValueService exchangeValueService) {
        this.exchangeRatesService = exchangeRatesService;
        this.exchangeValueService = exchangeValueService;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}/quantity/{quantity}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to, @PathVariable float quantity)  {
        if (!exchangeRatesService.isTodayExchangeRates()){
            exchangeRatesService.findTodayExchangeRatesAndSaveToDB();
        }
        return  exchangeValueService.count(from,to,quantity);
    }
}
