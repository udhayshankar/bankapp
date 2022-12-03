package com.mastercard.bankapp.service;

import com.mastercard.bankapp.constants.Constants;
import com.mastercard.bankapp.constants.Helper;
import com.mastercard.bankapp.models.Currency;
import com.mastercard.bankapp.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public Currency createCurrency(Currency currency){
        currency.setCurrencyId(Constants.CURRENCY_CODE+ Helper.generateRandomValues(Constants.NUMERIC_CONSTANTS,Constants.LENGTH));
        return currencyRepository.save(currency);
    }

    public Optional<Currency> getByCurrencyId(String currencyId){
        return currencyRepository.findById(currencyId);
    }
}
