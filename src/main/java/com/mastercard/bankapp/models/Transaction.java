package com.mastercard.bankapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @Column(length = 13,nullable = false)
    private String transactionId;

    @Column(length = 7,nullable = false)
    private String senderUserId;

    @Column(length = 7,nullable = false)
    private String receiverUserId;

    private String transactionDateTime;

    private Long transactionAmount;

    private String transactionStatusCode;

    private String transactionTypeCode;

    private String transactionDescription;


}
