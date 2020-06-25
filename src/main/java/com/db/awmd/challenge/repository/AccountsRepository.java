package com.db.awmd.challenge.repository;

import java.math.BigDecimal;

import org.springframework.transaction.annotation.Transactional;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;

public interface AccountsRepository {

  void createAccount(Account account) throws DuplicateAccountIdException;

  Account getAccount(String accountId);

  void clearAccounts();
  
  @Transactional
  String transferAmounts(String accountIdFrom,BigDecimal balanceOfFrom,String accountIdTo,BigDecimal balanceOfTo,BigDecimal transferAmount);
  
  @Transactional
  String transferAmountsDirect(String accountIdFrom, String accountIdTo, BigDecimal transferAmount);
}
