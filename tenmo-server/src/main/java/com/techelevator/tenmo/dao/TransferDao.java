package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface TransferDao {

   String newTransfer(Long senderAccountId, Long receiverAccountId, BigDecimal amountToTransfer);

}
