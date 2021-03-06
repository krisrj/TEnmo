package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.NotAuthorizedException;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RequestMapping("/user/account/transfer")
@RestController
public class TransferController {

    @Autowired
    private TransferDao transferDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    //TODO Un-Nest all of this ridiculousness
    @RequestMapping(method = RequestMethod.POST)
    public String newTransfer(@Valid @RequestBody Transfer transfer, Principal principal) throws NotAuthorizedException {
        // Check to ensure sending account matches principal account, and does not match receiver account.
        if (!accountDao.getUsernameByAccountId(transfer.getReceiverAccountId()).equals(principal.getName())) {
            if (!transfer.getSenderAccountId().equals(transfer.getReceiverAccountId())) {
                BigDecimal senderBalance = accountDao.findBalanceByAccountId(transfer.getSenderAccountId());
                BigDecimal amountToSend = transfer.getAmount();
                // Compare senderBalance to AmountToSend. Fail if amount isn't positive or exceeds senderBalance.
                if (senderBalance.compareTo(amountToSend) > 0 && amountToSend.compareTo(BigDecimal.ZERO) == 1) {
                    return transferDao.newTransfer(transfer.getSenderAccountId(), transfer.getReceiverAccountId(),
                            transfer.getAmount());
                } throw new NotAuthorizedException();
            } throw new NotAuthorizedException();
        } throw new NotAuthorizedException();
    }

    //TODO Investigate adding constraints to transfer model
    @RequestMapping(path = "/history", method = RequestMethod.GET)
    public List<Transfer> getTransferHistory (Principal principal) {

        return transferDao.getAllTransfersByUserId(userDao.findIdByUsername(principal.getName()));
    }

    @RequestMapping(path = "/details/{transferId}", method = RequestMethod.GET)
    public Transfer getTransfer (@PathVariable Long transferId) throws TransferNotFoundException {
        if (transferDao.getTransfer(transferId) != null) {
            return transferDao.getTransfer(transferId);
        } else {
            throw new TransferNotFoundException();
        }
    }

}




//    throws NotAuthorizedException {

//    int principalUserId = userDao.findIdByUsername(principal.getName());
//    Long senderUserId = accountDao.getUserIdByAccountId(transfer.getSenderAccountId());
//
//        if (senderUserId != principalUserId) {

