package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;

@PreAuthorize("isAuthenticated")
@RequestMapping("/user/account")
@RestController
public class AccountController {

    @Autowired
    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    //TODO Make sure authenticatedUser can't access someone else's
    // account balance.
    @RequestMapping(path = "/balance/{id}", method = RequestMethod.GET)
    public BigDecimal getBalance(@Valid @PathVariable("id") Long userId) {
        // Possible Solution: Check principal.getName() == userId from
        // above if not, throw NotAuthorizedUserException e
        // if so, return accountDao.findBalanceByUserId(userId)
        return accountDao.findBalanceByUserId(userId);
    }
}
