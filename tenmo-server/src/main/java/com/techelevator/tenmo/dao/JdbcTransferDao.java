package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.math.BigDecimal;

@Component
public class JdbcTransferDao implements TransferDao{

    public BigDecimal testAmount = new BigDecimal(50);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AccountDao accountDao;

    @Transactional
    @Override
    public String newTransfer(Long senderAccountId, Long receiverAccountId, BigDecimal amountToTransfer) {
        // Sql string to insert into Transfer
        //if else here
        // updated status_id here

        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, " +
                "account_from, account_to, amount) " +
                "VALUES (2, 2, ?, ?, ?);";
        // This updates the empty values that were inserted with the included
        // values
        jdbcTemplate.update(sql, senderAccountId, receiverAccountId, amountToTransfer);
        accountDao.addToBalance(receiverAccountId, amountToTransfer);
        accountDao.subtractFromBalance(senderAccountId, amountToTransfer);
        return "Transfer complete.";
    }

}
