package com.tomhor.currency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExchangeRates {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private String code;
    private String currency;
    private Float mid;
    private LocalDate date;
}
