package com.mastercard.bankapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Column(nullable = false)
    private Long customerId;

    @Id
    @Column(length = 7,nullable = false)
    private String userId;

    @Column(length = 16,nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    private String accountStatusId;

    @Column(nullable = false)
    private String branchId;

    @Column(nullable = false)
    private String accountTypeId;

    @Column(nullable = false)
    private String currencyId;





}
