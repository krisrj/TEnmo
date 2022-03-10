package com.techelevator.tenmo.dao;

import javax.validation.Valid;
import java.math.BigDecimal;

public interface TransferDao {

   BigDecimal updateSenderBalance(@Valid BigDecimal amount, Long senderId);

   String newTransferv1(Long senderId, Long receiverId, BigDecimal amountToTransfer);

   BigDecimal updateReceiverBalance(BigDecimal amount, Long receiverId);

   BigDecimal updateSenderBalance(Long senderId);

   BigDecimal updateReceiverBalance(Long receiverId);

}
