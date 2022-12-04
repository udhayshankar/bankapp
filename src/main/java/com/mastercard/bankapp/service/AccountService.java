package com.mastercard.bankapp.service;

import com.mastercard.bankapp.constants.Constants;
import com.mastercard.bankapp.constants.Helper;
import com.mastercard.bankapp.models.Account;
import com.mastercard.bankapp.repository.AccountRepository;
import com.mastercard.bankapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.persistence.LockTimeoutException;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;



    public Account createAccount(Account account){

        account.setUserId(Helper.generateRandomValues(Constants.ALPHANUMERIC_CONSTANTS,Constants.USER_ID_LENGTH));
        account.setAccountNumber(Helper.generateRandomValues(Constants.NUMERIC_CONSTANTS,Constants.ACCOUNT_NUMBER_LENGTH));
        //setting default balance for each account when creating account for customer
        if(account.getBalance() == null)
            account.setBalance(Constants.DEFAULT_BALANCE);
        return accountRepository.save(account);
    }

    public Optional<Account> findAccountByCustomerId(String customerId)  {
        return accountRepository.findById(customerId);
    }
@Lock(LockModeType.OPTIMISTIC)
public Account findOptimisticLock(String customerId){
        return accountRepository.findById(customerId).get();
}
    public List<Account> fetchAllAccounts(){
        return accountRepository.findAll();
    }

    public void deleteAccountByCustomerId(String customerId){
         accountRepository.deleteById(customerId);
    }


}


