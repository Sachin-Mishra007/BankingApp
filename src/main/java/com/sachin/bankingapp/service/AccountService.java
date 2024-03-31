package com.sachin.bankingapp.service;

import java.util.List;

import com.sachin.bankingapp.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount (AccountDto account);
    AccountDto findAccountById (Long id);
    AccountDto depositBalance(Long id,Double amount);
    AccountDto withdrawBalance(Long id, Double amount);
    List <AccountDto> getAllAccounts();
    void deleteAccountById(Long id);
}
