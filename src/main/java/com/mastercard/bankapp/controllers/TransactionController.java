package com.mastercard.bankapp.controllers;

import com.mastercard.bankapp.models.Transaction;
import com.mastercard.bankapp.models.enums.TransactionStatusEnum;
import com.mastercard.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity transferAmount(@RequestBody Transaction transaction)  {
        try {
            String res = transactionService.transferAmount(transaction);
            if(!res.equalsIgnoreCase(TransactionStatusEnum.SUCCESS.toString()))
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, res);

        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Transaction Successful");
    }

    @GetMapping("/{transactionId}")
    public Optional<Transaction> findById(@PathVariable String transactionId){
        return transactionService.findByID(transactionId);
    }

    @GetMapping("/all")
    public List<Transaction> fetchAllTransaction(){
        return transactionService.fetchAllTransactions();
    }
}
