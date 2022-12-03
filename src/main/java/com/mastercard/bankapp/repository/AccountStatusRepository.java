package com.mastercard.bankapp.repository;

import com.mastercard.bankapp.models.AccountStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus, String> {
}
