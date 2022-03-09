package com.techelevator.tenmo.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Account {

    @NotNull(message = "Account ID can't be null.")
    private Long account_id;
    @NotNull(message = "User ID can't be null.")
    private Long user_id;
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

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    //TODO Ensure this user_id getter is needed
    public Long getUser_id(Long user_id) {
        return this.user_id;
    }


    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    // Original method had things in {}, unsure if needed
    @Override
    public String toString() {
        return "Your current account balance is: " +
                "$" + balance;
    }


}