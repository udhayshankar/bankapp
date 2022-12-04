package com.mastercard.bankapp.service;

import com.mastercard.bankapp.constants.Constants;
import com.mastercard.bankapp.constants.Helper;
import com.mastercard.bankapp.models.TransactionType;
import com.mastercard.bankapp.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository  transactionTypeRepository;

    public TransactionType createTransactionType(TransactionType transactionType){
        transactionType.setTransactionTypeCode(Constants.TRANSACTION_TYPE_CODE+Helper.generateRandomValues(Constants.NUMERIC_CONSTANTS,Constants.LENGTH));
        return transactionTypeRepository.save(transactionType);
    }

    public Optional<TransactionType> getByTransactionTypeId(String transactionTypeId){
        return transactionTypeRepository.findById(transactionTypeId);
    }
}
