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
public class Branch {

    @Id
    @Column(nullable = false)
    private String branchId;

    private String branchName;

    private String branchAddress;

    private String branchCity;

    private String branchCounty;

    private String branchCountry;

    @Column(length=10)
    private String branchEirCode;

    @Column(length=10)
    private String branchPhoneNumber;

}
