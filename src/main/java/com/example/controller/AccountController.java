package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Account;
import com.example.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account accountRequest) {
        return accountService.createAccount(accountRequest.getOwner(), accountRequest.getBalance());
    }

    @PostMapping("/deposit")
    public Account deposit(@RequestParam Long accountId, @RequestParam Double amount) throws Exception {
        return accountService.deposit(accountId, amount);
    }

    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam Long accountId, @RequestParam Double amount) throws Exception {
        return accountService.withdraw(accountId, amount);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam Double amount) throws Exception {
        accountService.transfer(fromAccountId, toAccountId, amount);
    }

    @GetMapping("/balance")
    public Double getBalance(@RequestParam Long accountId) throws Exception {
        return accountService.getBalance(accountId);
    }
}