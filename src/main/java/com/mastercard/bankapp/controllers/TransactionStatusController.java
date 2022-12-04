package com.mastercard.bankapp.controllers;

import com.mastercard.bankapp.models.TransactionStatus;
import com.mastercard.bankapp.service.TransactionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/transactionstatus")
public class TransactionStatusController {

    @Autowired
    private TransactionStatusService transactionStatusService;

    @PostMapping("/create")
    public TransactionStatus create(@RequestBody TransactionStatus transactionStatus){
        return transactionStatusService.createTransactionStatus(transactionStatus);
    }

    @GetMapping("/{transactionStatusId}")
    public Optional<TransactionStatus> getByTransactionStatusId(@PathVariable String transactionStatusId){
        return transactionStatusService.getByTransactionStatusId(transactionStatusId);
    }

    @GetMapping("all")
    public List<TransactionStatus> fetchAllTransactionStatus(){
        return transactionStatusService.fetchAllTransactionStatuses();
    }


}
