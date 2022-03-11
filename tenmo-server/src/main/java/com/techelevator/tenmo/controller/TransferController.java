package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@PreAuthorize("isAuthenticated()")
@RequestMapping("/user/account/transfer")
@RestController
public class TransferController {

    @Autowired
    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String newTransfer(@Valid @RequestBody Transfer transfer) {
    // Add conditions for success/failure here
        return transferDao.newTransfer(transfer.getSenderAccountId(), transfer.getReceiverAccountId(),
                transfer.getAmount());
    }

}
