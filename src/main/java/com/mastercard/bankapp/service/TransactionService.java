package com.mastercard.bankapp.service;

import com.mastercard.bankapp.constants.Constants;
import com.mastercard.bankapp.constants.Helper;
import com.mastercard.bankapp.models.Account;
import com.mastercard.bankapp.models.Transaction;
import com.mastercard.bankapp.models.TransactionStatus;
import com.mastercard.bankapp.models.enums.TransactionStatusEnum;
import com.mastercard.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PessimisticLockException;
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
    public void performLockAndPersist(Transaction transaction,List<TransactionStatus> transactionStatuses){

        try{
            //fetching sender and receiver account details

            Account senderAccount = em.find(Account.class,transaction.getSenderUserId());
            Account receiverAccount = em.find(Account.class,transaction.getReceiverUserId());

            em.lock(senderAccount, LockModeType.PESSIMISTIC_READ);
            em.lock(receiverAccount, LockModeType.PESSIMISTIC_READ);


            //performing transaction
            if(transaction.getTransactionAmount() <= senderAccount.getBalance()){
                System.out.println("if blcokkkkk"+transaction.toString());
                Long senderBalance = senderAccount.getBalance() - transaction.getTransactionAmount();
                Long receiverBalance = receiverAccount.getBalance() + transaction.getTransactionAmount();
                senderAccount.setBalance(senderBalance);
                receiverAccount.setBalance(receiverBalance);

                em.merge(senderAccount);
                em.merge(receiverAccount);
                Optional<TransactionStatus> successTransStatus= transactionStatuses.stream().filter(t->t.getTransactionStatusDescription().equalsIgnoreCase(String.valueOf(TransactionStatusEnum.SUCCESS))).findAny();
                //saving the transaction status code
                transaction.setTransactionStatusCode(successTransStatus.get().getTransactionStatusCode());

                System.out.println("Successful Transaction");
            }
            else {
                Optional<TransactionStatus> insuffTransStatus= transactionStatuses.stream().filter(t->t.getTransactionStatusDescription().equalsIgnoreCase(String.valueOf(TransactionStatusEnum.INSUFFICIENT_BALANCE))).findAny();
                //saving the transaction status code
                transaction.setTransactionStatusCode(insuffTransStatus.get().getTransactionStatusCode());
                System.out.println("Insuffience Balance");

            }

        }catch(PessimisticLockException ex){

            System.out.println("PessimisticLockException caught");

            Optional<TransactionStatus> failedTransStatus= transactionStatuses.stream().filter(t->t.getTransactionStatusDescription().equalsIgnoreCase(String.valueOf(TransactionStatusEnum.SUCCESS))).findAny();
            //saving the transaction status code
            transaction.setTransactionStatusCode(failedTransStatus.get().getTransactionStatusCode());
            System.out.println("pessiii"+transaction.toString());

        }
    }

    @Transactional
    public String transferAmount(Transaction transaction) throws Exception {
        List<TransactionStatus> transactionStatuses = null;

        try {
            // fetch all transaction statuses
            transactionStatuses = transactionStatusService.fetchAllTransactionStatuses();

            //Generating transaction id
            transaction.setTransactionId(Constants.TRANSACTION_CODE + Helper.generateRandomValues(Constants.NUMERIC_CONSTANTS, Constants.TRANSACTION_CODE_LENGTH));

            //storing transaction date and time
            transaction.setTransactionDateTime(new Date().toString());
            Optional<TransactionStatus> inProgressTransStatus= transactionStatuses.stream().filter(t->t.getTransactionStatusDescription().equalsIgnoreCase(String.valueOf(TransactionStatusEnum.IN_PROGRESS))).findAny();
            //updating transaction to IN PROGRESS
            transaction.setTransactionStatusCode(inProgressTransStatus.get().getTransactionStatusCode());
//
            Thread.sleep(10000);

            performLockAndPersist(transaction,transactionStatuses);


        }


        catch (Exception ex){
            System.out.println("Exception ex");
            Optional<TransactionStatus> failedTransStatus= transactionStatuses.stream().filter(t->t.getTransactionStatusDescription().equalsIgnoreCase(String.valueOf(TransactionStatusEnum.FAILED))).findAny();
            //saving the transaction status code
            transaction.setTransactionStatusCode(failedTransStatus.get().getTransactionStatusCode());

        }
        finally {
            //update the transaction status
            updateTransaction(transaction);
        }

        return transactionStatuses.stream().filter(t->t.getTransactionStatusCode().equalsIgnoreCase(transaction.getTransactionStatusCode())).findAny().get().getTransactionStatusDescription();
    }

    public Optional<Transaction> findByID(String id){
        return transactionRepository.findById(id);
    }

    public List<Transaction> fetchAllTransactions(){
        return transactionRepository.findAll();
    }

    @Transactional
    public Transaction updateTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

}
