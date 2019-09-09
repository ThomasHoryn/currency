package com.tomhor.currency.model;

import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeValue {
    @NotNull(message = "Podaj walutę")
    @Size(min = 3, max = 3, message = "Waluta powinna być podana w formacie trzy znakowym np PLN")
    private String from;

    @NotNull(message = "Podaj walutę")
    @Size(min = 3, max = 3, message = "Waluta powinna być podana w formacie trzy znakowym np PLN")
    private String to;

    @NotNull(message = "Podaj ilość")
    private Float quantity;

    private BigDecimal value;


}
