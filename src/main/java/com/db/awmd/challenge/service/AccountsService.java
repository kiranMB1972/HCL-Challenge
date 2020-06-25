package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.repository.AccountsRepository;
import lombok.Getter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;

  public AccountsRepository getAccountsRepository() {
	return accountsRepository;
}

@Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }
  
  public String transferAmounts(String accountIdFrom,BigDecimal balanceOfFrom,String accountIdTo,BigDecimal balanceOfTo,BigDecimal transferAmount) {
	  return this.accountsRepository.transferAmounts(accountIdFrom, balanceOfFrom, accountIdTo, balanceOfTo, transferAmount);
  }
  
  public String transferAmountsDirect(String accountIdFrom, String accountIdTo, BigDecimal transferAmount) {
	  return this.accountsRepository.transferAmountsDirect(accountIdFrom, accountIdTo, transferAmount);
  }
}
