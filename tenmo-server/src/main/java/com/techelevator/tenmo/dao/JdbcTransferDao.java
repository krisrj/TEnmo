package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Transfer getTransfer(Long transferId) throws TransferNotFoundException {
        String sql = "SELECT * FROM transfer " +
                     "WHERE transfer_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transferId);
        if (rowSet.next()) {
            return mapRowToTransfer(rowSet);
        } throw new TransferNotFoundException();
    }

    //TODO Handle exception if currentUserId doesn't match any in database
    @Override
    public List<Transfer> getAllTransfersByUserId(Long currentUserId) {
        String sql = "SELECT t.transfer_id, u.username, u2.username, t.amount " +
                "FROM transfer t " +
                "JOIN account a " +
                "ON t.account_from = a.account_id " +
                "JOIN account b " +
                "ON t.account_to = b.account_id " +
                "JOIN tenmo_user u " +
                "ON u.user_id = a.user_id " +
                "JOIN tenmo_user u2 " +
                "ON u2.user_id = b.user_id " +
                "WHERE a.user_id = ? OR " +
                "b.user_id = ? " +
                "ORDER BY transfer_id DESC;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, currentUserId, currentUserId);
        List<Transfer> transferList = new ArrayList<>();
        while (rowSet.next()) {
            transferList.add(mapRowToTransfer(rowSet));
        } return transferList;
    }

    private Transfer mapRowToTransfer(SqlRowSet resultSet) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(resultSet.getLong("transfer_id"));
        transfer.setTransferTypeId(resultSet.getLong("transfer_type_id"));
        transfer.setTransferStatusId(resultSet.getLong("transfer_status_id"));
        transfer.setSenderUserId(resultSet.getLong("account_from"));
        transfer.setReceiverUserId(resultSet.getLong("account_to"));
        transfer.setAmount(resultSet.getBigDecimal("amount"));
        return transfer;
    }

//    @Override
//    public List<Transfer> getAllTransfersByUserId(Long currentUserId) {
//        // Check to make sure logged in user matches currentUserId
//        // if they do match, proceed with SQL string to get all transfers
//        // that match the given currentUserId (WHERE t.user_id = ?)
//        // Then use SqlRowSet to get a result, which is then mapped
//        // using mapRowToTransfer to a new ArrayList of Transfer objects
//        // populate the ArrayList using a while (rowSet.next())
//        // Finally, return the ArrayList
//        List<Transfer> transferList = new ArrayList<>();
//
//
//    }


}

