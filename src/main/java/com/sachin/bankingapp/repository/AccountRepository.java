package com.sachin.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.bankingapp.entity.Account;

public interface AccountRepository  extends JpaRepository<Account,Long>{

}
