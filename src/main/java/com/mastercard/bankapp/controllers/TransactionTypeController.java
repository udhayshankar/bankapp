package com.mastercard.bankapp.controllers;

import com.mastercard.bankapp.models.TransactionType;
import com.mastercard.bankapp.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/transactiontype")
public class TransactionTypeController {

    @Autowired
    private TransactionTypeService transactionTypeService;

    @PostMapping("/create")
    public TransactionType create(@RequestBody TransactionType transactionType){
        return transactionTypeService.createTransactionType(transactionType);
    }

    @GetMapping("/{transactionTypeId}")
    public Optional<TransactionType> getByTransactionTypeId(@PathVariable  String transactionTypeId){
        return transactionTypeService.getByTransactionTypeId(transactionTypeId);
    }

}
