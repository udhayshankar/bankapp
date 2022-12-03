package com.mastercard.bankapp.service;

import com.mastercard.bankapp.constants.Constants;
import com.mastercard.bankapp.constants.Helper;
import com.mastercard.bankapp.models.AccountType;
import com.mastercard.bankapp.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    public AccountType createAccountType(AccountType accountType){
        accountType.setAccountTypeId(Constants.ACCOUNT_TYPE_CODE+ Helper.generateRandomValues(Constants.NUMERIC_CONSTANTS,Constants.LENGTH));
        return accountTypeRepository.save(accountType);
    }

    public Optional<AccountType> getByAccountTypeId(String accountTypeId){
        return accountTypeRepository.findById(accountTypeId);
    }
}
