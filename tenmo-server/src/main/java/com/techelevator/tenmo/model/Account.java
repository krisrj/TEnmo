package com.techelevator.tenmo.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Account {

    @NotNull(message = "Account ID can't be null.")
    private Long accountId;
    @NotNull(message = "User ID can't be null.")
    private Long userId;
    @NotNull(message = "Balance can't be null.")
    @Digits(integer=13, fraction=2)
    private BigDecimal balance;
    //TODO Make sure you add @Valid to the controller class


    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId(Long userId) {
        return this.userId;
    }

    @Override
    public String toString() {
        return "Your current account balance is: " +
                "$" + balance;
    }


}
