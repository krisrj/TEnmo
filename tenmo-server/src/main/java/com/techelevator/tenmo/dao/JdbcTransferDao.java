package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class JdbcTransferDao implements TransferDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AccountDao accountDao;

    @Transactional
    @Override
    public String newTransfer(Long senderAccountId, Long receiverAccountId, BigDecimal amount) {
            // This updates the transfer table with relevant details
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, " +
                "account_from, account_to, amount) " +
                "VALUES (2, 2, ?, ?, ?);";
        jdbcTemplate.update(sql, senderAccountId, receiverAccountId, amount);

            // These two methods adjust the pertinent balances
        accountDao.addToBalance(receiverAccountId, amount);
        accountDao.subtractFromBalance(senderAccountId, amount);

            // Setting up UserId variables to use in the answer String
        String senderUsername = accountDao.getUsernameByAccountId(senderAccountId);
        String receiverUsername = accountDao.getUsernameByAccountId(receiverAccountId);

            // String answer that will be displayed to the user
        String answer = "Transfer of " + amount + " TE Bucks from " + senderUsername +
                " to " + receiverUsername + " was successful.";
        return answer;
    }
}

