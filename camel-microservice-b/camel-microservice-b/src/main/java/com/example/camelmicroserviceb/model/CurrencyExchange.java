package com.example.camelmicroserviceb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyExchange {

    private Long id;
    private String from;
    private String to;
    private Double conversionMultiple;

    @Override
    public String toString() {
        return "CurrencyExchange[id=" + id +", from=" + from  +", to=" + to +", conversionMultiple=" + conversionMultiple +"]";
    }
}
