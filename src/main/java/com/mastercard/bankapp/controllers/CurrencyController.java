package com.mastercard.bankapp.controllers;


import com.mastercard.bankapp.models.Currency;
import com.mastercard.bankapp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("create")
    public Currency createCurrency(@RequestBody Currency currency){
        return currencyService.createCurrency(currency);
    }

    @GetMapping("/{currencyId}")
    public Optional<Currency> getByCurrencyId(@PathVariable String currencyId){
        return currencyService.getByCurrencyId(currencyId);
    }
}
