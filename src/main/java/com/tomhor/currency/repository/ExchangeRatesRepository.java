package com.tomhor.currency.repository;

import com.tomhor.currency.model.ExchangeRates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ExchangeRatesRepository extends CrudRepository<ExchangeRates, Long> {
    Optional<ExchangeRates> findByCode(String code);
    Set<ExchangeRates> findByDate(LocalDate date);
}
