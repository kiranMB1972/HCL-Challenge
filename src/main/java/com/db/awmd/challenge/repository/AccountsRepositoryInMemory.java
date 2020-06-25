package com.db.awmd.challenge.repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountsRepositoryInMemory implements AccountsRepository {

  private final Map<String, Account> accounts = new ConcurrentHashMap<>();

  @Override
  public void createAccount(Account account) throws DuplicateAccountIdException {
    Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
    if (previousAccount != null) {
      throw new DuplicateAccountIdException(
        "Account id " + account.getAccountId() + " already exists!");
    }
  }

  @Override
  public Account getAccount(String accountId) {
    return accounts.get(accountId);
  }

  @Override
  public void clearAccounts() {
    accounts.clear();
  }
  
  @Transactional
  public String transferAmmounts(String accountIdFrom, BigDecimal balanceOfFrom, String accountIdTo,BigDecimal balanceOfTo, BigDecimal transferAmount) {
    Account accountFrom=new Account(accountIdFrom);
    accountFrom.setBalance(balanceOfFrom);
    Account accountTo=new Account(accountIdTo);
    accountFrom.setBalance(balanceOfFrom.subtract(transferAmount));
    accountTo.setBalance(balanceOfTo.add(transferAmount));
    
    System.out.println(accountTo.getBalance());
      return "Successfully transferred amount "+transferAmount+" from '"+accountIdFrom+"' to '"+accountIdTo+"'\nCurrently balances are: "+accountFrom.getBalance()+", "+accountTo.getBalance()+" respectively.........";
  }
  
  @Transactional
  public String transferAmountsDirect(String accountIdFrom, String accountIdTo, BigDecimal transferAmount) {
	  Account accountFrom=new Account(accountIdFrom);
	  Account accountTo=new Account(accountIdTo);
	  BigDecimal balanceOfFrom,balanceOfTo;
	  
	  accountFrom.setBalance(BigDecimal.valueOf(5000));
	  accountTo.setBalance(BigDecimal.valueOf(50));
	  
	  balanceOfFrom=BigDecimal.valueOf(5000);
	  balanceOfTo=BigDecimal.valueOf(100);
	  
	  balanceOfFrom=balanceOfFrom.subtract(transferAmount);
	  balanceOfTo=balanceOfFrom.add(transferAmount);
	  
	  accountFrom.setBalance(balanceOfFrom);
	  accountTo.setBalance(balanceOfTo);
      return "Successfully transferred amount "+transferAmount+" from '"+accountIdFrom+"' to '"+accountIdTo+"'\nCurrently balances are: "+accountFrom.getBalance()+", "+accountTo.getBalance()+" respectively.........";
  }

@Override
public String transferAmounts(String accountIdFrom, BigDecimal balanceOfFrom, String accountIdTo,BigDecimal balanceOfTo, BigDecimal transferAmount) {
	  Account accountFrom=new Account(accountIdFrom);
	  Account accountTo=new Account(accountIdTo);
	  
	  accountFrom.setBalance(BigDecimal.valueOf(5000));
	  accountTo.setBalance(BigDecimal.valueOf(50));
	  
	  
	  balanceOfFrom=BigDecimal.valueOf(5000);
	  balanceOfTo=BigDecimal.valueOf(100);
	  
	  balanceOfFrom=balanceOfFrom.subtract(transferAmount);
	  balanceOfTo=balanceOfFrom.add(transferAmount);
	  
	  accountFrom.setBalance(balanceOfFrom);
	  accountTo.setBalance(balanceOfTo);
    return "Successfully transferred amount "+transferAmount+" from '"+accountIdFrom+"' to '"+accountIdTo+"'\nCurrently balances are: "+accountFrom.getBalance()+", "+accountTo.getBalance()+" respectively.........";
}
}
