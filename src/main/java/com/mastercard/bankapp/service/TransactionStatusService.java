package com.mastercard.bankapp.service;

import com.mastercard.bankapp.constants.Constants;
import com.mastercard.bankapp.constants.Helper;
import com.mastercard.bankapp.models.TransactionStatus;
import com.mastercard.bankapp.repository.TransactionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionStatusService {

    @Autowired
    private TransactionStatusRepository  transactionStatusRepository;

    public TransactionStatus createTransactionStatus(TransactionStatus transactionStatus){
        transactionStatus.setTransactionStatusCode(Constants.TRANSACTION_STATUS_CODE+ Helper.generateRandomValues(Constants.NUMERIC_CONSTANTS,Constants.LENGTH));
        return transactionStatusRepository.save(transactionStatus);

    }

    public Optional<TransactionStatus> getByTransactionStatusId(String transactionStatusId){
        return transactionStatusRepository.findById(transactionStatusId);
    }

    public Optional<TransactionStatus> getByTransactionStatusDesc(String transactionStatusDescription){
        return transactionStatusRepository.findByTransactionStatusDescription(transactionStatusDescription);
    }

    public List<TransactionStatus> fetchAllTransactionStatuses(){
        return transactionStatusRepository.findAll();
    }
}
