package com.account.accountservice.service;


import com.account.accountservice.entity.Account;
import com.account.accountservice.repository.AccountRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public Account saveAccount(@NotNull Account account) {

        try {
            int newRecordId = this.getNewRecordId();
            // Account Number format - "12000 + new record AccountId
            account.setAccountNumber(String.valueOf(12000 + newRecordId));

        } catch (Exception e) {
            throw e;
        }
        // save Account detail
        return repository.save(account);
    }

    public List<Account> saveAccounts(@NotNull List<Account> accounts) {
        int newRecordId = this.getNewRecordId();
        // save all Accounts detail
        for (Account account : accounts) {
            account.setAccountNumber(String.valueOf(12000 + newRecordId));
            newRecordId++;
        }
        return repository.saveAll(accounts);
    }

    public List<Account> getAccounts() {

        // get all Accounts detail
        return repository.findAll();
    }

    public Account getAccountById(int id) {
        // get Account detail by Account id
        return repository.findById(id).orElse(null);
    }

    public List<Account> getAccountByCustomerId(int customerId) {
        // get Accounts detail by Customer id
        return repository.findAccountByCustomerId(customerId);
    }

    public List<Account> getAccountByAccountHolderName(String accName) {
        // get Account detail by Account holder name
        return repository.findByAccountHolderName(accName);
    }

    public String deleteAccountById(int accountId) {
        // delete Account by Account id
        repository.deleteById(accountId);
        return "Account Id - " + accountId + " is deleted";
    }

    public Account updateAccount(Account account) {
        // get existing account
        Account existingAccount = repository.findById(account.getAccountId()).orElse(null);
        if (existingAccount != null) {
            // update all fields of account
            existingAccount.setAccountHolderName(account.getAccountHolderName());
            existingAccount.setAccountHolderNicName(account.getAccountHolderNicName());
            existingAccount.setAccountType(account.getAccountType());
            existingAccount.setAccountStatus(account.getAccountStatus());
            existingAccount.setAccountBalance(account.getAccountBalance());
        }
        // save updated changes
        return repository.save(existingAccount);
    }

    public int getNewRecordId() {
        // Account Number generation process
        // Declare variable as lastRecordId and assign value as 0
        int lastRecordId = 0;
        // get last account id - from last record in the account table
        Account acc = repository.findTopByOrderByAccountIdDesc();
        // check record exist or not
        if (acc != null)
            lastRecordId = acc.getAccountId() + 1;
        else lastRecordId = 1;
        return lastRecordId;
    }

}
