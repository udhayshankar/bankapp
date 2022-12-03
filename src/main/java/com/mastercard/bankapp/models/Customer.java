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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long customerId;


    private String name;

    @Column(length=7)
    private String gender;

    @Column(length=10)
    private String phoneNumber;

    private String email;

    private String password;

    private String address;

    private String city;

    private String county;

    @Column(length=10)
    private String eirCode;

    private String country;


}
