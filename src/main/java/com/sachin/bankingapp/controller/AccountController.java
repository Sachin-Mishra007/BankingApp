package com.sachin.bankingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.bankingapp.dto.AccountDto;
import com.sachin.bankingapp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountbyId(@PathVariable Long id)
    {
        AccountDto accountDto=accountService.findAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositBalance(@PathVariable Long id, @RequestBody Map <String,Double> request)
    {
        double amount=request.get("amount");
        AccountDto accountDto=accountService.depositBalance(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawBalance(@PathVariable Long id, @RequestBody Map <String,Double> request)
    {
        double amount=request.get("amount");
        AccountDto accountDto=accountService.withdrawBalance(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteById(@PathVariable Long id)
    {
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("Account Deleted Successfully");
    }
    @GetMapping
    public ResponseEntity<List <AccountDto>> findAllAccounts()
    {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

}
