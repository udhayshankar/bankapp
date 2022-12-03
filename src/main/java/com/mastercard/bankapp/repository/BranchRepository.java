package com.mastercard.bankapp.repository;

import com.mastercard.bankapp.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,String> {

}
