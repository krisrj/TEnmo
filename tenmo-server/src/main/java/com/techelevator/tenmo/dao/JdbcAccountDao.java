package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal findBalanceByUserId(Long userId) {
        String sql = "SELECT balance"
    }
    
    //TODO Implement these two methods
    @Override
    public List<Account> findAllAccounts() {
        return null;
    }

    @Override
    public BigDecimal findBalanceUserId(Long userId) {
        return null;
    }
}
