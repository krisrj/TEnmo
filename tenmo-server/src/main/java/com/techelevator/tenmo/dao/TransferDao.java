package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

   String newTransfer(Long senderAccountId, Long receiverAccountId, BigDecimal amountToTransfer);

   Transfer getTransfer(Long transferId) throws TransferNotFoundException;

   List<Transfer> getAllTransfersByUserId(Long currentUserId);

}
