package com.example.camelmicroserviceb.controller;

import com.example.camelmicroserviceb.model.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/get/from/{from}/to/{to}")
    public CurrencyExchange getCurrencyExchange(@PathVariable("from") String from, @PathVariable("to") String to){

        return new CurrencyExchange(1005L,from,to,50.5);
    }
}
