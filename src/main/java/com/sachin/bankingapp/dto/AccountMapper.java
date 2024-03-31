package com.sachin.bankingapp.dto;

import com.sachin.bankingapp.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto)
    {
        Account account=new Account(
            accountDto.getId(),
            accountDto.getAccountHolderName(),
            accountDto.getBalance()
        );
        return account;
    }
    public static AccountDto mapToAcccountDto(Account account)
    {
        AccountDto accountDto=new AccountDto(
            account.getId(),
            account.getAccountHolderName(),
            account.getBalance()
        
        );
        return accountDto;
    }
}
