package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

  //  public BigDecimal testAmount = new BigDecimal(50);
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal findBalanceByUserId(Long userId) {
       String sql = "SELECT balance " +
               "FROM account " +
               "WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
    }

    public BigDecimal findBalanceByAccountId(Long accountId) {
        String sql = "SELECT balance " +
                "FROM account " +
                "WHERE account_id = ?";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, accountId);
    }

    @Override
    public BigDecimal addToBalance(Long receiverAccountId, BigDecimal amount) {
        String sql = "UPDATE account SET balance = balance + ? " +
                     "WHERE account_id = ?;";
        jdbcTemplate.update(sql, amount, receiverAccountId);
        return findBalanceByAccountId(receiverAccountId);
    }

    @Override
    public BigDecimal subtractFromBalance(Long senderAccountId, BigDecimal amount) {
        String sql = "UPDATE account SET balance = balance - ? " +
                "WHERE account_id = ?;";
        jdbcTemplate.update(sql, amount, senderAccountId);
        return findBalanceByAccountId(senderAccountId);
    }


    private Account mapRowToAccount(SqlRowSet resultSet) {
        Account account = new Account();
        account.setAccountId(resultSet.getLong("account_id"));
        account.setUserId(resultSet.getLong("user_Id"));
        account.setBalance(resultSet.getBigDecimal("balance"));
        return account;
    }
    
    //TODO Implement these two methods
    @Override
    public List<Account> findAllAccounts() {
        return null;
    }


}
