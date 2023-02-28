package com.account.accountservice.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
// table name in DB
@Table(name = "CUSTOMER_TBL")
public class Customer {

    // Declare variable - customerId
    // primary key
    @Id
    // auto generate id value by 1 - GenerationType.IDENTITY - current table id increment only
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // column name in table
    @Column(name = "Customer_Id")
    private int customerId;

    // Declare variable - customerFirstName
    // column name in table
    @Column(name = "Customer_FirstName")
    private String customerFirstName;

    // Declare variable - customerLastName
    // column name in table
    @Column(name = "Customer_LastName")
    private String customerLastName;

    // Declare variable - customerGender
    // column name in table
    @Column(name = "Customer_Gender")
    private String customerGender;

    // Declare variable - customerDOB
    // column name in table
    @Column(name = "Customer_DOB")
    private String customerDOB;

    // Declare variable - customerAddress1
    // column name in table
    @Column(name = "Customer_Address1")
    private String customerAddress1;

    // Declare variable - customerAddress2
    // column name in table
    @Column(name = "Customer_Address2")
    private String customerAddress2;

    // Declare variable - customerPan
    // column name in table
    @Column(name = "Customer_Pan", unique = true)
    private String customerPan;

    // Declare variable - customerAadhar
    // column name in table
    @Column(name = "Customer_Aadhar", unique = true)
    private int customerAadhar;

    // Declare variable - customerMobileNumber
    // column name in table
    @Column(name = "Customer_MobileNumber")
    private String customerMobileNumber;

    // Declare variable - customerMailId
    // column name in table
    @Column(name = "Customer_MailId")
    private String customerMailId;

}

