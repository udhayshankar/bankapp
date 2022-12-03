package com.mastercard.bankapp.controllers;

import com.mastercard.bankapp.models.Account;
import com.mastercard.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("create")
    public Account createAccount(@RequestBody  Account account){
        return accountService.createAccount(account);

    }

    @GetMapping("{customerId}")
    public Optional<Account> getAccountByCustomerId(@PathVariable String customerId){
        return accountService.findAccountByCustomerId(customerId);
    }
}
