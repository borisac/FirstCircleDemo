package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Account;
import com.example.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
	  @Autowired
	    private AccountRepository accountRepository;

	    public Account createAccount(String owner, Double initialDeposit) {
	        Account account = new Account(owner, initialDeposit);
	        return accountRepository.save(account);
	    }

	    public Account deposit(Long accountId, Double amount) throws Exception {
	        Optional<Account> accountOpt = accountRepository.findById(accountId);
	        if (accountOpt.isPresent()) {
	            Account account = accountOpt.get();
	            account.setBalance(account.getBalance()+ amount);
	            return accountRepository.save(account);
	        } else {
	            throw new Exception("Account not found");
	        }
	    }

	    public Account withdraw(Long accountId, Double amount) throws Exception {
	        Optional<Account> accountOpt = accountRepository.findById(accountId);
	        if (accountOpt.isPresent()) {
	            Account account = accountOpt.get();
	            if (account.getBalance() >= amount) {
	                account.setBalance(account.getBalance() - amount);
	                return accountRepository.save(account);
	            } else {
	                throw new Exception("Insufficient funds");
	            }
	        } else {
	            throw new Exception("Account not found");
	        }
	    }

	    @Transactional
	    public void transfer(Long fromAccountId, Long toAccountId, Double amount) throws Exception {
	        withdraw(fromAccountId, amount);
	        deposit(toAccountId, amount);
	    }

	    public Double getBalance(Long accountId) throws Exception {
	        Optional<Account> accountOpt = accountRepository.findById(accountId);
	        if (accountOpt.isPresent()) {
	            return accountOpt.get().getBalance();
	        } else {
	            throw new Exception("Account not found");
	        }
	    }
}
