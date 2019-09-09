package com.tomhor.currency.service;

import com.tomhor.currency.model.ExchangeValue;
import org.springframework.stereotype.Service;

@Service
public interface ExchangeValueService {
    ExchangeValue count(String fromCode, String toCode, float quantity);
}
