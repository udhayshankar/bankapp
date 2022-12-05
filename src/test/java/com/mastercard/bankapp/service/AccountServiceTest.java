package com.mastercard.bankapp.service;

import com.mastercard.bankapp.models.Account;
import com.mastercard.bankapp.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void findAccountByCustomerIdTest(){
        Optional<Account> accStub = createAccountStub();
        when(accountRepository.findById(accStub.get().getUserId())).thenReturn(accStub);
        Optional<Account> acc = accountService.findAccountByCustomerId(accStub.get().getUserId());
        Assertions.assertEquals(acc.get().getBalance(),accStub.get().getBalance());
    }

    @Test
    public void createAccountTest(){
        Optional<Account> accStub = createAccountStub();
        when(accountRepository.save(accStub.get())).thenReturn(accStub.get());
       Account acc =  accountService.createAccount(accStub.get());
       Assertions.assertEquals(acc.getBalance(),accStub.get().getBalance());
    }

    @Test
    public void fetchAllAccountsTest(){
        List<Account> accountStubList = createAccountListStub();
        when(accountRepository.findAll()).thenReturn(createAccountListStub());
        List<Account> accountList = accountService.fetchAllAccounts();
        Assertions.assertEquals(accountList.size(),accountStubList.size());
    }

    private Optional<Account> createAccountStub(){
        Account accountStub = Account.builder()
                .customerId(3L)
                .userId("D0G3AS4")
                .balance(1000L)
                .accountNumber("8303551299022129")
                .branchId("BR285")
                .accountTypeId("ACCTYP569")
                .currencyId("CURR799")
                .accountStatusId("ACCSTAT925")

                .build();
        return Optional.of(accountStub);
    }
    private List<Account> createAccountListStub(){
        List<Account> acc =  new ArrayList<Account>();
        acc.add(createAccountStub().get());
        return acc;
    }
}
