package com.mastercard.bankapp.service;

import com.mastercard.bankapp.constants.Constants;
import com.mastercard.bankapp.constants.Helper;
import com.mastercard.bankapp.models.AccountStatus;
import com.mastercard.bankapp.repository.AccountStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountStatusService {
    @Autowired
    private AccountStatusRepository accountStatusRepository;

    public AccountStatus createAccountStatus(AccountStatus accountStatus){
        accountStatus.setAccountStatusId(Constants.ACCOUNT_STATUS_CODE+ Helper.generateRandomValues(Constants.NUMERIC_CONSTANTS,Constants.LENGTH));
        return accountStatusRepository.saveAndFlush(accountStatus);
    }

    public Optional<AccountStatus> findByAccountStatusId(String accountStatusId){
        return accountStatusRepository.findById(accountStatusId);
    }
}
