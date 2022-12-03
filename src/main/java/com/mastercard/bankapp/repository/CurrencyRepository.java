package com.mastercard.bankapp.repository;

import com.mastercard.bankapp.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,String> {
}
