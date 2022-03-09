package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
       String sql = "Select balance\n" +
               "From tenmo_user t \n" +
               "Join account a On t.user_id = a.user_id\n" +
               "Where t.user_id = ?";

        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);

       return balance;
    }

    private Account mapRowToAccount(SqlRowSet resultSet) {
        Account account = new Account();
        account.setAccount_id(resultSet.getLong("account_id"));
        account.setUser_id(resultSet.getLong("user_Id"));
        account.setBalance(resultSet.getBigDecimal("balance"));


        return account;
    }
    
    //TODO Implement these two methods
    @Override
    public List<Account> findAllAccounts() {
        return null;
    }


}
