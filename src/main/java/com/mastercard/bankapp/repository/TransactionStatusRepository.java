package com.mastercard.bankapp.repository;

import com.mastercard.bankapp.models.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus,String> {
//    @Query("SELECT * FROM TRANSACTION_STATUS WHERE transactionStatusCode")
Optional<TransactionStatus> findByTransactionStatusDescription(String transactionStatusDescription);
}
