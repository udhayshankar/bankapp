package com.mastercard.bankapp.controllers;



import com.mastercard.bankapp.models.AccountType;
import com.mastercard.bankapp.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/accounttype")
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;
    @PostMapping("create")
    public AccountType createAccountType(@RequestBody AccountType accountType){

        return accountTypeService.createAccountType(accountType);
    }

    @GetMapping("/{accountTypeId}")
    public Optional<AccountType> findByAccountTypeId(@PathVariable String accountTypeId){
        return accountTypeService.getByAccountTypeId(accountTypeId);
    }
}
