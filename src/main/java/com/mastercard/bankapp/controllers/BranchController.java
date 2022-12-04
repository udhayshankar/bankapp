package com.mastercard.bankapp.controllers;

import com.mastercard.bankapp.models.Branch;
import com.mastercard.bankapp.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("/create")
    public Branch createBranch(@RequestBody  Branch branch){
        return branchService.createBranch(branch);
    }

        @GetMapping("/{branchId}")
    public Optional<Branch> findByBranchId(@PathVariable String branchId){
        return branchService.findByBranchId(branchId);
    }

    @GetMapping("all")
    public List<Branch> fetchAllBranches(){
        return branchService.fetchAllBranches();
    }
}
