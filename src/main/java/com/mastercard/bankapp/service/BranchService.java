package com.mastercard.bankapp.service;

import com.mastercard.bankapp.constants.Constants;
import com.mastercard.bankapp.constants.Helper;
import com.mastercard.bankapp.models.Branch;
import com.mastercard.bankapp.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    public Branch createBranch(Branch branch){
        branch.setBranchId(Constants.BRANCH_CODE+ Helper.generateRandomValues(Constants.NUMERIC_CONSTANTS,Constants.LENGTH));
        return branchRepository.save(branch);
    }

    public Optional<Branch> findByBranchId(String branchId){
        return branchRepository.findById(branchId);
    }
}
