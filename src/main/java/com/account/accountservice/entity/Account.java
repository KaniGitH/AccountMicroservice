package com.account.accountservice.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
// table name in DB
@Table(name = "ACCOUNT_TBL")
public class Account {

    // Declare variable - accountId
    // primary key
    @Id
    // auto generate id value by 1 - GenerationType.IDENTITY - current table id increment only
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // column name in table
    @Column(name = "Account_Id")
    private int accountId;

    // Declare variable - customerId
    // column name in table
    @Column(name = "Customer_Id")
    private int customerId;

    // Declare variable - accountHolderName
    // column name in table
    @Column(name = "Account_HolderName")
    private String accountHolderName;

    // Declare variable - accountHolderNicName
    // column name in table
    @Column(name = "Account_HolderNicName")
    private String accountHolderNicName;

    // Declare variable - accountNumber
    // column name in table
    @Column(name = "Account_Number", unique = true, nullable = false)
    private String accountNumber;

    // Declare variable - accountType
    // column name in table
    @Column(name = "Account_Type")
    private String accountType;

    // Declare variable - accountBalance
    // column name in table
    @Column(name = "Account_Balance")
    private double accountBalance;

    // Declare variable - accountStatus
    // column name in table
    @Column(name = "Account_Status")
    private String accountStatus;

}
