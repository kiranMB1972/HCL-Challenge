package com.db.awmd.challenge.web;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.service.AccountsService;
//import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounts")
@Slf4j
public class AccountsController {

  private final AccountsService accountsService;

  @Autowired
  public AccountsController(AccountsService accountsService) {
    this.accountsService = accountsService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createAccount(@RequestBody /*@Valid*/ Account account) {
    //log.info("Creating account {}", account);

    try {
    this.accountsService.createAccount(account);
    } catch (DuplicateAccountIdException daie) {
      return new ResponseEntity<>(daie.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(path = "/{accountId}")
  public Account getAccount(@PathVariable String accountId) {
    //log.info("Retrieving account for id {}", accountId);
    return this.accountsService.getAccount(accountId);
  }
  
  @PutMapping(path="/{accountIdFrom}/{balanceOfFrom}/{accountIdTo}/{balanceOfTo}/{transferAmount}")
  public String transferAmounts(@PathVariable String accountIdFrom,@PathVariable BigDecimal balanceOfFrom,@PathVariable String accountIdTo,@PathVariable BigDecimal balanceOfTo,@PathVariable BigDecimal transferAmount)
  {
	  return accountsService.transferAmounts(accountIdFrom, balanceOfFrom, accountIdTo, balanceOfTo, transferAmount);
  }
  
  @PutMapping(path="/{accountIdFrom}/{accountIdTo}/{transferAmount}")
  public String transferAmounts_Testing(@PathVariable String accountIdFrom,@PathVariable String accountIdTo,@PathVariable BigDecimal transferAmount){
	  return accountsService.transferAmountsDirect(accountIdFrom, accountIdTo, transferAmount);
  }

}
