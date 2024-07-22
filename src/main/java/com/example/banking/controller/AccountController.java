package com.example.banking.controller;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add Account REST API

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto addAccount(@RequestBody AccountDto accountDto)
    {
        return accountService.createAccount(accountDto);
    }

    // Get Account REST API
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccountByID(@PathVariable Long id)
    {
        // return new ResponseEntity<>(accountService.getAccountByID(id), HttpStatus.OK);
        // Or
        AccountDto accountDto = accountService.getAccountByID(id);
        return accountDto;
    }


    // Deposit REST API

    @PutMapping("/{id}/deposit")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto deposit(@PathVariable Long id, @RequestBody Map<String, Double> request)
    {
        double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return accountDto;
    }


    // Withdraw REST API
    @PutMapping("/{id}/withdraw")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request)
    {
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return accountDto;
    }

    // Get all Account REST API
    @GetMapping("/allAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> getAllAccounts()
    {
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        return accountDtos;
    }

    // Delete Account REST API
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAccount(@PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return "Account is deleted successfully.";
    }
}
