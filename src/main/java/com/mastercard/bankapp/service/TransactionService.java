package com.mastercard.bankapp.service;

import com.mastercard.bankapp.constants.Constants;
import com.mastercard.bankapp.constants.Helper;
import com.mastercard.bankapp.exceptions.CustomException;
import com.mastercard.bankapp.models.Account;
import com.mastercard.bankapp.models.Transaction;

import com.mastercard.bankapp.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpServerErrorException;


import javax.persistence.*;
import javax.transaction.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionStatusService transactionStatusService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EntityManager em;


    @Transactional
    public String transferAmount(Transaction transaction) throws Exception {
        try {
            //Generating transaction id
            transaction.setTransactionId(Constants.TRANSACTION_CODE + Helper.generateRandomValues(Constants.NUMERIC_CONSTANTS, Constants.TRANSACTION_CODE_LENGTH));

            //storing transaction date and time
            transaction.setTransactionDateTime(new Date().toString());
            //updating transaction to IN PROGRESS
            transaction.setTransactionStatusCode(transactionStatusService.getByTransactionStatusDesc("In progress").get().getTransactionStatusCode());


            //fetching sender and receiver account details

//            Account senderAccount = em.find(Account.class,transaction.getSenderUserId());
//            Account receiverAccount = em.find(Account.class,transaction.getReceiverUserId());
//
//            em.lock(senderAccount, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
//            em.lock(receiverAccount, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            Account senderAccount = accountService.findOptimisticLock(transaction.getSenderUserId());
            Account receiverAccount = accountService.findOptimisticLock(transaction.getReceiverUserId());

            System.out.println("Values start");
            System.out.println(senderAccount.getVersion());
            System.out.println(receiverAccount.getVersion());
            System.out.println(accountService.findAccountByCustomerId(transaction.getSenderUserId()).get().getVersion());
            System.out.println(accountService.findAccountByCustomerId(transaction.getReceiverUserId()).get().getVersion());
            System.out.println("Values endd");

            transaction = transactionRepository.saveAndFlush(transaction);
            Thread.sleep(15000);
            //performing transaction
            if(transaction.getTransactionAmount() <= senderAccount.getBalance()){
                    Long senderBalance = senderAccount.getBalance() - transaction.getTransactionAmount();
                    Long receiverBalance = receiverAccount.getBalance() + transaction.getTransactionAmount();
                    senderAccount.setBalance(senderBalance);
                    receiverAccount.setBalance(receiverBalance);



                //saving the transaction status code
                    transaction.setTransactionStatusCode(transactionStatusService.getByTransactionStatusDesc("Success").get().getTransactionStatusCode());

                return "Transaction Successful";
            }
            else
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,"Insufficient Balance on Sender Account");

        }catch(OptimisticLockException ex){
            transaction.setTransactionStatusCode(transactionStatusService.getByTransactionStatusDesc("Failed").get().getTransactionStatusCode());
            System.out.println("OptimisticLockException caught");


        }
        catch (Exception ex){
            transaction.setTransactionStatusCode(transactionStatusService.getByTransactionStatusDesc("Failed").get().getTransactionStatusCode());
            System.out.println("Exception caught successfullyyyyy");



        }
        finally {
            System.out.println("finally"+transaction.toString());
            transactionRepository.saveAndFlush(transaction);


        }

        return null;
    }

    public Optional<Transaction> findByID(String id){
        return transactionRepository.findById(id);
    }

    public List<Transaction> fetchAllTransactions(){
        return transactionRepository.findAll();
    }


}
