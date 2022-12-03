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
public class AccountType {
    @Id
    @Column(nullable = false)
    private String accountTypeId;

    @Column(nullable = false)
    private String accountTypeDescription;


}
