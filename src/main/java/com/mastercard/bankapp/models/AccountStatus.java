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
public class AccountStatus {
    @Id
    @Column(nullable = false)
    private String accountStatusId;

    @Column(nullable = false)
    private String accountStatusDescription;


}
