package com.sachin.bankingapp.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.sachin.bankingapp.dto.AccountDto;
import com.sachin.bankingapp.dto.AccountMapper;
import com.sachin.bankingapp.entity.Account;
import com.sachin.bankingapp.repository.AccountRepository;
import com.sachin.bankingapp.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository)
    {
        this.accountRepository=accountRepository;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account=AccountMapper.mapToAccount(accountDto);
        Account saveAccount=accountRepository.save(account);
        return AccountMapper.mapToAcccountDto(saveAccount);
        
        
    }
    @Override
    public AccountDto findAccountById(Long id) {
        Account findAccount=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Not Found"));
        return AccountMapper.mapToAcccountDto(findAccount);
    }
    @Override
    public AccountDto depositBalance(Long id, Double depositBalance) {
        Account findAccount=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Not Found"));
        findAccount.setBalance(findAccount.getBalance()+depositBalance);
        Account saveAccount=accountRepository.save(findAccount);
        return AccountMapper.mapToAcccountDto(saveAccount);
    }
    @Override
    public AccountDto withdrawBalance(Long id, Double amount) {
        Account findAccount=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Not Found"));
        if(findAccount.getBalance()<amount)
            throw new RuntimeException("Insufficient Balance");

        findAccount.setBalance(findAccount.getBalance()-amount);
        Account saveAccount=accountRepository.save(findAccount);
        return AccountMapper.mapToAcccountDto(saveAccount);
    }
    @Override
    public List<AccountDto> getAllAccounts() {
       List <Account> accounts=accountRepository.findAll();
       return accounts.stream().map((account)->AccountMapper.mapToAcccountDto(account)).collect(Collectors.toList());
    }
    @Override
    public void deleteAccountById(Long id) {
        accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Not Found"));
        accountRepository.deleteById(id);
    }
    

}
