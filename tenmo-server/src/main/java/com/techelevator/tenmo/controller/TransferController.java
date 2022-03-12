package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.NotAuthorizedException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;

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
        // Check to ensure sending account matches principal account
        if (!accountDao.getUsernameByAccountId(transfer.getReceiverAccountId()).equals(principal.getName())) {
            // Check to ensure sending account is not the same as receiving account
            if (!transfer.getSenderAccountId().equals(transfer.getReceiverAccountId())) {
                // Create simple variables for compareTo check
                BigDecimal senderBalance = accountDao.findBalanceByAccountId(transfer.getSenderAccountId());
                BigDecimal amountToSend = transfer.getAmount();
                // Compare senderBalance to AmountToSend, fail if 0 or negative
                if (senderBalance.compareTo(amountToSend) > 0 && amountToSend.compareTo(BigDecimal.ZERO) == 1) {
                    return transferDao.newTransfer(transfer.getSenderAccountId(), transfer.getReceiverAccountId(),
                            transfer.getAmount());
                } throw new NotAuthorizedException();
            } throw new NotAuthorizedException();
        } throw new NotAuthorizedException();
    }
}


//    throws NotAuthorizedException {

//    int principalUserId = userDao.findIdByUsername(principal.getName());
//    Long senderUserId = accountDao.getUserIdByAccountId(transfer.getSenderAccountId());
//
//        if (senderUserId != principalUserId) {

