package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    Long getUserIdByAccountId(Long accountId);

    String getUsernameByAccountId(Long accountId);

    BigDecimal findBalanceByUserId(Long userId);

    BigDecimal findBalanceByAccountId(Long accountId);

    BigDecimal addToBalance(Long receiverAccountId, BigDecimal amountToAdd);

    BigDecimal subtractFromBalance(Long senderAccountId, BigDecimal testAmount);

    List<Account> findAllAccounts();

}
