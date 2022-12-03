package com.mastercard.bankapp.controllers;

import com.mastercard.bankapp.models.AccountStatus;
import com.mastercard.bankapp.service.AccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/accountstatus")
public class AccountStatusController {

    @Autowired
    private AccountStatusService accountStatusService;

    @PostMapping("/create")
    public AccountStatus createAccountStatus(@RequestBody AccountStatus accountStatus){
        return accountStatusService.createAccountStatus(accountStatus);
    }

    @GetMapping("/{accountStatusId}")
    public Optional<AccountStatus> getAccountStatus(@PathVariable String accountStatusId){
        return accountStatusService.findByAccountStatusId(accountStatusId);
    }
}
