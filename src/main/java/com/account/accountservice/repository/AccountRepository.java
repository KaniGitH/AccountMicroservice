package com.account.accountservice.repository;


import com.account.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


    List<Account> findByAccountHolderName(String accountHolderName);

    List<Account> findAccountByCustomerId(int customerId);

    Account findTopByOrderByAccountIdDesc();
}
