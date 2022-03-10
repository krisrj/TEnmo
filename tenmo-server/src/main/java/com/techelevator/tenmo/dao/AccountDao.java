package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    BigDecimal findBalanceByUserId(Long userId);

    BigDecimal addToBalance(Long userId, BigDecimal amountToAdd);

    BigDecimal subtractFromBalance(Long userId, BigDecimal testAmount);

    List<Account> findAllAccounts();

}
