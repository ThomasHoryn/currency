package com.tomhor.currency.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "api.nbp.pl", url = "http://api.nbp.pl")
public interface CurrencyExchangeServiceProxy {
    @GetMapping("api/exchangerates/tables/A?format=json")
    public String retrieveExchangeRates();
}
