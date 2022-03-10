package com.techelevator.tenmo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.math.BigDecimal;

@Component
public class JdbcTransferDao implements TransferDao{

    public BigDecimal testAmount = new BigDecimal(50);

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Override
//    public String sendTransfer(Long senderId, Long receiverId, BigDecimal amount) {
//
//    }

    // If sender != receiver
    // if amount isn't more than current account balance
    //





//    @Override
//    public BigDecimal updateSenderBalance(@Valid BigDecimal amount, Long senderId) {
//        // Get balance of sender
        // Update balance by amount (PUT new amount in)
//        String sql = "SELECT balance FROM account " +
//                     "WHERE user_id = ?;";
//        BigDecimal result;
//        result = jdbcTemplate.queryForObject(sql, BigDecimal.class, senderId);
//
//            BigDecimal newBalance = result.subtract(testAmount);
//            return newBalance;
//    }

    @Override
    public String newTransfer(Long senderId, Long receiverId, BigDecimal amountToTransfer) {
        // Sql string to insert into Transfer
        String sql = "INSERT INTO transfer"
        // This updates the empty values that were inserted with the included
        // values
        jdbcTemplate.update(sql, senderId, receiverId);

    }

    @Override
    public BigDecimal updateReceiverBalance(BigDecimal amount, Long receiverId) {
        return null;
    }

    @Override
    public BigDecimal updateSenderBalance(Long senderId) {
        return null;
    }

    @Override
    public BigDecimal updateReceiverBalance(Long receiverId) {
        return null;
    }

    // Takes as input senderId, receiverId, amount
        // Calls updateSenderBalance, then updateReceiverBalance
        // by the amount specified,
        // then returns a String stating "Transfer successful" and
        // concatenate the new balance
        // else "Not enough money, etc" upon failure

}
