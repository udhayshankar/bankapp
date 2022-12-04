package com.mastercard.bankapp.repository;

import com.mastercard.bankapp.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType,String> {
}
